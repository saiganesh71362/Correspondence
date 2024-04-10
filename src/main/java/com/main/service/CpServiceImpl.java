package com.main.service;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.main.binding.CpResponse;
import com.main.entity.CasesEntity_Cp;
import com.main.entity.CitizenApiEntity_Cp;
import com.main.entity.CoTriggerEntity_Cp;
import com.main.entity.EligiblityDlEntity_Cp;
import com.main.repository.CasesRepository;
import com.main.repository.CitizenApiRepository;
import com.main.repository.CoTriggerRepository;
import com.main.repository.EligiblityDlRepository;
import com.main.utils.EmailUtils;


@Service
public class CpServiceImpl implements CpService {

	@Autowired
	private CoTriggerRepository coTrgRepo;
	
	@Autowired
	private EligiblityDlRepository eligRepo;
	
	@Autowired
	private CitizenApiRepository appRepo;
	
	@Autowired
	private CasesRepository cpCaseRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public CpResponse processPendingTriggers() {
		
		CpResponse response = new CpResponse();
		
		
		CitizenApiEntity_Cp appEntity = null;
		//fetch all pending triggers
		List<CoTriggerEntity_Cp> pendingTriggers = coTrgRepo.findByTrgStatus("Pending");
		
		response.setTotalTriggers(Long.valueOf(pendingTriggers.size()));
		
		//process each Pending trigger
		for(CoTriggerEntity_Cp entity : pendingTriggers) {
			
			//get eligibility data based on caseNum
			EligiblityDlEntity_Cp eligRecord = eligRepo.findByCaseNum(entity.getCaseNum());

			//get citizen data based on caseNum
			Optional<CasesEntity_Cp> findById = cpCaseRepo.findById(entity.getCaseNum());
			if (findById.isPresent()) {
				CasesEntity_Cp cpCasesEntity = findById.get();
				Integer appId = cpCasesEntity.getAppId();
				Optional<CitizenApiEntity_Cp> appEntityOptional = appRepo.findById(appId);
				
				if (appEntityOptional.isPresent()) {
					appEntity = appEntityOptional.get();
				}
			}
			
			//generate pdf with elig details & send the pdf to citize mail
			
			Long success = 0l;
			Long failed = 0l;
			
			try {
				generateAndSendPdf(eligRecord, appEntity);
				success++;
			} catch (Exception e) {
				e.printStackTrace();
				failed++;
			}
			response.setSuccTriggers(success);
			response.setFailedTriggers(failed);
			
		}
		
		//return summary
		return response;
	}
	
	private void generateAndSendPdf(EligiblityDlEntity_Cp eligData, CitizenApiEntity_Cp appEntity) throws Exception {
		Document document = new Document(PageSize.A4);
		FileOutputStream fos = null;
		File file = new File(eligData.getCaseNum()+".pdf");
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		PdfWriter.getInstance(document, fos);
		
		document.open();
		com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);  // This Line Error
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph p = new Paragraph("ELIGIBILITY REPORT", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
		
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 1.5f, 3.0f, 1.5f, 3.0f});
		table.setSpacingBefore(10);
		
		PdfPCell cell = new PdfPCell();
		
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("Citizen Name", font));
		table.addCell(cell);
			
		cell.setPhrase(new Phrase("Plan Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Plan Status", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Plan Start Date", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Plan End Date", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Benefot Amount", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Denial Reason", font));
		table.addCell(cell);
		
			table.addCell(appEntity.getFullName());
			table.addCell(eligData.getPlanName());
			table.addCell(eligData.getPlanStatus());
			table.addCell(eligData.getPlanStartDate()+"");
			table.addCell(eligData.getPlanEndDate()+"");
			table.addCell(eligData.getBenefitAmt()+"");
			table.addCell(eligData.getDenialReason()+"");
		document.add(table);
		document.close();
		
		
		String subject = "HIS Eligibility Info";
		String body = "HIS Eligibility Info";
		emailUtils.sendEmail(subject, body,appEntity.getEmail() , file);
		updateTrigger(eligData.getCaseNum(), file);
		
		//file.delete();
	}
	
	private void updateTrigger(Long caseNum, File file) throws Exception {
		CoTriggerEntity_Cp coTriggerEntity = coTrgRepo.findByCaseNum(caseNum);
		
		byte[] arr = new byte[(byte) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(arr);
		
		coTriggerEntity.setCoPdf(arr);
		coTriggerEntity.setTrgStatus("Completed");
		coTrgRepo.save(coTriggerEntity);
		
		fis.close();
	}
}




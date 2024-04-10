package com.main.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CO_TRIGGERS_DC")
public class CoTriggerEntity_Cp
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trgId;

	private Long caseNum;

	@Lob
	private byte[] coPdf;

	private String trgStatus;
	
	private String createdBy;
	private String updateBy;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate cretedDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;
	public Integer getTrgId() {
		return trgId;
	}
	public void setTrgId(Integer trgId) {
		this.trgId = trgId;
	}
	public Long getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(Long caseNum) {
		this.caseNum = caseNum;
	}
	public byte[] getCoPdf() {
		return coPdf;
	}
	public void setCoPdf(byte[] coPdf) {
		this.coPdf = coPdf;
	}
	public String getTrgStatus() {
		return trgStatus;
	}
	public void setTrgStatus(String trgStatus) {
		this.trgStatus = trgStatus;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public LocalDate getCretedDate() {
		return cretedDate;
	}
	public void setCretedDate(LocalDate cretedDate) {
		this.cretedDate = cretedDate;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

}

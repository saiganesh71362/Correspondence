package com.main.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "INCOME_ENTITY_DC")
public class IncomeEntity_Cp
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer incomeId;
	
	private Long caseNum;
	
	private Double empIncome;
	
	private Double propertyIncome;
	
	private Double rentIncome;
	
	private String createdBy;
	private String updateBy;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate cretedDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;
	public Integer getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(Integer incomeId) {
		this.incomeId = incomeId;
	}
	public Long getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(Long caseNum) {
		this.caseNum = caseNum;
	}
	public Double getEmpIncome() {
		return empIncome;
	}
	public void setEmpIncome(Double empIncome) {
		this.empIncome = empIncome;
	}
	public Double getPropertyIncome() {
		return propertyIncome;
	}
	public void setPropertyIncome(Double propertyIncome) {
		this.propertyIncome = propertyIncome;
	}
	public Double getRentIncome() {
		return rentIncome;
	}
	public void setRentIncome(Double rentIncome) {
		this.rentIncome = rentIncome;
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

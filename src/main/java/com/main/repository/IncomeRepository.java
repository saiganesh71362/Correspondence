package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.IncomeEntity_Cp;

@Repository
public interface IncomeRepository  extends JpaRepository<IncomeEntity_Cp, Integer>{
	public IncomeEntity_Cp findByCaseNum(Long caseNum);

}

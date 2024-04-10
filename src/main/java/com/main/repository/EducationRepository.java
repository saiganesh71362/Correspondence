package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.EducationEntity_Cp;

@Repository
public interface EducationRepository extends JpaRepository<EducationEntity_Cp, Integer> {
	public EducationEntity_Cp findByCaseNum(Long caseNum);


}

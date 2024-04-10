package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.CoTriggerEntity_Cp;

@Repository
public interface CoTriggerRepository extends JpaRepository<CoTriggerEntity_Cp, Integer>{

	public List<CoTriggerEntity_Cp> findByTrgStatus(String staus);
	
	public CoTriggerEntity_Cp findByCaseNum(Long caseNum);

}

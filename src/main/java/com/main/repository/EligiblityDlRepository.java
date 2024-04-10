package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.EligiblityDlEntity_Cp;

@Repository
public interface EligiblityDlRepository extends JpaRepository<EligiblityDlEntity_Cp, Integer> 
{
	public EligiblityDlEntity_Cp findByCaseNum(Long caseNum);


}

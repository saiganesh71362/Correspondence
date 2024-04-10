package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.ChildernEntity_Cp;

@Repository
public interface ChildernRepository extends JpaRepository<ChildernEntity_Cp, Integer>
{
	public List<ChildernEntity_Cp> findByCaseNum(Long caseNum);

}
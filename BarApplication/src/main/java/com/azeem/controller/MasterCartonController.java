package com.azeem.controller;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azeem.dao.Dao;
import com.azeem.dto.MasterCartonViewDto;
import com.azeem.entities.MasterCarton;
import com.azeem.service.MasterCartonService;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@RestController
public class MasterCartonController {

	@Autowired
	private MasterCartonService masterCartonService;
	@Autowired
	private Dao dao;
	

	@PostMapping("/mastercartonrecord")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> saveMastercartonRecord(@RequestBody MasterCarton cartonTemplate) {
		System.out.println("Output :" + cartonTemplate);
		return masterCartonService.saveCartonTemplate(cartonTemplate);
	}

	@GetMapping("/mastercartonrecord")
	public ResponseEntity<?> getMastercartonRecord() {
		return masterCartonService.getAllRecords();

	}

	@GetMapping("/mastercartonrecord/{key}")
	public ResponseEntity<?> getMastercartonRecordById(@PathVariable int key) {
		return masterCartonService.findById(key);
	}

	
	@GetMapping("/mastercartonviewrecord")
	public List<MasterCartonViewDto> testing() throws SQLException{
	return 	dao.connect("select * from MasterCartonViewDto");
	}

}

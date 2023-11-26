package com.azeem.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azeem.dao.Dao;
import com.azeem.dto.MasterCartonViewDto;
import com.azeem.dto.Master_Carton_Detail_View_Dto;
import com.azeem.entities.MasterCartonScanner;
import com.azeem.service.MasterCartonScannerService;

@RestController
public class MasterCartonScannerController {

	@Autowired
	private MasterCartonScannerService service;
	@Autowired
	private Dao dao;
	@PostMapping("/mastercartonscannerrecord")
	public ResponseEntity<?> saveRecord(@RequestBody MasterCartonScanner record){
		return service.saveRecord(record);
	}
	
	@GetMapping("/mastercartonscannerrecord")
	public ResponseEntity<?> getAllRecords(){
		return service.fetchAllRecords();
	}
	
	@GetMapping("/mastercartonscannerrecord/{id}")
	public ResponseEntity<?> getById(@PathVariable int id){
		return service.fetchById(id);
	}
	
	
	@GetMapping("/mastercartonscannerrecordbytemplateid/{templateid}")
	public ResponseEntity<?> getBytemplateid(@PathVariable String templateid){
		List<Master_Carton_Detail_View_Dto> list = new ArrayList<>();
			list = new Dao().masterCartonDetailViewDtoGetAll("select * from master_carton_detail_vw where templateid='"+templateid+"'");
		return new ResponseEntity<>(list ,HttpStatus.OK);
	}
	
	
	
	
}

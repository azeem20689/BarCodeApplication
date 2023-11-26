package com.azeem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.azeem.entities.MasterCartonScanner;
import com.azeem.repo.MasterCartonScannerRepo;

@Service
public class MasterCartonScannerService {
	@Autowired
	private MasterCartonScannerRepo repo;
	
	public ResponseEntity<?> saveRecord(MasterCartonScanner record){
		System.out.println("Record Received : "+record);
		return new  ResponseEntity<>(repo.save(record),HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> fetchAllRecords(){
		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<?> fetchById(int id){
		return new ResponseEntity<>(repo.findById(id).get(),HttpStatus.OK);
	}
	public ResponseEntity<?> getBykey(int key){
		return new ResponseEntity<>(repo.fetchByKey(key),HttpStatus.OK);
	}
}

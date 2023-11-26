package com.azeem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.azeem.entities.MasterCarton;
import com.azeem.repo.MasterCartonRepo;

@Service
public class MasterCartonService {

	
	
	@Autowired
	private MasterCartonRepo masterCartonRepo;
	
	
	public ResponseEntity<?> saveCartonTemplate(MasterCarton cartonTemplate) {
		return new ResponseEntity<>(masterCartonRepo.save(cartonTemplate), HttpStatus.CREATED);
		
	}

	public ResponseEntity<?> getAllRecords() {
//		return ResponseEntity.ok(masterCartonViewRepo.findAll());
		return ResponseEntity.ok(masterCartonRepo.findAll());
		
	}
	
	public ResponseEntity<?> findById(int key){
		MasterCarton masterCarton =  masterCartonRepo.findById(key).get();
		if(masterCarton != null) {
			return new ResponseEntity<>(masterCarton, HttpStatus.OK); 
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	

	
	
}

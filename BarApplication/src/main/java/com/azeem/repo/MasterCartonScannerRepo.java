package com.azeem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.azeem.entities.MasterCartonScanner;

@Repository
public interface MasterCartonScannerRepo extends JpaRepository<MasterCartonScanner, Integer>{
	
	@Query(value = "select * from master_carton_scanner where key=:key",nativeQuery = true)
	List<MasterCartonScanner> fetchByKey(@Param("key") int key);
}

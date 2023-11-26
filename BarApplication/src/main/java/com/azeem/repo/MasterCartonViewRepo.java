//package com.azeem.repo;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Component;
//
//import com.azeem.dto.MasterCartonViewDto;
//
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.Repository;
//
//
//public interface MasterCartonViewRepo extends JpaRepository<MasterCartonViewDto, Long>{
//	
//	@Query(value = "select * from MasterCartonViewDto",nativeQuery = true)
//	public List<MasterCartonViewDto> find();
//}

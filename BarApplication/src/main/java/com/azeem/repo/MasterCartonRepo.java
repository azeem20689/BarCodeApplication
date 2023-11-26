package com.azeem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azeem.entities.MasterCarton;
@Repository
public interface MasterCartonRepo extends JpaRepository<MasterCarton, Integer>{

	
}

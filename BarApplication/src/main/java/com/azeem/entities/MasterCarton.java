package com.azeem.entities;

import java.sql.Timestamp;
import java.time.Instant;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
//@Table(schema = "data")
public class MasterCarton {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int key;
	private String color;
	private String commodity;
	private boolean deleted=false;
	private String ean;
	private String model;
	private String netweight;
	private String podate;
	private String pono;
	private String templateid;
	private int totalcarton;
	private String userid;
	private Timestamp timestamp=Timestamp.from(Instant.now());
	private String mfrname;
	
}

package com.azeem.dto;

import java.sql.Timestamp;
import java.time.Instant;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MasterCartonViewDto {
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
	private int lastcarton;
	private String mfrname;
}

package com.azeem.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Master_Carton_Detail_View_Dto {
	
	private String key;
	private String color;
	private String commodity;
	private boolean deleted;
	private String ean;
	private String model;
	private String netweight;
	private String podate;
	private String pono;
	private String templateid;
	private Timestamp timestamp;
	private int totalcarton;
	private String userid;
	private int id;
	private String barcodelist;
	private int cartonno;
	private String grossweight;
	private String msn;
	private String quantity;
	
	
}

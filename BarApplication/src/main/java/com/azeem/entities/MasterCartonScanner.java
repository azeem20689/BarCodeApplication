package com.azeem.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Table(schema = "data")
public class MasterCartonScanner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int cartonno;
	private String grossweight;
	private String msn;
	private String quantity;
	private String barcodelist;
	private int key;
	

}

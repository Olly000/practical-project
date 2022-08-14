package com.qa.persistence;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bands")
public class Band {
	
	public Band(String bandName, String genre, int yearFormed) {
		this.bandName = bandName;
		this.genre = genre;
		this.yearFormed = yearFormed;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="band_id")
	private Long bandId;

	
	@Column(nullable=false)
	private String bandName;
	
	@Column
	private String genre;
	
	@Column
	private int yearFormed;
	
}

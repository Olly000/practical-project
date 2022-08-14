package com.qa.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BandDTO {

	private String name;
	
	private String genre;
	
	private int yearFormed;
	
	public BandDTO(Band band) {
		this.name = band.getBandName();
		this.genre = band.getGenre();
		this.yearFormed = band.getYearFormed();
	}

}

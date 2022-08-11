package com.qa.persistence;

import java.util.Set;

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
	
	private Set<Recording> recordings;
	
	private Set<Musician> musicians;
	
	public BandDTO(Band band) {
		this.name = band.getBandName();
		this.genre = band.getGenre();
		this.yearFormed = band.getYearFormed();
		this.recordings = band.getRecordings();
		this.musicians = band.getMusicians();
	}

}

package com.qa.persistence;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
	
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicianDTO {

	
	private String fullName;
	
	private String instrument;
	
	private Set<Recording> recordings;
	
	private Set<Band> bands;

	public MusicianDTO(Musician musician) {
		this.fullName = musician.getFullName();
		this.instrument = musician.getInstrument();
		this.recordings = musician.getRecordings();
		this.bands = musician.getBands();
	}

}

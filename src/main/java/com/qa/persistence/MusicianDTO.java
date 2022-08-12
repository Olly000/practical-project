package com.qa.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
	
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicianDTO {

	
	private String fullName;
	
	private String instrument;

	public MusicianDTO(Musician musician) {
		this.fullName = musician.getFullName();
		this.instrument = musician.getInstrument();
	}

}

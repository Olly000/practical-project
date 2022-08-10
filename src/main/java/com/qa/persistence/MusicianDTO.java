package com.qa.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
	
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicianDTO {

	
	private String forename;
	
	private String surname;
	
	private String instrument;

	public MusicianDTO(Musician musician) {
		this.forename = musician.getForename();
		this.surname = musician.getSurname();
		this.instrument = musician.getInstrument();
	}

}

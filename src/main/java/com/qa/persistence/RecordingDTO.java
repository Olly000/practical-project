package com.qa.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordingDTO {

	private String title;
	
	private Band band;
	
	private String label;
	
	private int releaseYear;

	public RecordingDTO(Recording recording) {
		this.title = recording.getTitle();
		this.band = recording.getBand(); // TODO: Get from name input
		this.label = recording.getLabel();
		this.releaseYear = recording.getReleaseYear();
	}
	
	

}

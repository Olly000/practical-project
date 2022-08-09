package com.qa.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordingDTO {

	private String title;
	
	private int bandId;
	
	private String label;
	
	private int releaseYear;
	
	private String linkToCover;

	public RecordingDTO(Recording recording) {
		this.title = recording.getTitle();
		this.bandId = recording.getBandId(); // TODO: Get from name input
		this.label = recording.getLabel();
		this.releaseYear = recording.getReleaseYear();
		this.linkToCover = recording.getLinkToCover();
	}

}

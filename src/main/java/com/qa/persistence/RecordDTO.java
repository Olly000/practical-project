package com.qa.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO {

	private String title;
	
	private int bandId;
	
	private String label;
	
	private int releaseYear;
	
	private String linkToCover;

	public RecordDTO(Record record) {
		this.title = record.getTitle();
		this.bandId = record.getBandId(); // TODO: Get from name input
		this.label = record.getLabel();
		this.releaseYear = record.getReleaseYear();
		this.linkToCover = record.getLinkToCover();
	}

}

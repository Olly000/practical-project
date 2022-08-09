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
		
		private boolean active;
		
		public BandDTO(Band band) {
			this.name = band.getName();
			this.genre = band.getGenre();
			this.yearFormed = band.getYearFormed();
			this.active = band.isActive();
		}


}

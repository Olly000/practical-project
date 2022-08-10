package com.qa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;


import com.qa.exceptions.NoDeleteException;
import com.qa.persistence.Band;
import com.qa.persistence.BandDTO;
import com.qa.persistence.Musician;
import com.qa.persistence.MusicianDTO;
import com.qa.repositories.BandRepository;
import com.qa.runner.PracticalProjectApplication;

@SpringBootTest(classes=PracticalProjectApplication.class)
public class BandServiceTests {
	
	@Autowired
	BandService service;
	
	@MockBean
	BandRepository repo;
	
	@Test
	public void testCreate() {
		Band Band = new Band();
		
		Mockito.when(repo.save(Band)).thenReturn(Band);
		BandDTO BandDTO = new BandDTO(Band);
		
		Assertions.assertEquals(service.addBand(Band), BandDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(Band);
		
	}
	
	@Test
	public void testGetOneBand() throws NotFoundException {
		Long id = 1L;
		Optional<Band> band = Optional.of(new Band());
		
		Mockito.when(repo.findById(id)).thenReturn(band);
		BandDTO bandDTO = new BandDTO(band.get());
		
		Assertions.assertEquals(service.getOneBand(id), bandDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	public void testGetAllBands() {
		List<Band> current = new ArrayList<>();
		Band band1 = new Band("Throwing Muses", "indie", 1984, true);
		Band band2 = new Band("Mogwai", "post-rock", 1995, true);
		current.add(band1);
		current.add(band2);
		
		Mockito.when(repo.findAll()).thenReturn(current);
		
		List<BandDTO> currentDTO = new ArrayList<>();
		BandDTO bandDTO1 = new BandDTO("Throwing Muses", "indie", 1984, true);
		BandDTO bandDTO2 = new BandDTO("Mogwai", "post-rock", 1995, true);
		currentDTO.add(bandDTO1);
		currentDTO.add(bandDTO2);
		
		
		Assertions.assertEquals(currentDTO, service.getAllBands());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testUpdateBand() throws NotFoundException {
		Band newBand = new Band("Throwing Muses", "indie", 1984, true);
		Optional<Band> storedBand = Optional.of(new Band("Throwing Muses", "indie", 1990, true));
		Long id = 1L;
		
		Mockito.when(repo.findById(id)).thenReturn(storedBand);
		Mockito.when(repo.save(newBand)).thenReturn(newBand);
		
		BandDTO newBandDTO = service.mapToDTO(newBand);
		
		Assertions.assertEquals(newBandDTO, service.updateBand(id, newBand));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(newBand);
	}
	
	@Test
	public void testDeleteBand() throws NoDeleteException {
		Long id = 1L;
		Mockito.doNothing().when(repo).deleteById(id);
		Mockito.when(repo.existsById(id)).thenReturn(false);
		
		Assertions.assertEquals(service.deleteBand(id), true);
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

}

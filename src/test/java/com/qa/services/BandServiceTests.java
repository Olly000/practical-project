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
		Optional<Band> Band = Optional.of(new Band());
		
		Mockito.when(repo.findById(id)).thenReturn(Band);
		BandDTO BandDTO = new BandDTO(Band.get());
		
		Assertions.assertEquals(service.getOneBand(id), BandDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	public void testGetAllBands() {
		List<Band> current = new ArrayList<>();
		Band band1 = new Band();
		Band band2 = new Band();
		current.add(band1);
		current.add(band2);
		
		Mockito.when(repo.findAll()).thenReturn(current);
		
		
	}
	
//	@Test
//	public void testUpdateBand() {
//		
//	}
	
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

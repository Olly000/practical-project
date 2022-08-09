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
import com.qa.persistence.Musician;
import com.qa.persistence.MusicianDTO;
import com.qa.repositories.MusicianRepository;
import com.qa.runner.PracticalProjectApplication;

@SpringBootTest(classes=PracticalProjectApplication.class)
public class MusicianServiceTests {
	
	@Autowired
	MusicianService service;
	
	@MockBean
	MusicianRepository repo;
	
	@Test
	public void testCreate() {
		Musician Musician = new Musician();
		
		Mockito.when(repo.save(Musician)).thenReturn(Musician);
		MusicianDTO MusicianDTO = new MusicianDTO(Musician);
		
		Assertions.assertEquals(service.addMusician(Musician), MusicianDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(Musician);
		
	}
	
	@Test
	public void testGetOneMusician() throws NotFoundException {
		Long id = 1L;
		Optional<Musician> musician = Optional.of(new Musician());
		
		Mockito.when(repo.findById(id)).thenReturn(musician);
		MusicianDTO musicianDTO = new MusicianDTO(musician.get());
		
		Assertions.assertEquals(service.getOneMusician(id), musicianDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	public void testGetAllMusicians() {
		List<Musician> current = new ArrayList<>();
		Musician musician1 = new Musician("Britt", "Walford", "drums");
		Musician musician2 = new Musician("Kim", "Deal", "vocals");
		current.add(musician1);
		current.add(musician2);
		
		Mockito.when(repo.findAll()).thenReturn(current);
		
		
	}
	
//	@Test
//	public void testUpdateMusician() {
//		
//	}
	
	@Test
	public void testDeleteMusician() throws NoDeleteException {
		Long id = 1L;
		Mockito.doNothing().when(repo).deleteById(id);
		Mockito.when(repo.existsById(id)).thenReturn(false);
		
		Assertions.assertEquals(service.deleteMusician(id), true);
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

}

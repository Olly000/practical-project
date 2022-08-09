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
import com.qa.persistence.Recording;
import com.qa.persistence.RecordingDTO;
import com.qa.repositories.RecordingRepository;
import com.qa.runner.PracticalProjectApplication;

@SpringBootTest(classes=PracticalProjectApplication.class)
public class RecordingServiceTests {
	
	@Autowired
	RecordingService service;
	
	@MockBean
	RecordingRepository repo;
	
	@Test
	public void testCreate() {
		Recording recording = new Recording();
		
		Mockito.when(repo.save(recording)).thenReturn(recording);
		RecordingDTO recordingDTO = new RecordingDTO(recording);
		
		Assertions.assertEquals(service.addRecording(recording), recordingDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(recording);
		
	}
	
	@Test
	public void testGetOneRecording() throws NotFoundException {
		Long id = 1L;
		Optional<Recording> recording = Optional.of(new Recording());
		
		Mockito.when(repo.findById(id)).thenReturn(recording);
		RecordingDTO recordingDTO = new RecordingDTO(recording.get());
		
		Assertions.assertEquals(service.getOneRecording(id), recordingDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	public void testGetAllRecordings() {
		List<Recording> current = new ArrayList<>();
		Recording recording1 = new Recording();
		Recording recording2 = new Recording();
		current.add(recording1);
		current.add(recording2);
		
		Mockito.when(repo.findAll()).thenReturn(current);
		
		
	}
	
//	@Test
//	public void testUpdateRecording() {
//		
//	}
	
	@Test
	public void testDeleteRecording() throws NoDeleteException {
		Long id = 1L;
		Mockito.doNothing().when(repo).deleteById(id);
		Mockito.when(repo.existsById(id)).thenReturn(false);
		
		Assertions.assertEquals(service.deleteRecording(id), true);
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

}

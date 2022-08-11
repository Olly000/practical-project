package com.qa.services;

import java.util.ArrayList;
import java.util.Collections;
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
import com.qa.persistence.Musician;
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
		Band band1 = new Band("Throwing Muses", "indie", 1984);
		Band band2 = new Band("Mogwai", "post-rock", 1995);
		List<Recording> current = new ArrayList<>();
		Recording recording1 = new Recording("University", band1, "4AD", 1990);
		Recording recording2 = new Recording("Young Team", band2, "Chemikal", 1997);
		current.add(recording1);
		current.add(recording2);
		
		Mockito.when(repo.findAll()).thenReturn(current);
		
		List<RecordingDTO> currentDTO = new ArrayList<>();
		RecordingDTO recordingDTO1 = new RecordingDTO("University", band1, "4AD", 1990, Collections.<Musician>emptySet());
		RecordingDTO recordingDTO2 = new RecordingDTO("Young Team", band2, "Chemikal", 1997, Collections.<Musician>emptySet());
		currentDTO.add(recordingDTO1);
		currentDTO.add(recordingDTO2);
		
		Assertions.assertEquals(currentDTO, service.getAllRecordings());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testUpdateRecording() throws NotFoundException {
		Band band = new Band("Mogwai", "post-rock", 1995);
		Recording newRecording = new Recording("Young Team", band, "Chemikal", 1997);
		Optional<Recording> storedRecording = Optional.of(new Recording("Young Team", band, "Chemikal Underground", 1997));
		Long id = 1L;
		
		Mockito.when(repo.findById(id)).thenReturn(storedRecording);
		Mockito.when(repo.save(newRecording)).thenReturn(newRecording);
		
		RecordingDTO newRecordingDTO = service.mapToDTO(newRecording);
		
		Assertions.assertEquals(newRecordingDTO, service.updateRecording(id, newRecording));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(newRecording);
	}
	
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

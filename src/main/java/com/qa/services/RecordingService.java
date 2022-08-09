package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.qa.exceptions.NoDeleteException;
import com.qa.persistence.Recording;
import com.qa.persistence.RecordingDTO;
import com.qa.repositories.RecordingRepository;



@Service
public class RecordingService {
	
private RecordingRepository repo;

private ModelMapper mapper;

	public RecordingService(RecordingRepository repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	// convert recording entity to recordingDTO class
	public RecordingDTO mapToDTO(Recording recording) {
		return this.mapper.map(recording, RecordingDTO.class);
	}
	
	// create
	public RecordingDTO addRecording(Recording recording) {
		return this.mapToDTO(this.repo.save(recording));
	}
	
	// read
	public List<RecordingDTO> getAllrecordings() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public RecordingDTO getOneRecording(Long id) throws NotFoundException {
		return this.mapToDTO(repo.findById(id).orElseThrow(NotFoundException::new));
	}
	
	// update
	public RecordingDTO updaterecording(Long id, Recording recording) throws NotFoundException {
		Recording existingRecording = this.repo.findById(id).orElseThrow(NotFoundException::new);
		if(existingRecording != null) {
			existingRecording.setTitle(recording.getTitle());
			existingRecording.setBandId(recording.getBandId());
			existingRecording.setLabel(recording.getLabel());
			existingRecording.setReleaseYear(recording.getReleaseYear());
			existingRecording.setLinkToCover(recording.getLinkToCover());
			
			
		} else {
			return this.addRecording(recording);
		}
		return this.mapToDTO(this.repo.save(existingRecording));
	}
	
	//delete
	public boolean deleteRecording(Long id) throws NoDeleteException {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		if(exists) {
			throw new NoDeleteException();
		}
		return !exists;
	}
}
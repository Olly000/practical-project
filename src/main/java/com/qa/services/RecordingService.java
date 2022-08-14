package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.qa.exceptions.NoDeleteException;
import com.qa.persistence.Band;
import com.qa.persistence.Recording;
import com.qa.persistence.RecordingDTO;
import com.qa.repositories.BandRepository;
import com.qa.repositories.RecordingRepository;



@Service
public class RecordingService {
	
	@Autowired
	private RecordingRepository repo;
	
	private BandRepository bandRepo;
	
	private BandService bandService;
	
	private ModelMapper mapper;

	public RecordingService(RecordingRepository repo, ModelMapper mapper, BandRepository bandRepo, BandService bandService) {
		super();
		this.repo = repo;
		this.mapper = mapper;
		this.bandRepo = bandRepo;
		this.bandService = bandService;
	}
	
	// convert recording entity to recordingDTO class
	public RecordingDTO mapToDTO(Recording recording) {
		return this.mapper.map(recording, RecordingDTO.class);
	}
	
	public Long idFromTitle(String title) throws NotFoundException {
		Recording recording = repo.findByTitle(title);
		return recording.getRecordingId();
	}
	
	// this method removes the value of the band property of the recording entity so that the recording can
	// be deleted without violating key constraints
	public void removeBand(Long id) {
		Recording recording = repo.findById(id).get();
		recording.setBand(null);
		repo.save(recording);
	}
	
	// create
	public RecordingDTO addRecording(Recording recording) {
		System.out.println(recording);
		if(recording.getBand().getBandId() == 0) {
			Band newBand = new Band(null, recording.getBand().getBandName(), recording.getBand().getGenre(), recording.getBand().getYearFormed());
			bandRepo.save(newBand);
		}
		return this.mapToDTO(this.repo.save(recording));
	}
	
	// read
	public List<RecordingDTO> getAllRecordings() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public RecordingDTO getOneRecording(Long id) throws NotFoundException {
		return this.mapToDTO(repo.findById(id).orElseThrow(NotFoundException::new));
	}
	
	// update
	public RecordingDTO updateRecording(Recording recording) throws NotFoundException {
		Recording existingRecording = this.repo.findByTitle(recording.getTitle());
		if(existingRecording != null) {
			existingRecording.setTitle(recording.getTitle());
			existingRecording.setBand(recording.getBand());
			existingRecording.setLabel(recording.getLabel());
			existingRecording.setReleaseYear(recording.getReleaseYear());
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
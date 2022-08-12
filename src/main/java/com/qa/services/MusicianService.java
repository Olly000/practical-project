package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.qa.exceptions.NoDeleteException;
import com.qa.persistence.Musician;
import com.qa.persistence.MusicianDTO;
import com.qa.repositories.MusicianRepository;

@Service
public class MusicianService {
	
	@Autowired
	private MusicianRepository repo;
	
	private ModelMapper mapper;

	public MusicianService(MusicianRepository repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	// convert Musician entity to MusicianDTO class
	public MusicianDTO mapToDTO(Musician musician) {
		return this.mapper.map(musician, MusicianDTO.class);
	}
	
	public Long idFromName(String fullName) throws NotFoundException { // TODO - Ask A about this
		Musician musician = repo.findByName(fullName);
		return musician.getMusicianId();
	}
	
	// create
	public MusicianDTO addMusician(Musician musician) {
		return this.mapToDTO(this.repo.save(musician));
	}
	
	// read
	public List<MusicianDTO> getAllMusicians() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public MusicianDTO getOneMusician(Long id) throws NotFoundException {
		return this.mapToDTO(repo.findById(id).orElseThrow(NotFoundException::new));
	}
	
	// update
	public MusicianDTO updateMusician(Long id, Musician musician) throws NotFoundException {
		Musician existingMusician = this.repo.findById(id).orElseThrow(NotFoundException::new);
		if(existingMusician != null) {
			existingMusician.setFullName(musician.getFullName());
			existingMusician.setInstrument(musician.getInstrument());
		} else {
			return this.addMusician(musician);
		}
		return this.mapToDTO(this.repo.save(existingMusician));
	}
	
	//delete
	public boolean deleteMusician(Long id) throws NoDeleteException {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		if(exists) {
			throw new NoDeleteException();
		}
		return !exists;
	}

}
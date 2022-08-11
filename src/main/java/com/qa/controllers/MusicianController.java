package com.qa.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.exceptions.NoDeleteException;
import com.qa.persistence.Musician;
import com.qa.persistence.MusicianDTO;
import com.qa.services.MusicianService;

@Service
@RestController
public class MusicianController {
	
	private MusicianService service;

	public MusicianController(MusicianService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/addMusician")
	public MusicianDTO add(@RequestBody Musician musician) {
		return service.addMusician(musician);
	}
	
	@GetMapping("/getAllMusicians")
	public List<MusicianDTO> getAll() {
		return service.getAllMusicians();
	}
	
	@GetMapping("/getOneMusician")
	public MusicianDTO getOne(@PathParam("fullName") String fullName) throws NotFoundException {
		Long id = service.idFromName(fullName);
		return service.getOneMusician(id);
	}
	
	@PostMapping("/updateMusician")
	public MusicianDTO update(@RequestBody Musician musician) throws NotFoundException {
		return service.updateMusician(musician.getMusicianId(), musician);
	}
	
	@PostMapping("/deleteMusician")
	public boolean delete(@PathParam("fullName") String fullName) throws NoDeleteException, NotFoundException {
		Long id = service.idFromName(fullName);
		return service.deleteMusician(id);
	}

}

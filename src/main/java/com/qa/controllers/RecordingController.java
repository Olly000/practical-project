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
import com.qa.persistence.Recording;
import com.qa.persistence.RecordingDTO;
import com.qa.services.RecordingService;

@Service
@RestController
public class RecordingController {
	
	private RecordingService service;
	
	public RecordingController(RecordingService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/recordings")
	public String recordingHome() {
		return "<h1>This is the Recordings homepage</h1>";
	}
	
	@PostMapping("/addRecording")
	public RecordingDTO add(@RequestBody Recording recording) {
		
		return service.addRecording(recording);
	}
	
	@GetMapping("/getAllRecordings")
	public List<RecordingDTO> getAll() {
		return service.getAllRecordings();
	}
	
	@GetMapping("/getOneRecording")
	public RecordingDTO getOne(@PathParam("title") String title) throws NotFoundException {
		Long id = service.idFromTitle(title);
		return service.getOneRecording(id);
	}
	
	@PostMapping("/updateRecording")
	public RecordingDTO update(@RequestBody Recording recording) throws NotFoundException {
		return service.updateRecording(recording);
	}
	
	@PostMapping("/deleteRecording")
	public boolean delete(@PathParam("title") String title) throws NoDeleteException, NotFoundException {
		Long id = service.idFromTitle(title);
		service.removeBand(id); 
		return service.deleteRecording(id);
	}

}
package com.qa.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.persistence.Recording;
import com.qa.persistence.RecordingDTO;
import com.qa.runner.PracticalProjectApplication;


@SpringBootTest(classes = {PracticalProjectApplication.class, RecordingController.class})
@AutoConfigureMockMvc
public class RecordingControllerTest {

	

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	private RecordingDTO mapToDTO(Recording recording) {
        return this.mapper.map(recording, RecordingDTO.class);
    }

    @Autowired
    private ObjectMapper jsonifier;
    
    private final Long TEST_ID = 55L;
    private final Recording TEST_RECORDING = new Recording ();

}

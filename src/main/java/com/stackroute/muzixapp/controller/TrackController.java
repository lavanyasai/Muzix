package com.stackroute.muzixapp.controller;

import com.stackroute.muzixapp.mapper.TrackMapper;
import com.stackroute.muzixapp.dto.TrackDTO;
import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.service.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TrackController {

	@Autowired
	private final TrackService trackService;

	@Autowired
	private final TrackMapper trackMapper;

	@PostMapping("track")
	public ResponseEntity<TrackDTO> addTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
		ResponseEntity responseEntity;
		responseEntity = new ResponseEntity<TrackDTO>(trackMapper.toTrackDTO(trackService.saveTrack(track)), HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("track")
	public ResponseEntity<List<TrackDTO>> indexPage(ModelMap model) {
		ResponseEntity responseEntity;
		List<TrackDTO> trackList = trackMapper.toTrackDTOs(trackService.getAllTracks());
		model.addAttribute("trackList", trackList);
		responseEntity = new ResponseEntity<List<TrackDTO>>(trackList, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("track/{name}")
	public ResponseEntity<List<TrackDTO>> findByName(@PathVariable(value = "name") String name, ModelMap model) {
		ResponseEntity responseEntity;
		List<TrackDTO> trackList = trackMapper.toTrackDTOs(trackService.findByName(name));
		model.addAttribute("trackList", trackList);
		responseEntity = new ResponseEntity<List<TrackDTO>>(trackList, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("track/{id}")
	public ResponseEntity<TrackDTO> deleteTrack(@PathVariable(name = "id") int id) throws TrackNotFoundException {
		ResponseEntity responseEntity;
		responseEntity = new ResponseEntity<TrackDTO>(trackMapper.toTrackDTO(trackService.deleteTrack(id)), HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("track/{id}")
	public ResponseEntity<TrackDTO> updateTrack(@PathVariable(name = "id") int id, @RequestBody Track track) throws TrackNotFoundException {
		ResponseEntity responseEntity;
		responseEntity = new ResponseEntity<TrackDTO>(trackMapper.toTrackDTO(trackService.updateTrack(track)), HttpStatus.OK);
		return responseEntity;
	}

}
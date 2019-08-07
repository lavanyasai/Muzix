package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

	private TrackRepository trackRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	public TrackServiceImpl(TrackRepository trackRepository) {
		this.trackRepository = trackRepository;
	}

	//Save a track.
	@Override
	public Track saveTrack(Track track) throws TrackAlreadyExistsException {
		Track savedTrack = null;
		if(!trackRepository.findById(track.getId()).isPresent()) {
			savedTrack = trackRepository.save(track);
		}
		else {
			throw new TrackAlreadyExistsException();
		}
		return savedTrack;
	}

	//Delete track based on the specified ID.
	@Override
	public boolean deleteTrack(int id) throws TrackNotFoundException {
		boolean result = false;
		if(!trackRepository.findById(id).isPresent()) {
			throw new TrackNotFoundException();
		}
		else {
			result = true;
			trackRepository.delete(getTrackById(id));
		}
		return result;
	}

	//Get all tracks.
	@Override
	public List<Track> getAllTracks() {
		return trackRepository.findAll();
	}

	@Override
	public List<Track> findByName(String name) throws TrackNotFoundException {
		if(!(trackRepository.findByName(name).size() > 0))
			throw new TrackNotFoundException();
		return trackRepository.findByName(name);
	}

	//Get track based on the specified ID.
	@Override
	public Track getTrackById(int id) throws TrackNotFoundException {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Track savedTrack = mongoTemplate.findOne(query, Track.class);
		if(savedTrack == null) {
			throw new TrackNotFoundException();
		}
		return savedTrack;
	}

	//Update track with given body.
	@Override
	public Track updateTrack(Track track) throws TrackNotFoundException {
		Track result = null;
		if(trackRepository.findById(track.getId()).isPresent()) {
			result = trackRepository.save(track);
		}
		else {
			throw new TrackNotFoundException();
		}
		return result;
	}

}

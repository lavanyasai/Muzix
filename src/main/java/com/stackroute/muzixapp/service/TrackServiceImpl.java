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

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

	private TrackRepository trackRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private Sequence sequence;

	@Autowired
	public TrackServiceImpl(TrackRepository trackRepository) {
		this.trackRepository = trackRepository;
	}

	@Override
	public Track saveTrack(Track track) throws TrackAlreadyExistsException {
		Track savedTrack = null;
		track.setId(sequence.getNextSequenceId(Track.SEQUENCE_NAME));
		track.setCreatedAt(LocalDateTime.now());
		if(!trackRepository.findById(track.getId()).isPresent()) {
			savedTrack = trackRepository.save(track);
		}
		else {
			throw new TrackAlreadyExistsException();
		}
		return savedTrack;
	}

	@Override
	public Track deleteTrack(int id) throws TrackNotFoundException {
		Track deletedTrack = null;
		if(!trackRepository.findById(id).isPresent()) {
			throw new TrackNotFoundException();
		}
		else {
			deletedTrack = getTrackById(id);
			trackRepository.delete(deletedTrack);
		}
		return deletedTrack;
	}

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

	@Override
	public Track getTrackById(int id) throws TrackNotFoundException {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Track savedTrack = mongoTemplate.findOne(query, Track.class);
		System.out.println(savedTrack);
		if(savedTrack == null) {
			throw new TrackNotFoundException();
		}
		return savedTrack;
	}

	@Override
	public Track updateTrack(Track track) throws TrackNotFoundException {
		Track updatedTrack = null;
		track.setId(sequence.getNextSequenceId(Track.SEQUENCE_NAME));
		if(trackRepository.findById(track.getId()).isPresent()) {
			updatedTrack = trackRepository.save(track);
		}
		else {
			throw new TrackNotFoundException();
		}
		return updatedTrack;
	}

}

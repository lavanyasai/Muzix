package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

	private TrackRepository trackRepository;

	@Autowired
	public TrackServiceImpl(TrackRepository trackRepository) {
		this.trackRepository = trackRepository;
	}

	@Override
	public Track saveTrack(Track track) {
		Track savedTrack = trackRepository.save(track);
		if(savedTrack == null) {
            savedTrack = null;
		}
		return savedTrack;
	}

	@Override
	public Track deleteTrack(int id) {
		Track result = null;
		if(!trackRepository.findById(id).isPresent()) {
			result = null;
		}
		else {
			trackRepository.delete(getTrackById(id));
			result = getTrackById(id);
		}
		return result;
	}

	@Override
	public List<Track> getAllTracks() {
		return trackRepository.findAll();
	}

	@Override
	public Track getTrackById(int id) {
		Track savedTrack = trackRepository.getOne(id);
		return savedTrack;
	}

	@Override
	public Track updateTrack(Track track) {
		return null;
	}

}

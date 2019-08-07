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

	//Save a track.
	@Override
	public Track saveTrack(Track track) {
		boolean result = false;
		Track savedTrack = trackRepository.save(track);
		if(savedTrack != null) {
			result = true;
		}
		return savedTrack;
	}

	//Delete track based on the specified ID.
	@Override
	public Track deleteTrack(int id) {
		boolean result = false;
		if(!trackRepository.findById(id).isPresent()) {
			result = false;
		}
		else {
			trackRepository.delete(getTrackById(id));
		}
		return getTrackById(id);
	}

	//Get all tracks.
	@Override
	public List<Track> getAllTracks() {
		return trackRepository.findAll();
	}

	//Get track based on the specified ID.
	@Override
	public Track getTrackById(int id) {
		Track savedTrack = trackRepository.getOne(id);
		return savedTrack;
	}

	//Update track with given body.
	@Override
	public Track updateTrack(Track track) {
		boolean result = false;
		Track savedTrack = trackRepository.getOne(track.getId());
		savedTrack.setName(track.getName());
		savedTrack.setComment(track.getComment());
		trackRepository.save(savedTrack);
		if(savedTrack != null) {
			result = true;
		}
		return savedTrack;
	}

}

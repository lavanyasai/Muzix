package com.stackroute.muzixapp.service;


import java.util.List;

import com.stackroute.muzixapp.model.Track;

public interface TrackService {

	public Track saveTrack(Track track);

	public Track deleteTrack(int id);

	public List<Track> getAllTracks();

	public Track getTrackById(int id);

	public Track updateTrack(Track track);

}
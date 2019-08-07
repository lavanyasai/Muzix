package com.stackroute.muzixapp.service;


import java.util.List;

import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.model.Track;

public interface TrackService {

	public Track saveTrack(Track track) throws TrackAlreadyExistsException;

	public Track deleteTrack(int id) throws TrackNotFoundException;

	public List<Track> getAllTracks();

	public Track getTrackById(int id) throws TrackNotFoundException;

	public Track updateTrack(Track track) throws TrackNotFoundException;

	public List<Track> findByName(String Name) throws TrackNotFoundException;

}
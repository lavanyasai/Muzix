package com.stackroute.muzixapp;

import com.stackroute.muzixapp.model.Image;
import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.service.Sequence;
import com.stackroute.muzixapp.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class TrackApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TrackService trackService;

    @Autowired
    private Sequence sequence;

    public TrackApplicationListener(TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Track track = new Track(sequence.getNextSequenceId(Track.SEQUENCE_NAME), "A", "A", new ArrayList<Image>(), false, null);
        try {
            trackService.saveTrack(track);
            for(Track allTracks : trackService.getAllTracks()) {
                System.out.println(allTracks.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

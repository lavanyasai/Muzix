package com.stackroute.muzixapp.mapper;

import com.stackroute.muzixapp.dto.TrackDTO;
import com.stackroute.muzixapp.model.Track;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TrackMapper {

    TrackDTO toTrackDTO(Track track);

    List<TrackDTO> toTrackDTOs(List<Track> tracks);

    Track toTrack(TrackDTO productDTO);
}

package com.stackroute.muzixapp.dto;

import com.stackroute.muzixapp.model.Image;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class TrackDTO {

    private String id;
    private String name;
    private String artist;
    private ArrayList<Image> image;
    private Boolean favorite;

    private final LocalDateTime createdAt = LocalDateTime.now();
}

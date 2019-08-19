package com.stackroute.muzixapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Document(collection = "track")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {

	public static final String SEQUENCE_NAME = "sequence";

	@Id
	private int id;
	private String name;
	private String artist;
	private ArrayList<Image> image;
	private Boolean favorite;

	private LocalDateTime createdAt;
}
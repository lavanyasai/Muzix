package com.stackroute.muzixapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "image")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @JsonProperty("#text")
    private String text;
    private String size;
}

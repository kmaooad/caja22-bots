package edu.kmaooad.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/*
* Entity that represents expert
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Expert {

    @JsonProperty()
    private String email;

    @JsonProperty()
    private String type;

    @JsonProperty()
    private String name;

}
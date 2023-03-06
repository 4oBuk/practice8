package com.chornobuk.practice8.celebapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RelatedPerson {

    @JsonProperty("relationship_type_en")
    private String relationshipTypeEn;

    @JsonProperty("date_established")
    private String dateEstablished;

    @JsonProperty("person_en")
    private String personEn;

    @JsonProperty("is_pep")
    private Boolean isPep;

    @JsonProperty("date_finished")
    private String dateFinished;

    @JsonProperty("person_uk")
    private String personUk;

    @JsonProperty("relationship_type")
    private String relationshipType;


    @JsonProperty("date_confirmed")
    private String dateConfirmed;
}

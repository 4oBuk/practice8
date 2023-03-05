package com.chornobuk.practice8.celebapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

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


    private String dateConfirmed;
//    private String fullNameEn;
//
//    private String lastnameEn;
//
//    private Boolean died;
//
//    private String typeOfOfficial;
//
//    private String lastJobTittleEn;
//
//
//    private String url;
//
//    private String lastWorkplace;
//
//    private List<String> names;
//

}

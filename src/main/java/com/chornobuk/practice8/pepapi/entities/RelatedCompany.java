package com.chornobuk.practice8.pepapi.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class RelatedCompany {

    @JsonProperty("relationship_type_en")
    private String relationshipTypeEn;

    @JsonProperty("to_company_short_en")
    private String toCompanyShortEn;

    @JsonProperty("date_established")
    private String dateEstablished;

    @JsonProperty("to_company_edrpou")
    private String toCompanyEdrpou;

    @JsonProperty("to_company_founded")
    private String toCompanyFounded;


    @JsonProperty("date_finished")
    private String dateFinished;

    @JsonProperty("to_company_is_state")
    private Boolean toCompanyIsState;

    @JsonProperty("share")
    private Double share;

    @JsonProperty("date_confirmed")
    private String dateConfirmed;


    @JsonProperty("to_company_uk")
    private String toCompanyUk;

    @JsonProperty("to_company_short_uk")
    private String toCompanyShortUk;

    @JsonProperty("to_company_en")
    private String toCompanyEn;

    @JsonProperty("relationship_type_uk")
    private String relationshipTypeUk;

}

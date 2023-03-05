package com.chornobuk.practice8.celebapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Declaration {

    @JsonProperty("position_en")
    private String positionEn;

    @JsonProperty("url")
    private String url;

    @JsonProperty("income")
    private Double income;

    @JsonProperty("region_uk")
    private String regionUk;

    @JsonProperty("office_en")
    private String officeEn;

    @JsonProperty("position_uk")
    private String positionUk;

    @JsonProperty("year")
    private String year;

    @JsonProperty("office_uk")
    private String officeUk;

    @JsonProperty("region_en")
    private String regionEn;

    @JsonProperty("family_income")
    private String familyIncome;

}

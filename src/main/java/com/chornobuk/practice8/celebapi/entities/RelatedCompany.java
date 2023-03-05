package com.chornobuk.practice8.celebapi.entities;

import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RelatedCompany {
//    @Id
//    private String id;

    private String relationshipTypeEn;

    private String toCompanyShortEn;

    private String dateEstablished;

    private String toCompanyEdrpou;

    private String toCompanyFounded;

    private String dateFinished;

    private Boolean toCompanyIsState;
    private Double share;

    private String dateConfirmed;

    private String toCompanyUk;

    private String toCompanyShortUk;

    private String toCompanyEn;

    private String relationshipTypeUk;

}

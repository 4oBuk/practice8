package com.chornobuk.practice8.celebapi.entities;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Celebrity {
    @Id
    private String id;
    private String lastJobTitle;
    private String lastname;
    private String wikiUk;
    private String photo;
    private String reputationConvictionsEn;
    private String firstNameEn;
    private String lastWorkplaceEn;
    private String names;
    private String fullName;
    private String patronymic;
    private String alsoKnownAsEn;
    private String reputationManhuntUk;
    private String firstName;
    private List<Declaration> declarations;
    private String reputationSanctionsUk;
    private List<RelatedCompany> relatedCompanies;
    private String dateOfBirth;
    private String patronymicEn;
    private String reasonOfTerminationEn;
    private String reputationAssetsEn;
    private List<RelatedPerson> relatedPersons;
    private String reputationConvictionsUk;
    private String reputationCrimesEn;
    private String reasonOfTermination;
    private String fullNameEn;
    private String cityOfBirthUk;
    private String typeOfOfficial;
    private boolean died;
    private String lastNameEn;
    private String lastJobTitleEn;
    private boolean isPep;
    private String reputationManhuntEn;
    private String alsoKnownAsUk;
    private String url;
    private String terminationDateHuman;
    private String lastWorkplace;
    private String cityOfBirthEn;
    private String reputationSanctionsEn;
    private String reputationCrimesUk;
    private String wikiEn;
    private String reputationAssetsUk;
    private String typeOfOfficialEn;
}

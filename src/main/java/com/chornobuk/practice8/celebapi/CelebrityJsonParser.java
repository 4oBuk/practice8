package com.chornobuk.practice8.celebapi;

import com.chornobuk.practice8.celebapi.entities.Celebrity;
import com.chornobuk.practice8.celebapi.entities.Declaration;
import com.chornobuk.practice8.celebapi.entities.RelatedCompany;
import com.chornobuk.practice8.celebapi.entities.RelatedPerson;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CelebrityJsonParser {

    //    todo divide this class into two parts
//    one part will parse Celebrities from json
//    and the second part will write celebrity into mongo
//    I can create async parser that will read a pep
//    return it and the second part will write
//    I can use a point to indicate the end of previous reading
//    but the better way is to create another thread for parser
    private MongoTemplate template;

    public void writeFirmJsonToDB(String path) {
        File data = new File(path);
        JsonFactory factory = new JsonFactory();
        try (JsonParser parser = factory.createParser(data)) {
            if (parser.nextToken() == JsonToken.START_ARRAY) {
                parser.nextToken();
                while (parser.hasCurrentToken()) {
                    Celebrity celebrity = mapCelebrity(parser);
                    parser.nextToken();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Celebrity mapCelebrity(JsonParser parser) throws IOException {
        Celebrity celebrity = new Celebrity();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            if(parser.getCurrentToken() == JsonToken.START_OBJECT) {
                parser.nextToken();
            }
            String field = parser.getCurrentName();
            parser.nextToken();
            switch (field) {
                case "last_job_tittle" -> celebrity.setLastJobTitle(parser.getValueAsString(""));
                case "last_name" -> celebrity.setLastname(parser.getValueAsString(""));
                case "wiki_uk" -> celebrity.setWikiUk(parser.getValueAsString(""));
                case "photo" -> celebrity.setPhoto(parser.getValueAsString(""));
                case "reputation_convictions_en" -> celebrity.setReputationConvictionsEn(parser.getValueAsString(""));
                case "first_name_en" -> celebrity.setFirstNameEn(parser.getValueAsString(""));
                case "last_workplace_en" -> celebrity.setLastWorkplaceEn(parser.getValueAsString(""));
                case "names" -> //                    todo replace with array
                        celebrity.setNames(parser.getValueAsString(""));
                case "full_name" -> celebrity.setFullName(parser.getValueAsString(""));
                case "patronymic" -> celebrity.setPatronymic(parser.getValueAsString(""));
                case "also_known_as_en" -> celebrity.setAlsoKnownAsEn(parser.getValueAsString(""));
                case "reputation_manhunt_uk" -> celebrity.setReputationManhuntUk(parser.getValueAsString(""));
                case "first_name" -> celebrity.setFirstName(parser.getValueAsString(""));
                case "reputation_sanctions_uk" -> celebrity.setReputationSanctionsUk(parser.getValueAsString(""));
                case "date_of_birth" -> celebrity.setDateOfBirth(parser.getValueAsString(""));
                case "patronymic_en" -> celebrity.setPatronymicEn(parser.getValueAsString(""));
                case "reason_of_termination_en" -> celebrity.setReasonOfTerminationEn(parser.getValueAsString(""));
                case "reputation_assets_en" -> celebrity.setReputationAssetsEn(parser.getValueAsString(""));
                case "reputation_convictions_uk" -> celebrity.setReputationConvictionsUk(parser.getValueAsString(""));
                case "reputation_crimes_en" -> celebrity.setReputationCrimesEn(parser.getValueAsString(""));
                case "reason_of_termination" -> celebrity.setReasonOfTermination(parser.getValueAsString(""));
                case "full_name_en" -> celebrity.setFullNameEn(parser.getValueAsString(""));
                case "city_of_birth" -> celebrity.setCityOfBirthUk(parser.getValueAsString(""));
                case "type_of_official" -> celebrity.setTypeOfOfficial(parser.getValueAsString(""));
                case "last_name_en" -> celebrity.setLastNameEn(parser.getValueAsString(""));
                case "last_job_title_en" -> celebrity.setLastJobTitleEn(parser.getValueAsString(""));
                case "reputation_manhunt_en" -> celebrity.setReputationManhuntEn(parser.getValueAsString(""));
                case "also_known_as_uk" -> celebrity.setAlsoKnownAsUk(parser.getValueAsString(""));
                case "url" -> celebrity.setUrl(parser.getValueAsString(""));
                case "termination_date_human" -> celebrity.setTerminationDateHuman(parser.getValueAsString(""));
                case "last_workplace" -> celebrity.setLastWorkplace(parser.getValueAsString(""));
                case "city_of_birth_en" -> celebrity.setCityOfBirthEn(parser.getValueAsString(""));
                case "reputation_sanctions_en" -> celebrity.setReputationSanctionsEn(parser.getValueAsString(""));
                case "reputation_crimes_uk" -> celebrity.setReputationCrimesUk(parser.getValueAsString(""));
                case "wiki_en" -> celebrity.setWikiEn(parser.getValueAsString(""));
                case "reputation_assets_uk" -> celebrity.setReputationAssetsUk(parser.getValueAsString(""));
                case "type_of_official_en" -> celebrity.setTypeOfOfficialEn(parser.getValueAsString(""));
                case "died" -> celebrity.setDied(parser.getBooleanValue());
                case "is_pep" -> celebrity.setPep(parser.getBooleanValue());
                case "declarations" -> {
                    List<Declaration> declarations = new ArrayList<>();
                    while (parser.nextToken() != JsonToken.END_ARRAY) {
                        Declaration declaration = mapDeclaration(parser);
                        declarations.add(declaration);
                    }
                    celebrity.setDeclarations(declarations);
                }
                case "related_companies" -> {
                    List<RelatedCompany> relatedCompanies = new ArrayList<>();
                    while (parser.nextToken() != JsonToken.END_ARRAY) {
                        RelatedCompany relatedCompany = mapRelatedCompany(parser);
                        relatedCompanies.add(relatedCompany);
                    }
                    celebrity.setRelatedCompanies(relatedCompanies);
                }

                case "related_persons" -> {
                    List<RelatedPerson> relatedPeople = new ArrayList<>();
                    while (parser.nextToken() != JsonToken.END_ARRAY) {
                        RelatedPerson relatedPerson = mapRelatedPerson(parser);
                        relatedPeople.add(relatedPerson);
                    }
                    celebrity.setRelatedPersons(relatedPeople);
                }
            }
        }
        System.out.println(celebrity);
        return celebrity;
    }

    private static RelatedCompany mapRelatedCompany(JsonParser parser) throws IOException {
        RelatedCompany relatedCompany = new RelatedCompany();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String field = parser.getCurrentName();
            parser.nextToken();
            switch (field) {
                case "relationship_type_en" -> relatedCompany.setRelationshipTypeEn(parser.getValueAsString(""));
                case "to_company_short_en" -> relatedCompany.setToCompanyShortEn(parser.getValueAsString(""));
                case "date_established" -> relatedCompany.setDateEstablished(parser.getValueAsString(""));
                case "to_company_edrpou" -> relatedCompany.setToCompanyEdrpou(parser.getValueAsString(""));
                case "to_company_founded" -> relatedCompany.setToCompanyFounded(parser.getValueAsString(""));
                case "date_finished" -> relatedCompany.setDateFinished(parser.getValueAsString(""));
                case "to_company_is_state" -> relatedCompany.setToCompanyIsState(parser.getBooleanValue());
                case "share" -> relatedCompany.setShare(parser.getValueAsDouble(0));
                case "to_company_uk" -> relatedCompany.setToCompanyUk(parser.getValueAsString(""));
                case "to_company_short_uk" -> relatedCompany.setToCompanyShortUk(parser.getValueAsString(""));
                case "to_company_en" -> relatedCompany.setToCompanyEn(parser.getValueAsString(""));
                case "relationship_type_uk" -> relatedCompany.setRelationshipTypeUk(parser.getValueAsString(""));
                case "date_confirmed" -> relatedCompany.setDateConfirmed(parser.getValueAsString(""));
            }
        }
        return relatedCompany;
    }

    private static Declaration mapDeclaration(JsonParser parser) throws IOException {
        Declaration declaration = new Declaration();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String field = parser.getCurrentName();
            parser.nextToken();
            switch (field) {
                case "position_en" -> declaration.setPositionEn(parser.getValueAsString(""));
                case "url" -> declaration.setUrl(parser.getValueAsString(""));
                case "income" -> declaration.setIncome(parser.getValueAsDouble(0));
                case "region_uk" -> declaration.setRegionUk(parser.getValueAsString(""));
                case "region_en" -> declaration.setRegionEn(parser.getValueAsString(""));
                case "office_en" -> declaration.setOfficeEn(parser.getValueAsString(""));
                case "office_uk" -> declaration.setOfficeUk(parser.getValueAsString(""));
                case "position_uk" -> declaration.setPositionUk(parser.getValueAsString(""));
                case "year" -> declaration.setYear(parser.getValueAsString(""));
                case "family_income" -> declaration.setFamilyIncome(parser.getValueAsString(""));

            }
        }
        return declaration;
    }

    private static RelatedPerson mapRelatedPerson(JsonParser parser) throws IOException {
        RelatedPerson relatedPerson = new RelatedPerson();
        //        maybe remove next token and only check if token equals end object
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String field = parser.getCurrentName();
            parser.nextToken();
            switch (field) {
                case "relationship_type_en" -> relatedPerson.setRelationshipTypeEn(parser.getValueAsString(""));
                case "date_established" -> relatedPerson.setDateEstablished(parser.getValueAsString(""));
                case "person_en" -> relatedPerson.setPersonEn(parser.getValueAsString(""));
                case "is_pep" -> relatedPerson.setIsPep(parser.getBooleanValue());
                case "date_finished" -> relatedPerson.setDateFinished(parser.getValueAsString(""));
                case "person_uk" -> relatedPerson.setPersonUk(parser.getValueAsString(""));
                case "relationship_type" -> relatedPerson.setRelationshipType(parser.getValueAsString(""));
                case "date_confirmed" -> relatedPerson.setDateConfirmed(parser.getValueAsString(""));
            }
        }
        return relatedPerson;
    }


}

package com.chornobuk.practice8.celebapi.repositories.impl;

import com.chornobuk.practice8.celebapi.dtos.CelebrityQueryDto;
import com.chornobuk.practice8.celebapi.entities.Celebrity;
import com.chornobuk.practice8.celebapi.repositories.CelebrityRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CelebrityRepositoryImpl implements CelebrityRepository {
    private MongoTemplate template;

    @Override
    public Page<Celebrity> findByFullName(CelebrityQueryDto dto) {
//        todo: get only necessary fields

        PageRequest pageRequest = PageRequest.of(dto.getPage(), dto.getPageSize(), Sort.by(Sort.Direction.ASC, Celebrity.Fields.id));
        Query query = new Query();
        query.fields()
                .include(Celebrity.Fields.id)
                .include(Celebrity.Fields.isPep)
                .include(Celebrity.Fields.fullName)
                .include(Celebrity.Fields.fullNameEn)
                .include(Celebrity.Fields.firstName)
                .include(Celebrity.Fields.firstNameEn)
                .include(Celebrity.Fields.lastname)
                .include(Celebrity.Fields.lastNameEn)
                .include(Celebrity.Fields.patronymic)
                .include(Celebrity.Fields.patronymicEn)
                .include(Celebrity.Fields.relatedPersons)
                .include(Celebrity.Fields.relatedCompanies)
                .include(Celebrity.Fields.declarations)
                .include(Celebrity.Fields.typeOfOfficial)
                .include(Celebrity.Fields.typeOfOfficialEn)
                .include(Celebrity.Fields.lastWorkplace)
                .include(Celebrity.Fields.lastWorkplaceEn)
                .include(Celebrity.Fields.lastJobTitle)
                .include(Celebrity.Fields.lastJobTitleEn)
                .include(Celebrity.Fields.died);

        if (StringUtils.isNotBlank(dto.getFirstName())) {
            query.addCriteria(Criteria.where(Celebrity.Fields.firstName).is(dto.getFirstName()));
        }
        if (StringUtils.isNotBlank(dto.getLastname())) {
            query.addCriteria(Criteria.where(Celebrity.Fields.lastname).is(dto.getLastname()));
        }
        if (StringUtils.isNotBlank(dto.getPatronymic())) {
            query.addCriteria(Criteria.where(Celebrity.Fields.patronymic).is(dto.getPatronymic()));
        }
        query.with(pageRequest);
        final List<Celebrity> celebrities = template.find(query, Celebrity.class);

        return PageableExecutionUtils.getPage(
                celebrities,
                pageRequest,
                () -> template.count((Query.of(query).limit(-1).skip(-1)), Celebrity.class)
        );
    }
}

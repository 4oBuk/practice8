package com.chornobuk.practice8.pepapi.repositories.impl;

import com.chornobuk.practice8.pepapi.dtos.PepQueryDto;
import com.chornobuk.practice8.pepapi.entities.Pep;
import com.chornobuk.practice8.pepapi.repositories.PepRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
@AllArgsConstructor
public class PepRepositoryImpl implements PepRepository {
    private MongoTemplate template;

    @Override
    public Page<Pep> findByFullName(PepQueryDto dto) {
        PageRequest pageRequest = PageRequest.of(dto.getPage(), dto.getPageSize(), Sort.by(Sort.Direction.ASC, Pep.Fields.id));
        Query query = new Query();
        query.fields()
                .include(Pep.Fields.id)
                .include(Pep.Fields.isPep)
                .include(Pep.Fields.fullName)
                .include(Pep.Fields.fullNameEn)
                .include(Pep.Fields.firstName)
                .include(Pep.Fields.firstNameEn)
                .include(Pep.Fields.lastname)
                .include(Pep.Fields.lastNameEn)
                .include(Pep.Fields.patronymic)
                .include(Pep.Fields.patronymicEn)
                .include(Pep.Fields.relatedPersons)
                .include(Pep.Fields.relatedCompanies)
                .include(Pep.Fields.declarations)
                .include(Pep.Fields.typeOfOfficial)
                .include(Pep.Fields.typeOfOfficialEn)
                .include(Pep.Fields.lastWorkplace)
                .include(Pep.Fields.lastWorkplaceEn)
                .include(Pep.Fields.lastJobTitle)
                .include(Pep.Fields.lastJobTitleEn)
                .include(Pep.Fields.died);

        if (StringUtils.isNotBlank(dto.getFirstName())) {
            query.addCriteria(Criteria.where(Pep.Fields.firstName).is(dto.getFirstName()));
        }
        if (StringUtils.isNotBlank(dto.getLastname())) {
            query.addCriteria(Criteria.where(Pep.Fields.lastname).is(dto.getLastname()));
        }
        if (StringUtils.isNotBlank(dto.getPatronymic())) {
            query.addCriteria(Criteria.where(Pep.Fields.patronymic).is(dto.getPatronymic()));
        }
        query.with(pageRequest);
        final List<Pep> peps = template.find(query, Pep.class);

        return PageableExecutionUtils.getPage(
                peps,
                pageRequest,
                () -> template.count((Query.of(query).limit(-1).skip(-1)), Pep.class)
        );
    }

    @Override
    public Document getMostPopularPepFirstnames(int sizeOfTop) {

        Criteria criteria = Criteria.where(Pep.Fields.isPep).is(true);
        MatchOperation matchOperation = match(criteria);
        GroupOperation group = group(Pep.Fields.firstName).count().as("namePop");
        SortOperation sort = sort(Sort.by(Sort.Direction.DESC, "namePop"));
        LimitOperation limit = limit(sizeOfTop);
        ProjectionOperation projectToMatchModel = project()
                .andExpression(("_id")).as("name")
                .andExclude("_id")
                .andExpression("namePop").as("popularity");

        Aggregation aggregation = newAggregation(matchOperation, group, sort, limit, projectToMatchModel);

        AggregationResults<Document> result = template.aggregate(aggregation, "pep", Document.class);
        return result.getRawResults();
    }
}

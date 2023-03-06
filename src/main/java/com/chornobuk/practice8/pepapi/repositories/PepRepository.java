package com.chornobuk.practice8.pepapi.repositories;

import com.chornobuk.practice8.pepapi.dtos.PepQueryDto;
import com.chornobuk.practice8.pepapi.entities.Pep;
import org.bson.Document;
import org.springframework.data.domain.Page;


public interface PepRepository {
    Page<Pep> findByFullName(PepQueryDto dto);

    Document getMostPopularPepFirstnames(int sizeOfTop);
}

package com.chornobuk.practice8.pepapi.services;

import com.chornobuk.practice8.pepapi.dtos.PepQueryDto;
import com.chornobuk.practice8.pepapi.entities.Pep;
import org.bson.Document;
import org.springframework.data.domain.Page;


public interface PepService {
    Page<Pep> getByFullName(PepQueryDto dto);

    Document getMostPopularNames();
}

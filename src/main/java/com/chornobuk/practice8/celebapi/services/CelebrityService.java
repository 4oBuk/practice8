package com.chornobuk.practice8.celebapi.services;

import com.chornobuk.practice8.celebapi.dtos.CelebrityQueryDto;
import com.chornobuk.practice8.celebapi.entities.Celebrity;
import org.bson.Document;
import org.springframework.data.domain.Page;


public interface CelebrityService {
    Page<Celebrity> getByFullName(CelebrityQueryDto dto);

    Document getMostPopularNames();
}

package com.chornobuk.practice8.celebapi.repositories;

import com.chornobuk.practice8.celebapi.dtos.CelebrityQueryDto;
import com.chornobuk.practice8.celebapi.entities.Celebrity;
import org.springframework.data.domain.Page;

public interface CelebrityRepository {
    Page<Celebrity> findByFullName(CelebrityQueryDto dto);


}

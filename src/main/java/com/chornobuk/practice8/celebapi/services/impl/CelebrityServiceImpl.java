package com.chornobuk.practice8.celebapi.services.impl;

import com.chornobuk.practice8.celebapi.dtos.CelebrityQueryDto;
import com.chornobuk.practice8.celebapi.entities.Celebrity;
import com.chornobuk.practice8.celebapi.repositories.CelebrityRepository;
import com.chornobuk.practice8.celebapi.services.CelebrityService;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CelebrityServiceImpl implements CelebrityService {

    private CelebrityRepository celebrityRepository;
    private final static int DEFAULT_PAGE_SIZE = 10;
    private final static int DEFAULT_TOP_SIZE = 10;

    @Override
    public Page<Celebrity> getByFullName(CelebrityQueryDto dto) {
        if (dto.getPage() == null) {
            dto.setPage(0);
        }
        if (dto.getPageSize() == null || dto.getPageSize() <= 0) {
            dto.setPageSize(DEFAULT_PAGE_SIZE);
        }
        return celebrityRepository.findByFullName(dto);
    }

    @Override
    public Document getMostPopularNames() {
        return celebrityRepository.getMostPopularPepFirstnames(DEFAULT_TOP_SIZE);
    }
}

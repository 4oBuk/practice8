package com.chornobuk.practice8.pepapi.services.impl;

import com.chornobuk.practice8.pepapi.dtos.PepQueryDto;
import com.chornobuk.practice8.pepapi.entities.Pep;
import com.chornobuk.practice8.pepapi.repositories.PepRepository;
import com.chornobuk.practice8.pepapi.services.PepService;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PepServiceImpl implements PepService {

    private final PepRepository pepRepository;
    private final static int DEFAULT_PAGE_SIZE = 10;
    private final static int DEFAULT_TOP_SIZE = 10;

    @Override
    public Page<Pep> getByFullName(PepQueryDto dto) {
        if (dto.getPage() == null) {
            dto.setPage(0);
        }
        if (dto.getPageSize() == null || dto.getPageSize() <= 0) {
            dto.setPageSize(DEFAULT_PAGE_SIZE);
        }
        return pepRepository.findByFullName(dto);
    }

    @Override
    public Document getMostPopularNames() {
        return pepRepository.getMostPopularPepFirstnames(DEFAULT_TOP_SIZE);
    }
}

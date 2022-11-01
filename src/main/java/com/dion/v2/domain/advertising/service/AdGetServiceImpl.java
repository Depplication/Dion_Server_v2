package com.dion.v2.domain.advertising.service;

import com.dion.v2.domain.advertising.entity.Advertising;
import com.dion.v2.domain.advertising.exception.AdNotFoundException;
import com.dion.v2.domain.advertising.presentation.dto.response.AdListResponse;
import com.dion.v2.domain.advertising.presentation.dto.response.AdResponse;
import com.dion.v2.domain.advertising.repository.AdvertisingRepository;
import com.dion.v2.domain.advertising.type.Category;
import com.dion.v2.global.utils.AdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdGetServiceImpl implements AdGetService{

    private AdvertisingRepository adRepository;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public AdResponse getAd(Long adId) {
        Advertising ad = adRepository.findById(adId)
                .orElseThrow(() -> {throw AdNotFoundException.EXCEPTION;});

        return AdUtil.getAdResponse(ad);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public AdListResponse getList(Pageable pageable) {
        Page<Advertising> adList = adRepository.findAll(pageable);

        return AdListResponse.builder()
                .list(adList.stream().map(AdUtil::getAdResponse).collect(Collectors.toList()))
                .build();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public AdListResponse getAdByTitle(Pageable pageable, String title) {
        Page<Advertising> list =  adRepository.findAllByAdTitleContaining(pageable, title);

        return AdListResponse.builder()
                .list(list.stream().map(AdUtil::getAdResponse).collect(Collectors.toList()))
                .build();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public AdListResponse getAdByCategory(Pageable pageable, Category category) {
        Page<Advertising> list;
        if(category.equals(Category.ALL)) list = adRepository.findAll(pageable);
        else list = adRepository.findAllByCategory(pageable, category);

        return AdListResponse.builder()
                .list(list.stream().map(AdUtil::getAdResponse).collect(Collectors.toList()))
                .build();
    }

}

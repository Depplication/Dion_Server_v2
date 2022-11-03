package com.dion.v2.domain.advertising.presentation;

import com.dion.v2.domain.advertising.presentation.dto.response.AdListResponse;
import com.dion.v2.domain.advertising.presentation.dto.response.AdResponse;
import com.dion.v2.domain.advertising.service.AdGetService;
import com.dion.v2.domain.advertising.type.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Api(tags = "광고 정보")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ad")
public class AdvertisingGetController {

    private final AdGetService adGetService;

    @ApiOperation("광고 ID로 검색하기")
    @GetMapping("/{ad-id}")
    public AdResponse getAd(
            @PathVariable("ad-id") Long adId
    ) {
        return adGetService.getAd(adId);
    }

    @ApiOperation("광고 리스트 불러오기")
    @GetMapping("/")
    public AdListResponse getList(
            Pageable pageable
    ) {
        return adGetService.getList(pageable);
    }

    @ApiOperation("광고 제목으로 검색하기")
    @GetMapping("/t")
    public AdListResponse getAdByTitle(
            @RequestParam("title") String title,
            Pageable pageable
    ) {
        return adGetService.getAdByTitle(pageable, title);
    }

    @ApiOperation("광고 카테고리로 검색하기")
    @GetMapping("/c")
    public AdListResponse getAdByCategory(
            @RequestParam("category") Category category,
            Pageable pageable
    ) {
        return adGetService.getAdByCategory(pageable, category);
    }

}

package com.dion.v2.domain.advertising.presentation;

import com.dion.v2.domain.advertising.presentation.dto.request.CreateAdRequest;
import com.dion.v2.domain.advertising.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "광고")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ad")
public class AdvertisingController {

    private final AdService adService;

    @ApiOperation("광고 등록하기")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createAd(
            @RequestBody CreateAdRequest data
    ) {
        return adService.createAd(data);
    }

    @ApiOperation("광고 삭제하기")
    @DeleteMapping("/{ad-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAd(
            @PathVariable("ad-id") Long adId
    ) {
        adService.deleteAd(adId);
    }

}

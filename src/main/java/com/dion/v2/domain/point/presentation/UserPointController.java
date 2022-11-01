package com.dion.v2.domain.point.presentation;

import com.dion.v2.domain.point.presentation.dto.response.PointResponse;
import com.dion.v2.domain.point.service.UserPointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api("유저 포인트")
@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class UserPointController {

    private final UserPointService userPointService;

    @ApiOperation("유저 포인트 추가 (자동으로 10포인트 적립)")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{ad-id}")
    public void userSavePoint(
            @PathVariable("ad-id") Long adId
    ) {
        userPointService.savePoint(adId);
    }

    @ApiOperation("유저 포인트 조회")
    @GetMapping("/")
    public PointResponse getUserPoint() {
        return userPointService.getPoint();
    }
}

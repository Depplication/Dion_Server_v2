package com.dion.v2.domain.point.presentation;

import com.dion.v2.domain.point.presentation.dto.request.SavePointRequest;
import com.dion.v2.domain.point.presentation.dto.response.PointResponse;
import com.dion.v2.domain.point.service.OwnerPointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api("업주 포인트")
@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class OwnerPointController {

    private final OwnerPointService ownerPointService;

    @ApiOperation("업주 포인트 추가 (추후 결제 변경 예정)")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void ownerSavePoint(
            @RequestBody SavePointRequest request
    ) {
        ownerPointService.savePoint(request);
    }

    @ApiOperation("업주 포인트 조회")
    @GetMapping("/")
    public PointResponse getOwnerPoint() {
        return ownerPointService.getPoint();
    }
}

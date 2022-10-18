package com.dion.v2.domain.point.service;

import com.dion.v2.domain.auth.entity.User;
import com.dion.v2.domain.auth.exception.UserNotFoundException;
import com.dion.v2.domain.auth.facade.UserFacade;
import com.dion.v2.domain.auth.repository.UserRepository;
import com.dion.v2.domain.point.entity.UserPoint;
import com.dion.v2.domain.point.presentation.dto.response.PointResponse;
import com.dion.v2.domain.point.repository.UserPointRepository;
import com.dion.v2.global.utils.PointUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPointServiceImpl implements UserPointService{

    private final UserRepository userRepository;
    private final UserPointRepository userPointRepository;
    private final UserFacade userFacade;

    @Override
    public void savePoint(Long adId) {
        User user = userFacade.queryUser(true);

        UserPoint point = userPointRepository.findByUser(user)
                .orElseThrow(() -> {throw UserNotFoundException.EXCEPTION;});
        point.addPoint(10L);

        //광고 올린 업주 포인트에서 14 포인트 차감

        userPointRepository.save(point);
    }

    @Override
    public PointResponse getPoint() {
        User user = userFacade.queryUser(true);

        UserPoint point = userPointRepository.findByUser(user)
                .orElseThrow(() -> {throw UserNotFoundException.EXCEPTION;});
        return PointUtil.getUserPointResponse(point);
    }
}

package com.dion.v2.domain.point.service;

import com.dion.v2.domain.owner.entity.Owner;
import com.dion.v2.domain.owner.exception.OwnerNotFoundException;
import com.dion.v2.domain.owner.facade.OwnerFacade;
import com.dion.v2.domain.owner.repository.OwnerRepository;
import com.dion.v2.domain.point.entity.OwnerPoint;
import com.dion.v2.domain.point.presentation.dto.request.SavePointRequest;
import com.dion.v2.domain.point.presentation.dto.response.PointResponse;
import com.dion.v2.domain.point.repository.OwnerPointRepository;
import com.dion.v2.global.utils.PointUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerPointServiceImpl implements OwnerPointService{

    private final OwnerRepository ownerRepository;
    private final OwnerPointRepository ownerPointRepository;
    private final OwnerFacade ownerFacade;

    @Override
    public void savePoint(SavePointRequest request) {
        Owner owner = ownerFacade.queryOwner(true);

        OwnerPoint point = ownerPointRepository.findByOwner(owner)
                .orElseThrow(() -> {throw OwnerNotFoundException.EXCEPTION;});
        point.addPoint(request.getPoint());
        ownerPointRepository.save(point);
    }

    @Override
    public PointResponse getPoint() {
        Owner owner = ownerFacade.queryOwner(true);

        OwnerPoint point = ownerPointRepository.findByOwner(owner)
                .orElseThrow(() -> {throw OwnerNotFoundException.EXCEPTION;});
        return PointUtil.getOwnerPointResponse(point);
    }
}

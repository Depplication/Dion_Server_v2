package com.dion.v2.domain.owner.facade;

import com.dion.v2.domain.auth.entity.User;
import com.dion.v2.domain.auth.exception.UserNotFoundException;
import com.dion.v2.domain.owner.entity.Owner;
import com.dion.v2.domain.owner.exception.OwnerNotFoundException;
import com.dion.v2.domain.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OwnerFacade {

    private final OwnerRepository ownerRepository;

    @Transactional(readOnly = true)
    public Owner queryOwner(boolean withPersistence) {
        Owner withoutPersistencce = (Owner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(withPersistence) {
            return ownerRepository.findById(withoutPersistencce.getId())
                    .orElseThrow(() -> OwnerNotFoundException.EXCEPTION);
        }else {
            return withoutPersistencce;
        }
    }

    public Owner queryOwner() {
        return queryOwner(false);
    }

    public Owner queryOwnerById(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> OwnerNotFoundException.EXCEPTION);
    }

}

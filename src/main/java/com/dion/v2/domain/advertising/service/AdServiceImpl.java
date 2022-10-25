package com.dion.v2.domain.advertising.service;

import com.dion.v2.domain.advertising.entity.Advertising;
import com.dion.v2.domain.advertising.presentation.dto.request.CreateAdRequest;
import com.dion.v2.domain.advertising.presentation.dto.response.AdResponse;
import com.dion.v2.domain.advertising.repository.AdvertisingRepository;
import com.dion.v2.domain.advertising.type.Category;
import com.dion.v2.domain.owner.entity.Owner;
import com.dion.v2.domain.owner.facade.OwnerFacade;
import com.dion.v2.domain.product.entity.Product;
import com.dion.v2.domain.product.exception.ProductAccessWrongException;
import com.dion.v2.domain.product.exception.ProductNotCoincidenceException;
import com.dion.v2.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private AdvertisingRepository adRepository;
    private ProductRepository productRepository;
    private OwnerFacade ownerFacade;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createAd(CreateAdRequest data) {
        Owner author = ownerFacade.queryOwner(true);

        Advertising ad = Advertising.builder()
                .adTitle(data.getTitle()).adContent(data.getContent())
                .explain(data.getExplain()).email(data.getEmail())
                .startDate(data.getStartDate()).endDate(data.getEndDate())
                .build();
        author.addAdvertising(ad);

        if(!data.getProducts().isEmpty()) {
            data.getProducts().stream().map(id -> {
                Product product = productRepository.findById(id)
                        .orElseThrow(() -> {
                            throw new ProductNotCoincidenceException(id);
                        });

                if(!product.getAuthor().equals(author)) throw ProductAccessWrongException.EXCEPTION;

                ad.addProduct(product);
                return null;
            }).close();
        }

        return ad.getAdId();
    }

    @Override
    public AdResponse getAd(Long adId) {
        return null;
    }

    @Override
    public AdResponse getAdByTitle(String title) {
        return null;
    }

    @Override
    public AdResponse getAdByCategory(Category category) {
        return null;
    }

    @Override
    public void deleteAd(Long adId) {

    }
}

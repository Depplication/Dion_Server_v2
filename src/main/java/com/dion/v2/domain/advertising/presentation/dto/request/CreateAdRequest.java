package com.dion.v2.domain.advertising.presentation.dto.request;

import com.dion.v2.domain.advertising.type.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter @NoArgsConstructor
public class CreateAdRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;

    private String explain;

    private String email;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private Category category;

    private List<Long> products;

}

package com.julook.domain.home.find.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageableInfoDTO {
    private int currentPage;
    private int size;
    private boolean first;
    private boolean last;
    private int totalMakElements;
    private int totalPages;
}
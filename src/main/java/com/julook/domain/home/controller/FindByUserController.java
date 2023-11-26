package com.julook.domain.home.controller;

import com.julook.domain.common.dto.response.ApiResponseDTO;
import com.julook.domain.home.dto.MakInfoDTO;
import com.julook.domain.home.service.FindByUserService;
import com.julook.domain.home.dto.response.FindByUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/makInfo")
public class FindByUserController {
    private final FindByUserService findByUserService;

    @Autowired
    public FindByUserController(FindByUserService findByUserService) {
        this.findByUserService = findByUserService;
    }

    @GetMapping("/findByFeatures")
    public ResponseEntity<ApiResponseDTO<FindByUserResponseDTO>> getAllMaks(
            @RequestParam(value = "sort", defaultValue = "makLikeCount") String sort,
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "category", required = false) List<String> categories
    ) {

        // category가 선택되지 않은 경우 빈 배열 반환
        if (categories == null) {
            categories = new ArrayList<>();
        }

        Page<MakInfoDTO> allMaks = findByUserService.findMakWithPaginationAndSorting(offset, pageable.getPageSize(), sort, categories);
        FindByUserResponseDTO results = FindByUserResponseDTO.builder()
                .recordCounts(allMaks.getTotalElements())
                .makInfo(allMaks)
                .build();

        ApiResponseDTO<FindByUserResponseDTO> response = ApiResponseDTO.<FindByUserResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(results)
                .build();

        return ResponseEntity.ok(response);

    }
}

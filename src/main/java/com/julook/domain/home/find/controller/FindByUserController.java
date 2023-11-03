package com.julook.domain.home.find.controller;

import com.julook.domain.common.dto.response.ApiResponseDTO;
import com.julook.domain.home.find.service.FindByUserService;
import com.julook.domain.home.find.dto.response.FindByUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
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


//    @GetMapping("/all")
//    public List<MakInfoDTO> getAllMakInfo() {
//        return makInfoService.getAllMakInfo();
//    }

    @GetMapping("/userSearch")
    public ResponseEntity<ApiResponseDTO<FindByUserResponseDTO>> getMakInfoListByUserPreferences(
            @RequestParam(value = "lastMakNum", defaultValue = "30000") Long lastMakNum,
            @RequestParam(value = "category", required = false) List<String> categories,
            @RequestParam(value = "sort", required = false, defaultValue = "relevance") String sort,
            @PageableDefault(size = 10, sort = "relevance", direction = Sort.Direction.ASC) Pageable pageable) {

        // category가 선택되지 않은 경우 빈 배열 반환
        if (categories == null) {
            categories = new ArrayList<>();
        }
        // "sort" 값에 따라 정렬 방식을 변경
        if ("highAlcohol".equals(sort)) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc("makAlcoholPercentage")));
        } else if ("lowAlcohol".equals(sort)) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.asc("makAlcoholPercentage")));
        }

        // 서비스 메서드 호출
        FindByUserResponseDTO makInfoList = findByUserService.getMakInfoListByUserPreferences(lastMakNum, categories, sort, pageable);

        ApiResponseDTO<FindByUserResponseDTO> response = ApiResponseDTO.<FindByUserResponseDTO>builder()
                .resultCode(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(makInfoList)
                .build();

        return ResponseEntity.ok(response);
    }
}

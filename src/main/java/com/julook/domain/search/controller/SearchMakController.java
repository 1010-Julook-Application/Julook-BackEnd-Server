package com.julook.domain.search.controller;

import com.julook.domain.common.dto.response.ApiResponseDTO;
import com.julook.domain.common.dto.response.MakResponseDTO;
import com.julook.domain.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SearchMakController {
    private final SearchService searchService;

    @Autowired
    public SearchMakController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponseDTO<List<MakResponseDTO>>> getSearchResults(
            @RequestParam(value = "keyword", required = false) String keyword) {

        // 서비스
        List<MakResponseDTO> searchResults = searchService.getSearchResults(keyword);

        ApiResponseDTO<List<MakResponseDTO>> response = ApiResponseDTO.<List<MakResponseDTO>>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(searchResults)
                .build();

        return ResponseEntity.ok(response);

    }
}

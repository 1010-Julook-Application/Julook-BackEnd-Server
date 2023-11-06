package com.julook.domain.home.controller;

import com.julook.domain.common.dto.response.ApiResponseDTO;
import com.julook.domain.common.dto.response.MakResponseDTO;
import com.julook.domain.home.dto.response.MakLikesAndCommentsDTO;
import com.julook.domain.home.dto.response.RecentCommentsResponseDTO;
import com.julook.domain.home.repository.MainInfoRepository;
import com.julook.domain.home.service.MainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class MainInfoController {
    private final MainInfoService mainInfoService;

    @Autowired
    public MainInfoController(MainInfoRepository mainInfoRepository, MainInfoService mainInfoService) {
        this.mainInfoService = mainInfoService;
    }
    @GetMapping("/newMakList")
    public ResponseEntity<ApiResponseDTO<List<MakResponseDTO>>> getNewMakList() {
        List<MakResponseDTO> newMakResults = mainInfoService.getNewMakList();

        ApiResponseDTO<List<MakResponseDTO>> response = ApiResponseDTO.<List<MakResponseDTO>>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(newMakResults)
                .build();

        return ResponseEntity.ok(response);

    }

    @GetMapping("/recentComments")
    public ResponseEntity<ApiResponseDTO<List<RecentCommentsResponseDTO>>> getRecentComments() {
        List<RecentCommentsResponseDTO> recentCMResults = mainInfoService.getRecentComments();

        ApiResponseDTO<List<RecentCommentsResponseDTO>> response = ApiResponseDTO.<List<RecentCommentsResponseDTO>>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(recentCMResults)
                .build();

        return ResponseEntity.ok(response);
    }

}

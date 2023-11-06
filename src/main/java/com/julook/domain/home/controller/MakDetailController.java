package com.julook.domain.home.controller;

import com.julook.domain.common.dto.response.ApiResponseDTO;
import com.julook.domain.home.dto.response.MakDetailResponseDTO;
import com.julook.domain.home.dto.response.MakLikesAndCommentsDTO;
import com.julook.domain.home.service.MakDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/makInfo/userSearch")
public class MakDetailController {
    private final MakDetailService makDetailService;

    @Autowired
    public MakDetailController(MakDetailService makDetailService) {
        this.makDetailService = makDetailService;
    }

    @GetMapping("/detail")
    public ResponseEntity<ApiResponseDTO<MakDetailResponseDTO>> getMakDetailInfo(
            @RequestParam(value = "makNumber") int makSeq,
            @RequestParam(value = "userId") long userId) {

        // 사비스 메서드 호출
        MakDetailResponseDTO detailInfo = makDetailService.getMakDetailInfo(makSeq, userId);

        ApiResponseDTO<MakDetailResponseDTO> response = ApiResponseDTO.<MakDetailResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(detailInfo)
                .build();

        return ResponseEntity.ok(response);

    }

    @GetMapping("/makLikesAndComments")
    public ResponseEntity<ApiResponseDTO<MakLikesAndCommentsDTO>> getMakLikesAndComments(
            @RequestParam(value = "makNumber") int makSeq) {

        MakLikesAndCommentsDTO makResults = makDetailService.getMakLikesAndComments(makSeq);

        ApiResponseDTO<MakLikesAndCommentsDTO> response = ApiResponseDTO.<MakLikesAndCommentsDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(makResults)
                .build();

        return ResponseEntity.ok(response);
    }

}

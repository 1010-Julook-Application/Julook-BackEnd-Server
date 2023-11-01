package com.julook.domain.home.find.controller;

import com.julook.domain.home.common.dto.response.ApiResponseDTO;
import com.julook.domain.home.find.dto.response.FindByUserResponseDTO;
import com.julook.domain.home.find.dto.response.MakDetailResponseDTO;
import com.julook.domain.home.find.service.MakDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(value = "makNumber") int makSeq) {

        // 사비스 메서드 호출
        MakDetailResponseDTO detailInfo = makDetailService.getMakDetailInfo(makSeq);

        ApiResponseDTO<MakDetailResponseDTO> response = ApiResponseDTO.<MakDetailResponseDTO>builder()
                .resultCode(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(detailInfo)
                .build();

        return ResponseEntity.ok(response);


    }
}

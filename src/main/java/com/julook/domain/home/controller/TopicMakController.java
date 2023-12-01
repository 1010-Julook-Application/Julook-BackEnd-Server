package com.julook.domain.home.controller;

import com.julook.domain.common.dto.response.ApiResponseDTO;
import com.julook.domain.common.dto.response.MakResponseDTO;
import com.julook.domain.home.dto.MakInfoDTO;
import com.julook.domain.home.dto.response.FindByUserResponseDTO;
import com.julook.domain.home.service.TopicMakService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/home/topic")
public class TopicMakController {
    private final TopicMakService topicMakService;
    @GetMapping("/pohangMak")
    public ResponseEntity<ApiResponseDTO<Slice<MakInfoDTO>>> getPohangMakList(
            @PageableDefault(size = 10) Pageable pageable) {

        Slice<MakInfoDTO> results = topicMakService.getPohangMak(pageable);

        ApiResponseDTO<Slice<MakInfoDTO>> response = ApiResponseDTO.<Slice<MakInfoDTO>>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(results)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/2023AwardsMak")
    public ResponseEntity<ApiResponseDTO<Slice<MakInfoDTO>>> get2023AwardsMak(
            @PageableDefault(size = 10) Pageable pageable) {

        Slice<MakInfoDTO> results = topicMakService.get2023AwardsMak(pageable);

        ApiResponseDTO<Slice<MakInfoDTO>> response = ApiResponseDTO.<Slice<MakInfoDTO>>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(results)
                .build();

        return ResponseEntity.ok(response);
    }
}

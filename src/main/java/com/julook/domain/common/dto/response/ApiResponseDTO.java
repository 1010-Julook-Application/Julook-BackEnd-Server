package com.julook.domain.common.dto.response;


import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponseDTO<T> {

    // API 응답 코드 Response
    private int resultCode;

    // API 응답 코드 Message
    private String resultMsg;

    // API 응답 결과 Response
    private T result;

    @Builder
    public ApiResponseDTO(final int resultCode, final String resultMsg, final T result) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.result = result;
    }

}



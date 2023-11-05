package com.julook.domain.common.dto.response;


import lombok.*;

@Getter
public class ApiResponseDTO<T> {

    // API 응답 코드 Response
    private int status;

    // API 응답 코드 Message
    private String resultMsg;

    // API 응답 결과 Response
    private T result;

    @Builder
    public ApiResponseDTO(final int status, final String resultMsg, final T result) {
        this.status = status;
        this.resultMsg = resultMsg;
        this.result = result;
    }

}



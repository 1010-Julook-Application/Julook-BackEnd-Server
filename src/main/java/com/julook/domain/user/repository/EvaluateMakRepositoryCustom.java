package com.julook.domain.user.repository;

import com.julook.domain.user.dto.request.EvaluateMakRequestDTO;

public interface EvaluateMakRepositoryCustom {
    // 막걸리 평가 (안 한경우)
    Boolean insertEvaluateMak(EvaluateMakRequestDTO userRequest);

    // 막걸리 평가 (한 경우)
    Boolean updateEvaluateMak(EvaluateMakRequestDTO userRequest);

    // 막걸리 평가 여부 판단
    Boolean isUserAlreadyEvaluateMak(EvaluateMakRequestDTO userRequest);


}

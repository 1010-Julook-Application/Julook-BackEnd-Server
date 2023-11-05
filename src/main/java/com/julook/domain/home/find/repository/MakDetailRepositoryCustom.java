package com.julook.domain.home.find.repository;

import ch.qos.logback.core.joran.sanity.Pair;
import com.julook.domain.home.find.dto.MakInfoWithUserActionDTO;
import com.julook.domain.home.find.dto.response.MakLikesAndCommentsDTO;
import com.julook.domain.home.find.entity.MakInfo;
import com.julook.domain.user.dto.response.MakUserTableDTO;

import java.util.List;

public interface MakDetailRepositoryCustom {
    MakInfoWithUserActionDTO getMakDetailInfo(int makNumber, long userId);

    // 해당 막걸리 평가 및 코멘트 가져오기
    MakLikesAndCommentsDTO getMakLikesAndComments(int makNumber);
}

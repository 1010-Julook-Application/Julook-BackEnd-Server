package com.julook.domain.home.service;

import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.home.dto.MakInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface TopicMakService {
    Slice<MakInfoDTO> getPohangMak(Pageable pageable);
    Slice<MakInfoDTO> get2023AwardsMak(Pageable pageable);
}

package com.julook.domain.home.service.impl;

import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.home.dto.MakInfoDTO;
import com.julook.domain.home.repository.TopicMakRepository;
import com.julook.domain.home.service.TopicMakService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TopicMakServiceImpl implements TopicMakService {
    private final TopicMakRepository viewPohangMakRepository;
    private final ModelMapper modelMapper;

    @Override
    public Slice<MakInfoDTO> getPohangMak(Pageable pageable) {
        int offset = (int) pageable.getOffset();
        int pageSize = pageable.getPageSize();

        List<MakInfo> results = viewPohangMakRepository.findAllFromPohangView(offset, pageSize);

        List<MakInfoDTO> pohangList = results.stream()
                .map(entity -> modelMapper.map(entity, MakInfoDTO.class))
                .collect(Collectors.toList());

        return new SliceImpl<>(pohangList, pageable, hasNextPage(results, pageable.getPageSize()));
    }

    @Override
    public Slice<MakInfoDTO> get2023AwardsMak(Pageable pageable) {
        int offset = (int) pageable.getOffset();
        int pageSize = pageable.getPageSize();

        List<MakInfo> results = viewPohangMakRepository.findAllFrom2023AwardsView(offset, pageSize);

        List<MakInfoDTO> pohangList = results.stream()
                .map(entity -> modelMapper.map(entity, MakInfoDTO.class))
                .collect(Collectors.toList());

        return new SliceImpl<>(pohangList, pageable, hasNextPage(results, pageable.getPageSize()));
    }

    private boolean hasNextPage(List<MakInfo> contents, int pageSize) {
        if (contents.size() > pageSize) {
            contents.remove(pageSize);
            return true;
        }
        return false;
    }
}

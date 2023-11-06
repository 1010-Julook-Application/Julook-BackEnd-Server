package com.julook.domain.search.service.impl;

import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.common.dto.response.MakResponseDTO;
import com.julook.domain.search.repository.SearchRepository;
import com.julook.domain.search.service.SearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SearchServiceImpl(SearchRepository searchRepository, ModelMapper modelMapper) {
        this.searchRepository = searchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MakResponseDTO> getSearchResults(String keyword) {
        List<MakInfo> searchResults = searchRepository.getSearchResults(keyword);

        List<MakResponseDTO> responseDTO = searchResults.stream()
                .map(makInfo -> {
                    // MainDetail 객체 생성 및 설정
                    MakResponseDTO.MainDetail mainDetail = MakResponseDTO.MainDetail.builder()
                            .makAlcoholPercentage(makInfo.getMakAlcoholPercentage())
                            .makPrice(makInfo.getMakPrice())
                            .makVolume(makInfo.getMakVolume())
                            .build();

                    // Taste 객체 생성 및 설정
                    MakResponseDTO.Taste taste = MakResponseDTO.Taste.builder()
                            .makTasteSweet(makInfo.getMakTasteSweet())
                            .makTasteSour(makInfo.getMakTasteSour())
                            .makTasteThick(makInfo.getMakTasteThick())
                            .makTasteFresh(makInfo.getMakTasteFresh())
                            .build();

                    MakResponseDTO dto = MakResponseDTO.builder()
                            .makNumber(makInfo.getMakSeq())
                            .makName(makInfo.getMakName())
                            .makType(makInfo.getMakType())
                            .makImageNumber(makInfo.getMakImageNumber())
                            .mainDetail(mainDetail)
                            .taste(taste)
                            .build();


                    return dto;
                })
                .collect(Collectors.toList());


        return responseDTO;
    }


}

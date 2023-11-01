package com.julook.domain.home.find.service.impl;

import com.julook.domain.home.find.dto.response.MakDetailResponseDTO;
import com.julook.domain.home.find.entity.MakInfo;
import com.julook.domain.home.find.repository.MakDetailRepository;
import com.julook.domain.home.find.service.MakDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


@Service
public class MakDetailServiceImpl implements MakDetailService {
    private final MakDetailRepository makDetailRepository;
    private final ModelMapper modelMapper;

    public MakDetailServiceImpl(MakDetailRepository makDetailRepository, ModelMapper modelMapper) {
        this.makDetailRepository = makDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MakDetailResponseDTO getMakDetailInfo(int makNumber) {
        // MakDetailRepository를 사용하여 데이터 검색
        MakInfo makInfo = makDetailRepository.getMakDetailInfo(makNumber);

        // "makAwards" 값을 컴마(,)로 분리하여 리스트에 추가
        List<String> awards = new ArrayList<>();
        if (makInfo.getMakAwards() != null) {
            String[] awardParts = makInfo.getMakAwards().split(",");
            for (String award : awardParts) {
                String trimmedAward = award.trim();
                if (!trimmedAward.isEmpty()) {
                    awards.add(trimmedAward);
                }
            }
        }

        return createMakDetailResponseDTO(makInfo, awards);
    }

    private MakDetailResponseDTO.MainDetail createMainDetail(MakInfo makInfo) {
        return MakDetailResponseDTO.MainDetail.builder()
                .makAlcoholPercentage(makInfo.getMakAlcoholPercentage())
                .makVolume(makInfo.getMakVolume())
                .makPrice(makInfo.getMakPrice())
                .build();
    }

    private MakDetailResponseDTO.Taste createTaste(MakInfo makInfo) {
        return MakDetailResponseDTO.Taste.builder()
                .makTasteSweet(makInfo.getMakTasteSweet())
                .makTasteSour(makInfo.getMakTasteSour())
                .makTasteThick(makInfo.getMakTasteThick())
                .makTasteFresh(makInfo.getMakTasteFresh())
                .build();
    }

    private MakDetailResponseDTO.BreweryInfo createBreweryInfo(MakInfo makInfo) {
        return MakDetailResponseDTO.BreweryInfo.builder()
                .makBrewery(makInfo.getMakBrewery())
                .makBreweryLink(makInfo.getMakBreweryLink())
                .makSalesLink(makInfo.getMakSalesLink())
                .build();
    }

    private MakDetailResponseDTO createMakDetailResponseDTO(MakInfo makInfo, List<String> awards) {
        List<MakDetailResponseDTO.MainDetail> mainDetails = Collections.singletonList(createMainDetail(makInfo));
        List<MakDetailResponseDTO.Taste> tastes = Collections.singletonList(createTaste(makInfo));
        List<MakDetailResponseDTO.BreweryInfo> breweryInfoList = Collections.singletonList(createBreweryInfo(makInfo));

        return MakDetailResponseDTO.builder()
                .makSeq(makInfo.getMakSeq())
                .makType(makInfo.getMakType())
                .makName(makInfo.getMakName())
                .makImageNumber(makInfo.getMakImageNumber())
                .attributes(Collections.singletonList(
                        MakDetailResponseDTO.Attribute.builder()
                                .mainDetail(mainDetails)
                                .taste(tastes)
                                .makContent(makInfo.getMakContent())
                                .makAwards(awards)
                                .makRaw(makInfo.getMakRaw())
                                .breweryInfo(breweryInfoList)
                                .build()
                ))
                .build();
    }
}


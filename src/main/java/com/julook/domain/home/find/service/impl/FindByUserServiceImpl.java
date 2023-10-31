package com.julook.domain.home.find.service.impl;

import com.julook.domain.home.find.dto.MakInfoDTO;
import com.julook.domain.home.find.entity.MakInfo;
import com.julook.domain.home.find.repository.FindByUserRepository;
import com.julook.domain.home.find.service.FindByUserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindByUserServiceImpl implements FindByUserService {
    private final FindByUserRepository findByUserRepository;
    private final ModelMapper modelMapper;


    public FindByUserServiceImpl(FindByUserRepository findByUserRepository, ModelMapper modelMapper) {
        this.findByUserRepository = findByUserRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<MakInfoDTO> getMakInfoListByUserPreferences(Long lastMakNum, List<String> categories, String sort, Pageable pageable) {
        List<MakInfo> makInfoList = findByUserRepository.findByPreferences(lastMakNum, categories, sort, pageable);

        List<MakInfoDTO> makInfoDTOList = convertToDTOList(makInfoList);

        return makInfoDTOList;
    }

    /**
     * 엔터티를 DTO로 변환하는 메서드
     *
     * @param entities 엔터티 리스트
     * @return DTO 리스트
     */
    private List<MakInfoDTO> convertToDTOList(List<MakInfo> entities) {
        List<MakInfoDTO> dtoList = new ArrayList<>();
        for (MakInfo entity : entities) {
            MakInfoDTO dto = modelMapper.map(entity, MakInfoDTO.class);
            dtoList.add(dto);
        }
        return dtoList;
    }
}

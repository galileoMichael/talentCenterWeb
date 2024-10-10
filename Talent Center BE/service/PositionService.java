package com.tujuhsembilan.talentcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.talentcenter.dto.PositionDto;
import com.tujuhsembilan.talentcenter.model.*;
import com.tujuhsembilan.talentcenter.repository.PositionRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service // Anotasi ini digunakan untuk mendeklarasikan ini adalah bean service
public class PositionService {

    @Autowired //memberi tahu Spring Framework bahwa suatu properti, metode, atau konstruktor harus diinjeksikan (autowired) dengan dependensi yang sesuai
    private PositionRepository positionRepository;

    public List<PositionDto> getMasterPosisiTalent() {
        List<Position> posisiTalent = positionRepository.findAll(); // pemanggilan List dari Position Model

        return posisiTalent.stream()
                .map(positionModel -> {
                    PositionDto response = PositionDto.builder().build(); // karena pada DTO dan Model memakai @Builder, maka seperti line ini
                    response.setPositionId(positionModel.getPositionId()); //setter untuk ID
                    response.setPositionName(positionModel.getPositionName()); // setter untuk position name
                    return response; // return dari response nya
                })
                .collect(Collectors.toList()); // digunakan untuk mengumpulkan elemen dari sebuah stream ke dalam sebuah List.
    }
}
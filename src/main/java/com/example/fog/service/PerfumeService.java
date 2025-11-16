package com.example.fog.service;

import com.example.fog.dto.perfume.PerfumeResponseDto;
import com.example.fog.entity.Perfume;
import com.example.fog.repository.PerfumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerfumeService {

    private final PerfumeRepository perfumeRepository;
    private final ReviewService reviewService;


}
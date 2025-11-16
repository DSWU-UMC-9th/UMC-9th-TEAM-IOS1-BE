package com.example.fog.controller;

import com.example.fog.code.ResponseCode;
import com.example.fog.dto.perfume.PerfumeResponseDto;
import com.example.fog.dto.response.ResponseDTO;
import com.example.fog.service.PerfumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfumes")
@RequiredArgsConstructor
public class PerfumeController {

    private final PerfumeService perfumeService;

//    @GetMapping
//    public ResponseDTO<List<PerfumeResponseDto>> getPerfumes() {
//        List<PerfumeResponseDto> perfumes = perfumeService.getAllPerfumes();
//        return new ResponseDTO<>(ResponseCode.SUCCESS_GET_PERFUME, perfumes);
//    }
}
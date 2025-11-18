package com.example.fog.repository;

import com.example.fog.dto.perfume.PerfumeRecommendResponseDTO;
import com.example.fog.dto.perfume.PerfumeSummaryResponseDTO;
import com.example.fog.entity.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    @Query("""
        SELECT new com.example.fog.dto.perfume.PerfumeSummaryResponseDTO(
            p.id, p.name, p.imageUrl,
            COALESCE(CAST(AVG(r.rating) AS double), 0.0),
            COUNT(r)
        )
        FROM Perfume p
        LEFT JOIN p.reviews r
        GROUP BY p
    """)
    List<PerfumeSummaryResponseDTO> findPerfumeRating();

    @Query("""
        SELECT new com.example.fog.dto.perfume.PerfumeRecommendResponseDTO(
            p.id, p.name, p.imageUrl, COUNT(r)
        )
        FROM Perfume p
        LEFT JOIN p.reviews r ON r.createdAt >= :today
        GROUP BY p
        ORDER BY COUNT(r) DESC, p.name ASC
    """)
    List<PerfumeRecommendResponseDTO> findTodayTopPerfumes(LocalDateTime today);
}
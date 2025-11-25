package com.example.fog.repository;

import com.example.fog.entity.Review;
import com.example.fog.entity.Perfume;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPerfume(Perfume perfume, Sort sort);

    @Query("SELECT r FROM Review r JOIN FETCH r.user WHERE r.perfume = :perfume ORDER BY r.createdAt DESC")
    List<Review> findByPerfumeWithUser(@Param("perfume") Perfume perfume);
}
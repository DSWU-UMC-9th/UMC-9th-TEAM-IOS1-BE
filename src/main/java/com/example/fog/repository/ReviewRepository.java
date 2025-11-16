package com.example.fog.repository;

import com.example.fog.entity.Review;
import com.example.fog.entity.Perfume;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPerfume(Perfume perfume, Sort sort);
}
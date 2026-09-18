package com.example.restful_web_services.repository;

import com.example.restful_web_services.pojo.Preschool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreschoolRepository extends JpaRepository<Preschool, Long> {

    List<Preschool> findByNeighbourhoodIgnoreCase(String neighbourhood);

    List<Preschool> findByRatingGreaterThanEqual(double minRating);

    List<Preschool> findByNeighbourhoodIgnoreCaseAndRatingGreaterThanEqual(String neighbourhood, double minRating);
}

package com.example.restful_web_services.controller;

import com.example.restful_web_services.pojo.Preschool;
import com.example.restful_web_services.repository.PreschoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Read-only, public API for the Den Haag preschool research map.
 *
 * Quality signals (rating, reviewCount) and the aggregate diversity research
 * statistic are exposed as separate fields on purpose. There is intentionally
 * no "best school" ranking or filter that factors ethnicity/diversity in:
 * this endpoint is meant for research and reporting on childcare quality and,
 * separately, on neighbourhood-level segregation trends, not for building a
 * "pick a school by ethnic composition" consumer feature.
 */
@RestController
@RequestMapping("/api/den-haag/preschools")
@Slf4j
public class PreschoolController {

    @Autowired
    private PreschoolRepository preschoolRepository;

    @GetMapping
    public ResponseEntity<List<Preschool>> getAllPreschools(
            @RequestParam(required = false) String neighbourhood,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Boolean internationalOnly) {

        List<Preschool> preschools;
        if (neighbourhood != null && minRating != null) {
            preschools = preschoolRepository.findByNeighbourhoodIgnoreCaseAndRatingGreaterThanEqual(neighbourhood, minRating);
        } else if (neighbourhood != null) {
            preschools = preschoolRepository.findByNeighbourhoodIgnoreCase(neighbourhood);
        } else if (minRating != null) {
            preschools = preschoolRepository.findByRatingGreaterThanEqual(minRating);
        } else {
            preschools = preschoolRepository.findAll();
        }

        if (Boolean.TRUE.equals(internationalOnly)) {
            preschools = preschools.stream().filter(Preschool::isInternationalProgram).collect(Collectors.toList());
        }

        log.info("Returning {} preschools (neighbourhood={}, minRating={}, internationalOnly={})", preschools.size(), neighbourhood, minRating, internationalOnly);
        return new ResponseEntity<>(preschools, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Preschool> getPreschool(@PathVariable long id) {
        Optional<Preschool> preschool = preschoolRepository.findById(id);
        return preschool
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Aggregate, neighbourhood-level statistics for research/reporting only:
     * average rating and average diversity index per neighbourhood. Deliberately
     * not school-vs-school "which is whitest/best" output.
     */
    @GetMapping("/stats/by-neighbourhood")
    public ResponseEntity<List<Map<String, Object>>> getNeighbourhoodStats() {
        List<Preschool> all = preschoolRepository.findAll();

        Map<String, List<Preschool>> grouped = all.stream()
                .collect(Collectors.groupingBy(Preschool::getNeighbourhood, LinkedHashMap::new, Collectors.toList()));

        List<Map<String, Object>> stats = grouped.entrySet().stream()
                .map(entry -> {
                    List<Preschool> group = entry.getValue();
                    double avgRating = group.stream().mapToDouble(Preschool::getRating).average().orElse(0);
                    double avgDiversity = group.stream()
                            .filter(p -> p.getDiversityIndexPercent() != null)
                            .mapToDouble(Preschool::getDiversityIndexPercent)
                            .average()
                            .orElse(Double.NaN);
                    double avgPrice = group.stream()
                            .filter(p -> p.getPricePerHourEuro() != null)
                            .mapToDouble(Preschool::getPricePerHourEuro)
                            .average()
                            .orElse(Double.NaN);

                    Map<String, Object> row = new LinkedHashMap<>();
                    row.put("neighbourhood", entry.getKey());
                    row.put("preschoolCount", group.size());
                    row.put("averageRating", Math.round(avgRating * 10.0) / 10.0);
                    row.put("averageDiversityIndexPercent", Double.isNaN(avgDiversity) ? null : Math.round(avgDiversity * 10.0) / 10.0);
                    row.put("averagePricePerHourEuro", Double.isNaN(avgPrice) ? null : Math.round(avgPrice * 100.0) / 100.0);
                    return row;
                })
                .sorted(Comparator.comparing(m -> (String) m.get("neighbourhood")))
                .collect(Collectors.toList());

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}

package com.example.restful_web_services.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

/**
 * A preschool/childcare location in Den Haag, for the research map.
 *
 * diversityIndexPercent and diversitySourceYear hold an AGGREGATE, school-level
 * statistic (e.g. share of pupils with a migration background, as published by
 * official sources such as DUO or the Gemeente Den Haag open data portal).
 * This is deliberately kept separate from rating/reviewCount: the map must never
 * combine the two into a single "best" score, since ranking schools by ethnic
 * composition facilitates segregation in school choice. Treat this field as
 * research/reporting data only, not a selection criterion.
 *
 * internationalProgram/languages describe the medium of instruction and curriculum
 * on offer (e.g. bilingual Dutch/English, IB). This is an ordinary, non-discriminatory
 * school-choice factor and is fine to filter/sort on, unlike diversityIndexPercent above.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Preschool {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String neighbourhood;
    private String address;
    private double latitude;
    private double longitude;

    /** Average review rating, 0.0 - 5.0. */
    private double rating;
    private int reviewCount;

    /** e.g. "0-4 jaar" */
    private String ageRange;

    /** Aggregate, school-level diversity statistic for research purposes only. Nullable. */
    private Double diversityIndexPercent;

    /** Year the diversityIndexPercent figure was published. Nullable. */
    private Integer diversitySourceYear;

    /** Indicative childcare price in EUR per hour. */
    private Double pricePerHourEuro;

    /** Whether the location offers a bilingual/international programme (medium of instruction, curriculum). */
    private boolean internationalProgram;

    /** Languages of instruction, e.g. "Dutch" or "Dutch, English". */
    private String languages;

    @Override
    public String toString() {
        return "Preschool{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", neighbourhood='" + neighbourhood + '\'' +
                ", rating=" + rating +
                ", reviewCount=" + reviewCount +
                '}';
    }
}

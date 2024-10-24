package com.film_production.demo.specifications;

import com.film_production.demo.models.entities.FilmProduction;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class FilmProductionSpecification {

    public static Specification<FilmProduction> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> title == null ? null : criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<FilmProduction> hasDirector(String director) {
        return (root, query, criteriaBuilder) -> director == null ? null : criteriaBuilder.equal(root.get("director"), director);
    }

    public static Specification<FilmProduction> hasStatus(String status) {
        return (root, query, criteriaBuilder) -> status == null ? null : criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<FilmProduction> hasBudgetBetween(Double minBudget, Double maxBudget) {
        return (root, query, criteriaBuilder) -> {
            if (minBudget == null && maxBudget == null) return null;
            if (minBudget != null && maxBudget != null) {
                return criteriaBuilder.between(root.get("budget"), minBudget, maxBudget);
            } else if (minBudget != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("budget"), minBudget);
            } else {
                return criteriaBuilder.lessThanOrEqualTo(root.get("budget"), maxBudget);
            }
        };
    }

    public static Specification<FilmProduction> hasStartDateAfter(Date startDate) {
        return (root, query, criteriaBuilder) -> startDate == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    public static Specification<FilmProduction> hasEndDateBefore(Date endDate) {
        return (root, query, criteriaBuilder) -> endDate == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate);
    }
}

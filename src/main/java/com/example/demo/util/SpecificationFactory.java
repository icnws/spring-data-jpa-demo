package com.example.demo.util;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
public final class SpecificationFactory {

    public static Specification containsLike(String attribute, String value) {
        return (root, query, cb) -> cb.like(root.get(attribute), "%" + value + "%");
    }

    public static Specification isBetween(String attribute, int min, int max) {
        return (root, query, cb) -> cb.between(root.get(attribute), min, max);
    }

    public static Specification isBetween(String attribute, double min, double max) {
        return (root, query, cb) -> cb.between(root.get(attribute), min, max);
    }

    public static <T extends Enum<T>> Specification enumMatcher(String attribute, T queriedValue) {
        return (root, query, cb) -> {
            Path<T> actualValue = root.get(attribute);
            if (queriedValue == null) {
                return null;
            }
            return cb.equal(actualValue, queriedValue);
        };
    }
}

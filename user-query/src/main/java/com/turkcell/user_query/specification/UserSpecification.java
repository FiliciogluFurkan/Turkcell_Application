package com.turkcell.user_query.specification;

import com.turkcell.user_query.entity.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<UserEntity> hasName(String name) {
        return (Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (name == null) {
                return null;
            }
            return cb.equal(root.get("name"), name);
        };
    }

    public static Specification<UserEntity> hasAgeGreaterThan(Integer age) {
        return (Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (age < 0 || age == null) {
                return null;
            }
            return cb.greaterThan(root.get("age"), age);
        };
    }

    public static Specification<UserEntity> hasStatus(Boolean status) {
        return (Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            if (status == null) return null;

            return cb.equal(root.get("status"), status);
        };
    }


}

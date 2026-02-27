package com.mapnaom.foodapp.specifications;

import com.mapnaom.foodapp.entities.User;
import com.mapnaom.foodapp.searchForms.UserSearchForm;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName == null || firstName.isEmpty() ? null : criteriaBuilder.equal(root.get("firstname"), firstName);
    }

    public static Specification<User> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                lastName == null || lastName.isEmpty() ? null : criteriaBuilder.equal(root.get("lastname"), lastName);
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                email == null || email.isEmpty() ? null : criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<User> hasUsername(String username) {
        return (root, query, criteriaBuilder) ->
                username == null || username.isEmpty() ? null : criteriaBuilder.equal(root.get("username"), username);
    }

    public static Specification<User> isEnabled(Boolean enabled) {
        return (root, query, criteriaBuilder) ->
                enabled == null ? null : criteriaBuilder.equal(root.get("enabled"), enabled);
    }

    public static Specification<User> fromSearchForm(UserSearchForm searchForm) {
        return Specification.where(hasFirstName(searchForm.getFirstName()))
                .and(hasLastName(searchForm.getLastName()))
                .and(hasEmail(searchForm.getEmail()))
                .and(hasUsername(searchForm.getUsername()))
                .and(isEnabled(searchForm.getEnabled()));
    }
}

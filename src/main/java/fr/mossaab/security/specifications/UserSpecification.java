package fr.mossaab.security.specifications;

import fr.mossaab.security.entities.User;
import fr.mossaab.security.searchForms.UserSearchForm;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName == null || firstName.isEmpty() ? null : criteriaBuilder.equal(root.get("firstName"), firstName);
    }

    public static Specification<User> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                lastName == null || lastName.isEmpty() ? null : criteriaBuilder.equal(root.get("lastName"), lastName);
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                email == null || email.isEmpty() ? null : criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<User> isEnabled(Boolean enabled) {
        return (root, query, criteriaBuilder) ->
                enabled == null ? null : criteriaBuilder.equal(root.get("enabled"), enabled);
    }

    public static Specification<User> fromSearchForm(UserSearchForm searchForm) {
        return Specification.where(hasFirstName(searchForm.getFirstName()))
                .and(hasLastName(searchForm.getLastName()))
                .and(hasEmail(searchForm.getEmail()))
                .and(isEnabled(searchForm.getEnabled()));
    }
}

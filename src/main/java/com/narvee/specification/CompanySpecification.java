package com.narvee.specification;
import com.narvee.entity.Company;
import org.springframework.data.jpa.domain.Specification;

public class CompanySpecification {

    public static Specification<Company> filter(Company filter) {
        return (root, query, cb) -> {
            var predicate = cb.conjunction();

            if (filter.getName() != null) {
                predicate.getExpressions().add(
                        cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
            }
            if (filter.getEmail() != null) {
                predicate.getExpressions().add(
                        cb.like(cb.lower(root.get("email")), "%" + filter.getEmail().toLowerCase() + "%"));
            }
            if (filter.getPhoneNo() != null) {
                predicate.getExpressions().add(
                        cb.equal(root.get("phoneNo"), filter.getPhoneNo()));
            }
            if (filter.getAddress() != null) {
                predicate.getExpressions().add(
                        cb.like(cb.lower(root.get("address")), "%" + filter.getAddress().toLowerCase() + "%"));
            }
            if (filter.getWebsiteUrl() != null) {
                predicate.getExpressions().add(
                        cb.like(cb.lower(root.get("websiteUrl")), "%" + filter.getWebsiteUrl().toLowerCase() + "%"));
            }
            if (filter.getCountry() != null) {
                predicate.getExpressions().add(
                        cb.like(cb.lower(root.get("country")), "%" + filter.getCountry().toLowerCase() + "%"));
            }

            return predicate;
        };
    }
}

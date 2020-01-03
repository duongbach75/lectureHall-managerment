package vn.savis.lhm.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.savis.lhm.dto.SearchCriteria;
import vn.savis.lhm.entity.Registration;

public class RegistrationSpecifications implements Specification<Registration> {

	private SearchCriteria criteria;

	public RegistrationSpecifications(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	public SearchCriteria getCriteria() {
		return criteria;
	}

	@Override
	public Predicate toPredicate(Root<Registration> root, CriteriaQuery<?> criteriaQuery,
			CriteriaBuilder criteriaBuilder) {
		Predicate predicate = null;
		if (criteria.getOperation().equalsIgnoreCase(">")) {
			predicate = criteriaBuilder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()),
					criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("<")) {
			predicate = criteriaBuilder.lessThanOrEqualTo(root.<String>get(criteria.getKey()),
					criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase(":")) {
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				predicate = criteriaBuilder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
			} else {
				predicate = criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		}

		return predicate;
	}

}

package vn.savis.lhm.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.savis.lhm.dto.SearchCriteria;
import vn.savis.lhm.entity.StaffIncharge;


public class StaffInchargeSpecification implements Specification<StaffIncharge> {

	private SearchCriteria criteria;

	
	
	public StaffInchargeSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}


	public SearchCriteria getCriteria() {
		return criteria;
	}

	@Override
	public Predicate toPredicate(Root<StaffIncharge> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		Predicate predicate = null;
		if (criteria.getOperation().equalsIgnoreCase(">")) {
			predicate = criteriaBuilder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()),
					criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("<")) {
			predicate = criteriaBuilder.lessThanOrEqualTo(root.<String>get(criteria.getKey()),
					criteria.getValue().toString());
		}  else if (criteria.getOperation().equalsIgnoreCase(":")) {
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				predicate = criteriaBuilder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
			} else {
				predicate = criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		}

		return predicate;
	}

}

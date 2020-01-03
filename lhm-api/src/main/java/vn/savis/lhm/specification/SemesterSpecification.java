package vn.savis.lhm.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.savis.lhm.entity.Semester;
import vn.savis.lhm.entity.Subjects;

public class SemesterSpecification {
	public static Specification<Semester> advanceFilter(Semester semester){
		return new Specification<Semester>() {

			@Override
			public Predicate toPredicate(Root<Semester> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				return null;
			}

					};
	}


}

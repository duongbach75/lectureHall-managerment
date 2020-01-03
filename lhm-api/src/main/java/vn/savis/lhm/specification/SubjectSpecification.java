package vn.savis.lhm.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.savis.lhm.entity.Subjects;;

public class SubjectSpecification {
	public static Specification<Subjects> advanceFilter(Subjects Subjects){
		return new Specification<Subjects>() {

			@Override
			public Predicate toPredicate(Root<Subjects> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;

				if(Subjects.getSemester() != null && Subjects.getSemester().getIdSemester() != 0) {
					obj = cb.equal(root.get("Semester").get("idSemester"), Subjects.getSemester().getIdSemester());
					predicateList.add(obj);
				}
				
				if(Subjects.getSubjectsType() != null && Subjects.getSubjectsType().getIdSubjectsType() != 0) {
					obj = cb.equal(root.get("SubjectsType").get("idSubjectsType"), Subjects.getSubjectsType().getIdSubjectsType());
					predicateList.add(obj);
				}
				if (Subjects.getNameSubjects() != null && !Subjects.getNameSubjects().equals("")) {
					obj = cb.like(cb.lower(root.get("name_subject")), "%" + Subjects.getNameSubjects().toLowerCase() + "%");
					predicateList.add(obj);
				}
				if(Subjects.getNumberOfCredits() != 0) {
					obj = cb.equal(root.get("NumberofCredit"), Subjects.getNumberOfCredits());
					predicateList.add(obj);
				}
				if (Subjects.getStatuss() != null && !Subjects.getStatuss().equals("")) {
					obj = cb.like(cb.lower(root.get("statuss")), "%" + Subjects.getStatuss().toLowerCase() + "%");
					predicateList.add(obj);
				}

				query.orderBy(cb.desc(root.get("idSubjects")));
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

}

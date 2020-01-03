package vn.savis.lhm.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import vn.savis.lhm.entity.Schedule;

public class ScheduleSpecification {
	public static Specification<Schedule> advanceFilter(Schedule Schedule){
		return new Specification<Schedule>() {

			@Override
			public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;

				if(Schedule.getClass() != null && Schedule.getClassroom().getIdClassroom() != 0) {
					obj = cb.equal(root.get("Classroom").get("idClassroom"), Schedule.getClassroom().getIdClassroom());
					predicateList.add(obj);
				}
				
				if(Schedule.getSubjects() != null && Schedule.getSubjects().getIdSubjects() != 0) {
					obj = cb.equal(root.get("Subjects").get("idSubjects"), Schedule.getSubjects().getIdSubjects());
					predicateList.add(obj);
				}
				if (Schedule.getStudyTime() != null && !Schedule.getStudyTime().equals("")) {
					obj = cb.like(cb.lower(root.get("StudyTime")), "%" + Schedule.getStudyTime().toLowerCase() + "%");
					predicateList.add(obj);
				}
				

				query.orderBy(cb.desc(root.get("idSchedule")));
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}

		};
}
}

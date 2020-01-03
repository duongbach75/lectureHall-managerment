package vn.savis.lhm.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.savis.lhm.entity.Classroom;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: SonNH - SAVIS
 * @created: 9/6/19
 * @Time: 09:50
 * @modified 9/6/19
 **/

public class ClassroomSpecifications {
	public static Specification<Classroom> advanceFilter(Classroom classroom){
		return new Specification<Classroom>() {

			@Override
			public Predicate toPredicate(Root<Classroom> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;

				if(classroom.getAmphitheater() != null && classroom.getAmphitheater().getNameAmphitheater() != null) {
					obj = cb.equal(root.get("amphitheater").get("nameAmphitheater"), classroom.getAmphitheater().getNameAmphitheater());
					predicateList.add(obj);
				}
				if (classroom.getSymbol() != null && !classroom.getSymbol().equals("")) {
					obj = cb.like(cb.lower(root.get("symbol")), "%" + classroom.getSymbol().toLowerCase() + "%");
					predicateList.add(obj);
				}
				if (classroom.getNameClassroom() != null && !classroom.getNameClassroom().equals("")) {
					obj = cb.like(cb.lower(root.get("nameClassroom")), "%" + classroom.getNameClassroom().toLowerCase() + "%");
					predicateList.add(obj);
				}
				if(classroom.getAmount() != 0) {
					obj = cb.equal(root.get("amount"), classroom.getAmount());
					predicateList.add(obj);
				}
				if (classroom.getSize() != null && !classroom.getSize().equals("")) {
					obj = cb.like(cb.lower(root.get("size")), "%" + classroom.getSize().toLowerCase() + "%");
					predicateList.add(obj);
				}
				if (classroom.getChucNang() != null && !classroom.getChucNang().equals("")) {
					obj = cb.like(cb.lower(root.get("chucNang")), "%" + classroom.getChucNang().toLowerCase() + "%");
					predicateList.add(obj);
				}
				if (classroom.getStatuss() != null && !classroom.getStatuss().equals("")) {
					obj = cb.like(cb.lower(root.get("statuss")), "%" + classroom.getStatuss().toLowerCase() + "%");
					predicateList.add(obj);
				}

				query.orderBy(cb.desc(root.get("idClassroom")));
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}

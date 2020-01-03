package vn.savis.lhm.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.savis.lhm.common.util.CommonUtil;
import vn.savis.lhm.entity.Amphitheater;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: SonNH - SAVIS
 * @created: 9/12/19
 * @Time: 15:23
 * @modified 9/12/19
 **/
public class AmphitheaterSpecifications {
	public static Specification<Amphitheater> advanceFilter(Amphitheater amphitheater){
		return new Specification<Amphitheater>() {
			
			@Override
			public Predicate toPredicate(Root<Amphitheater> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;
				
				if(amphitheater.getNameAmphitheater() != null && !amphitheater.getNameAmphitheater().equals("")) {
					obj = cb.like(cb.lower(root.get("nameAmphitheater")), "%" + CommonUtil.standardized(amphitheater.getNameAmphitheater().toLowerCase()) + "%");
					predicateList.add(obj);	
				}
				if (amphitheater.getStatuss() != null && !amphitheater.getStatuss().equals("")) {
					obj = cb.like(cb.lower(root.get("statuss")), "%" + amphitheater.getStatuss().toLowerCase() + "%");
					predicateList.add(obj);
				}
				
				query.orderBy(cb.desc(root.get("idAmphitheater")));
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}

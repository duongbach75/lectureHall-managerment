package vn.savis.lhm.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.savis.lhm.common.util.CommonUtil;
import vn.savis.lhm.entity.Device;

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
public class DeviceSpecifications {
	public static Specification<Device> advanceFilter(Device device){
		return new Specification<Device>() {
			
			@Override
			public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				Predicate obj = null;
				
				if(device.getNameDevice() != null && !device.getNameDevice().equals("")) {
					obj = cb.like(cb.lower(root.get("nameDevice")), "%" + CommonUtil.standardized(device.getNameDevice().toLowerCase()) + "%");
					predicates.add(obj);
				}
				
				if(device.getStatuss() != null && !device.getStatuss().equals("")) {
					obj = cb.like(cb.lower(root.get("statuss")), "%" + CommonUtil.standardized(device.getStatuss().toLowerCase()) + "%");
					predicates.add(obj);
				}
				
				query.orderBy(cb.desc(root.get("idDevice")));
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}

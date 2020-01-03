package vn.savis.lhm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import vn.savis.lhm.entity.StaffIncharge;

public interface StaffInchargeService {
	List<StaffIncharge> findAll();

	Page<StaffIncharge> findPaging(Pageable pageable);

	StaffIncharge findOne(int id);

	int create(StaffIncharge staffIncharge);

	int update(StaffIncharge staffIncharge);

	int delete(int id);

	Page<StaffIncharge> findAll(Specification spec, Pageable pageable);

	int deleteInBatch(int[] ids);
}

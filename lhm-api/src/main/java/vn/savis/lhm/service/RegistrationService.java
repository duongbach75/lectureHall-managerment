package vn.savis.lhm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import vn.savis.lhm.entity.Registration;


public interface RegistrationService {
	
	List<Registration> findAll();

	Page<Registration> findPaging(Pageable pageable);

	Registration findOne(int id);

	int create(Registration registration);

	int update(Registration registration);

	int delete(int id);

	Page<Registration> findAll(Specification spec, Pageable pageable);

	int deleteInBatch(int[] ids);
}

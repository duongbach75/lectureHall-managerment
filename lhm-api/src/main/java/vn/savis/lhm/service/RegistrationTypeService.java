package vn.savis.lhm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import vn.savis.lhm.entity.RegistrationType;

public interface RegistrationTypeService {

	List<RegistrationType> findAll();

	Page<RegistrationType> findPaging(Pageable pageable);

	RegistrationType findOne(int id);

	int create(RegistrationType registrationType);

	int update(RegistrationType registrationType);

	int delete(int id);

	Page<RegistrationType> findAll(Specification spec, Pageable pageable);

	int deleteInBatch(int[] ids);
}

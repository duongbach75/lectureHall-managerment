package vn.savis.lhm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.savis.lhm.entity.Faculty;

public interface FacultyService {
	List<Faculty> findAll();

	

	
	Page<Faculty> findPaging(Pageable pageable);

	Faculty findOne(int id);
//	Faculty findByIdSubjectsType(int id);

	int create(Faculty faculty);

	int update(Faculty faculty);

	int delete(int id);
	int deleteAllFaculty(Iterable<Faculty> entities);
	List<Faculty> findBySubjectsTypeId(int id);
}

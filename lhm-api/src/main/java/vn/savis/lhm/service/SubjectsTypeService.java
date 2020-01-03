package vn.savis.lhm.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import vn.savis.lhm.entity.SubjectsType;


public interface SubjectsTypeService {
	List<SubjectsType> findAll();

	

	
	Page<SubjectsType> findPaging(Pageable pageable);

	SubjectsType findOne(int id);

	int create(SubjectsType subjectstype);

	int update(SubjectsType subjectstype);

	int delete(int id);
}

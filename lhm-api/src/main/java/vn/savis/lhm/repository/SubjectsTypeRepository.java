package vn.savis.lhm.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


import vn.savis.lhm.entity.SubjectsType;



public interface SubjectsTypeRepository  extends JpaRepository< SubjectsType, Integer>{
	SubjectsType findById(int id);
	List<SubjectsType> findAll(Specification<SubjectsType> spec);
//
	//@Procedure
//		List<User> getAllUsers();
//	
//	List<User> findAllByUsername(String username);
//	List<User> findAll(Specification<User> spec);
}

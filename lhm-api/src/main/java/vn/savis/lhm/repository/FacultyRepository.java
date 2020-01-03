package vn.savis.lhm.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.savis.lhm.entity.Faculty;
import vn.savis.lhm.entity.SubjectsType;



public interface FacultyRepository extends JpaRepository<Faculty, Integer>{
	Faculty findById(int id);
	List<Faculty> findAll(Specification<Faculty> spec);
	//Faculty findByidSubjectsType(int id);
	List<Faculty> findBySubjectsTypeIdSubjectsType(int subjectstypeId);
}

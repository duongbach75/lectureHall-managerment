package vn.savis.lhm.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.savis.lhm.entity.Lecturers;



public interface LecturersRepository  extends JpaRepository<Lecturers, Integer>{
	Lecturers findById(int id);
	List<Lecturers> findAll(Specification<Lecturers> spec);
	List<Lecturers> findByFacultyIdFaculty(int facultyId);
	
}

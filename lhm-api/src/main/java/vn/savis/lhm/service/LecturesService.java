package vn.savis.lhm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.savis.lhm.entity.Faculty;
import vn.savis.lhm.entity.Lecturers;

public interface LecturesService {
	List<Lecturers> findAll();

	


	Page<Lecturers> findPaging(Pageable pageable);

	Lecturers findOne(int id);

	int create(Lecturers lecturers);

	int update(Lecturers lecturers);

	int delete(int id);
	int deleteAllLecturers(Iterable<Lecturers> entities);
	List<Lecturers> findByFacultyId(int id);
}

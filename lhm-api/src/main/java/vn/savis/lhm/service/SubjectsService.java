package vn.savis.lhm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.savis.lhm.entity.Subjects;

@Service
public interface SubjectsService {
	List<Subjects> findAllSubjects();

    Page<Subjects> findPaging(Pageable pageable);

    Subjects findById(int idSubjects);

    int saveCreateSubjects(Subjects subjects);

    int updateSubjects(Subjects subjects);

    int deleteAllSubjects(int[] id);

    int deleteSubjects(int idSubjects);

    List<Subjects> searchSubjectsByName(String name);

    Page<Subjects> search(Subjects subjects, Pageable pageable);

}

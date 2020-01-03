package vn.savis.lhm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.savis.lhm.entity.Semester;

@Service
public interface SemesterService {

	List<Semester> findAllSemester();

    Page<Semester> findPaging(Pageable pageable);

    Semester findById(int idSemester);

    int saveCreateSemester(Semester Semester);

    int updateSemester(Semester Semester);

    int deleteAllSemester(int[] id);

    int deleteSemester(int idSemester);

    List<Semester> searchSemesterByName(String name);

    Page<Semester> search(Semester semester, Pageable pageable);

	
}

package vn.savis.lhm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.savis.lhm.entity.Semester;

public interface SemesterRespository extends JpaRepository<Semester, Integer>{
    Semester findByIdSemester(int id);

    @Query(value = "SELECT * FROM semester WHERE name_semester LIKE %:name%", nativeQuery = true)
    List<Semester> findAllByNameSemester(@Param("name") String semesterName);

    Page<Semester> findAll(Specification<Semester> specification, Pageable pageable);

}

package vn.savis.lhm.repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.savis.lhm.entity.Classroom;
import vn.savis.lhm.entity.Subjects;


public  interface SubjectRespository extends JpaRepository<Subjects,Integer>{
	
	Subjects findByIdSubjects(int id);

    @Query(value = "SELECT * FROM subjects WHERE name_subjects LIKE %:name%", nativeQuery = true)
    List<Subjects> findAllByNameSubjects(@Param("name") String subjectsName);
	
    Page<Subjects> findAll(Specification<Subjects> advanceFilter, Pageable pageable);

//    @Query(value = "select * from subjects a\r\n" +
//            "where a.id_subjects not in \r\n" +
//            "	(\r\n" +
//            "	select c.id_subjects\r\n" +
//            "	from registration r inner join subjects c on c.id_subjects = r.id_subjects\r\n" +
//            "					inner join amphitheater a on a.id_amphitheater = c.id_amphitheater\r\n" +
//            "	where r.from_date <= :date and r.to_date <= curdate() and a.id_amphitheater LIKE %:id% \r\n " +
//            "	)",nativeQuery = true)
//    Page<Classroom> findByClassroom(@Param("date") String date,@Param("id") int id ,Pageable pageable);


}
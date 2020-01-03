package vn.savis.lhm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.savis.lhm.entity.Classroom;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: SonNH - SAVIS
 * @created: 9/6/19
 * @Time: 09:50
 * @modified 9/6/19
 **/

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    Classroom findByIdClassroom(int id);

    @Query(value = "SELECT * FROM classroom WHERE name_classroom LIKE %:name%", nativeQuery = true)
    List<Classroom> findAllByNameClassroom(@Param("name") String classRoomName);

    Page<Classroom> findAll(Specification<Classroom> advanceFilter, Pageable pageable);

    List<Classroom> findByAmphitheaterIdAmphitheater(int amphitheaterId);

    @Query(value = "select * from classroom a\r\n" +
            "where a.id_classroom not in \r\n" +
            "	(\r\n" +
            "	select c.id_classroom\r\n" +
            "	from registration r inner join classroom c on c.id_classroom = r.id_classroom\r\n" +
            "					inner join amphitheater a on a.id_amphitheater = c.id_amphitheater\r\n" +
            "	where r.from_date <= :date and r.to_date <= curdate() and a.id_amphitheater LIKE %:id% \r\n " +
            "	)",nativeQuery = true)
    Page<Classroom> findByClassroom(@Param("date") String date,@Param("id") int id ,Pageable pageable);


}

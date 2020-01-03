package vn.savis.lhm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.savis.lhm.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{

	Schedule findByIdSchedule(int id);

    @Query(value = "SELECT * FROM schedule WHERE name_schedule LIKE %:name%", nativeQuery = true)
    List<Schedule> findAllByNameSchedule(@Param("name") String scheduleName);
	
    Page<Schedule> findAll(Specification<Schedule> advanceFilter, Pageable pageable);


}

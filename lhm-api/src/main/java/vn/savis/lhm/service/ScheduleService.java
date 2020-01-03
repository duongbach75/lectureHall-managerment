package vn.savis.lhm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.savis.lhm.entity.Schedule;

public interface ScheduleService {
	List<Schedule> findAllSchedule();

    Page<Schedule> findPaging(Pageable pageable);

    Schedule findById(int idSchedule);

    int saveCreateSchedule(Schedule schedule);

    int updateSchedule(Schedule schedule);

    int deleteAllSchedule(int[] id);

    int deleteSchedule(int idSchedule);

    List<Schedule> searchScheduleByName(String name);

    Page<Schedule> search(Schedule schedule, Pageable pageable);

}

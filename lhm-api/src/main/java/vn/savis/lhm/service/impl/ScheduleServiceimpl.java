package vn.savis.lhm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.savis.lhm.entity.Registration;
import vn.savis.lhm.entity.Schedule;
import vn.savis.lhm.entity.Semester;
import vn.savis.lhm.repository.ScheduleRepository;
import vn.savis.lhm.service.ScheduleService;
import vn.savis.lhm.specification.ScheduleSpecification;
@Service
public class ScheduleServiceimpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public List<Schedule> findAllSchedule() {
		// TODO Auto-generated method stub
		
		try {
			return scheduleRepository.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public Page<Schedule> findPaging(Pageable pageable) {
		// TODO Auto-generated method stub
		try {
			return scheduleRepository.findAll(pageable);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public Schedule findById(int idSchedule) {
		// TODO Auto-generated method stub
		try {
			return scheduleRepository.findByIdSchedule(idSchedule);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
			
		}
	}

	@Override
	public int saveCreateSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		try {
			Schedule s= new Schedule();
			s.setClassroom(schedule.getClassroom());
			s.setIdSchedule(schedule.getIdSchedule());
			s.setStudyTime(schedule.getStudyTime());
			s.setSubjects(schedule.getSubjects());
			s.setCreateTime(new Date());
			s.setCreatedBy("admin");
			scheduleRepository.save(s);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return 0;
			
		}
	}

	@Override
	public int updateSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		try {
			Schedule sjExit = findById(schedule.getIdSchedule());
			
			schedule.setCreatedBy(sjExit.getCreatedBy() );
			schedule.setCreateTime(sjExit.getCreateTime());
			schedule.setUpdateTime(new Date());
			schedule.setUpdatedBy("admin");
			scheduleRepository.save(schedule);
			return 1;
	} catch (Exception e) {
			return 0;
			
			// TODO: handle exception
		}
	}

	@Override
	public int deleteAllSchedule(int[] id) {
		// TODO Auto-generated method stu
		try {
			for (int i = 0; i < id.length; i++) {
				Schedule reg = scheduleRepository.findByIdSchedule(id[i]);
				reg.setUpdatedBy("admin");
				reg.setUpdateTime(new Date());
				reg.setStatuss("0");
				scheduleRepository.save(reg);
				
			}
			return id.length;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}



	@Override
	public int deleteSchedule(int idSchedule) {
		// TODO Auto-generated method stub
		try {
			Schedule old = scheduleRepository.findByIdSchedule(idSchedule);
			if (old != null) {
				old.setUpdatedBy("admin");
				old.setUpdateTime(new Date());
				old.setStatuss("0");
				scheduleRepository.save(old);
				return 1;
			}
			return 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Schedule> searchScheduleByName(String name) {
		// TODO Auto-generated method stub
		try {
			return scheduleRepository.findAllByNameSchedule(name);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
				}
	}

	@Override
	public Page<Schedule> search(Schedule schedule, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Schedule> a=null ;
		try {
			a= scheduleRepository.findAll(ScheduleSpecification.advanceFilter(schedule), pageable);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} 
		return a;
		
	}
	
}

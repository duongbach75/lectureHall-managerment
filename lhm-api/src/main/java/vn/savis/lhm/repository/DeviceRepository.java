package vn.savis.lhm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.savis.lhm.entity.Device;


public interface DeviceRepository extends JpaRepository<Device, Integer> {

	Device findByIdDevice(int id);
	
	Page<Device> findAll(Specification<Device> advanceFilter, Pageable pageable);
	
	Page<Device> findByClassroomIdClassroom(int classroomId, Pageable pageable);

//	List<Device> findByClassRoomClassRoomId(int classroomId, Pageable pageable);

//	Device getById(int classroomId, Pageable pageable);
}

package vn.savis.lhm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.savis.lhm.entity.Device;

import java.util.List;

/**
 * @author: SonNH - SAVIS
 * @created: 9/12/19
 * @Time: 15:23
 * @modified 9/12/19
 **/
public interface DeviceService {
	List<Device> findAll();
	
	Page<Device> findPaging(Pageable pageable);
	
	Device findById(int deviceId);
	
	int create(Device device);
	
	int update(Device device);
	
	Device searchDeviceById(int id);
	
	int deleteAllDevice(Iterable<Device> entities);
	
	int deleteById(int deviceId);
	
	Page<Device> search(Device device, Pageable pageable);

	Page<Device> findByClassroomIdClassroom(int classroomId, Pageable pageable);

//	Device findByClassRoomClassRoomId(int classroomId, Pageable pageable);
}

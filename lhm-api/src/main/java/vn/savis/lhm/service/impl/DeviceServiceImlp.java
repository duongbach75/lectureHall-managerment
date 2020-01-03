package vn.savis.lhm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.savis.lhm.common.constants.CommonConstants;
import vn.savis.lhm.entity.Device;
import vn.savis.lhm.repository.DeviceRepository;
import vn.savis.lhm.service.DeviceService;
import vn.savis.lhm.specification.DeviceSpecifications;

import java.util.Date;
import java.util.List;

/**
 * @author: SonNH - SAVIS
 * @created: 9/12/19
 * @Time: 15:23
 * @modified 9/12/19
 **/
@Service
public class DeviceServiceImlp implements DeviceService {

	@Autowired
	private DeviceRepository deviceRepository;
	
	private Logger LOGGER = LoggerFactory.getLogger(ClassroomServiceImpl.class);
	
	//lấy hết toàn bộ dự liệu
	@Override
	public List<Device> findAll() {
		List<Device> device = null;
		try {
			device = deviceRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return device;
	}
	
	// phân trang
	@Override
	public Page<Device> findPaging(Pageable pageable) {
		Page<Device> device = null;
		try {
			device = deviceRepository.findAll(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return device;
	}

	//thêm dữ liệu 
	@Override
	public int create(Device device) {
		try {
			device.setCreateTime(new Date());
			device.setClassroom(device.getClassroom());
			device.setCreatedBy(CommonConstants.DEFAULT_USER);
			device.setStatuss(CommonConstants.STATUS.TRUE);
			deviceRepository.save(device);
			return 1;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return 0;
		}
	}

	//sửa dữ liệu
	@Override
	public int update(Device device) {
		try {
			Device deviceExisting = deviceRepository.findByIdDevice(device.getIdDevice());
			if(deviceExisting != null) {
				deviceExisting.setNameDevice(device.getNameDevice());
				deviceExisting.setAmount(device.getAmount());
				deviceExisting.setStatuss(device.getStatuss());
				deviceExisting.setClassroom(device.getClassroom());
				deviceExisting.setUpdateTime(new Date());
				deviceExisting.setUpdatedBy(CommonConstants.DEFAULT_USER);
				deviceRepository.save(deviceExisting);
			}
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return 0;
		}
	}

	//lay du lieu cua 1 id
	@Override
	public Device findById(int deviceId) {
		Device device = null;
		try {
			device = deviceRepository.findByIdDevice(deviceId);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return device;
	}

	
	// xoa tat ca
	@Override
	public int deleteAllDevice(Iterable<Device> entities) {
		try {
			deviceRepository.deleteInBatch(entities);
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	
	// xoa theo id
	@Override
	public int deleteById(int deviceId) {
		try {
			deviceRepository.deleteById(deviceId);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return 0;
		}

	}

	@Override
	public Device searchDeviceById(int id) {
		Device device = null;
		try {
			device = deviceRepository.findByIdDevice(id);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return device;
	}

	@Override
	public Page<Device> search(Device device, Pageable pageable) {
		Page<Device> devices = null;
		try {
			devices = deviceRepository.findAll(DeviceSpecifications.advanceFilter(device), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return devices;
	}

	@Override
	public Page<Device> findByClassroomIdClassroom(int classroomId, Pageable pageable) {
		Page<Device> device = null;
		try {
			device = deviceRepository.findByClassroomIdClassroom(classroomId, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return device;
	}


}


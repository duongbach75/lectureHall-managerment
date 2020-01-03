package vn.savis.lhm.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vn.savis.lhm.common.constants.Constants;
import vn.savis.lhm.common.util.Result;
import vn.savis.lhm.dto.ServiceResponse;
import vn.savis.lhm.entity.Device;
import vn.savis.lhm.service.DeviceService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class DeviceResource {
	
	@Autowired
	private DeviceService deviceService;
	
	//lay ra toan bo du lieu theo classroomid
	@GetMapping(value = "/device-management/managed-devices/{classroom-id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody
	ServiceResponse<Page<Device>> getById(@PathVariable("classroom-id") int classroomId, Pageable pageable){
		ServiceResponse<Page<Device>> response = new ServiceResponse<>();

		Page<Device> device = null;

		try {
			device = deviceService.findByClassroomIdClassroom(classroomId, pageable);
			response.setData(device);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}
		return response;
	}

	//phan trang
	@GetMapping(value = "/device-management/managed-devices", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Page<Device>> findPaging(Pageable pageable) {
		ServiceResponse<Page<Device>> response = new ServiceResponse<>();

		Page<Device> device = null;

		try {
			device = deviceService.findPaging(pageable);
			response.setData(device);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}
		return response;
	}

    //GetAll class room
    @GetMapping(value = "/device-management/managed-devices/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public @ResponseBody
    ServiceResponse<List<Device>> findAll() {
        ServiceResponse<List<Device>> response = new ServiceResponse<>();

        List<Device> devices = null;

        try {
            devices = deviceService.findAll();
            response.setData(devices);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MSG);
        } catch (Exception e) {
            // TODO: handle exception
            response.setData(null);
            response.setCode(Constants.ERR_CODE_BAD_REQUEST);
            response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
        }
        return response;
    }

	
	//lay ra du lieu cua mot id
	@GetMapping(value = "/device-management/managed-devices/find-one/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Device> getById1(@PathVariable("id") int id) {
		ServiceResponse<Device> response = new ServiceResponse<>();

		Device device = null;

		try {
			device = deviceService.findById(id);
			response.setData(device);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}
	
	//them du lieu
	@PostMapping(value = "/device-management/managed-devices", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Integer> create(@RequestBody Device device) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if(deviceService.create(device) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
				response.setData(Constants.SUCCESS_CODE_FIELD_UNEXIST);
			} else {
				response.setData(Constants.CAUTION_CODE_FIELD_EXISTED);
				throw new Exception();
			}
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			if(response.getData() == Constants.CAUTION_CODE_FIELD_EXISTED) {
				response.setCode(Constants.ERR_CODE_BAD_REQUEST);
				response.setMessage(Constants.MSG_CAUTION + Result.CODE_IS_EXISTED.getMessage());
			} else {
				response.setData(0);
				response.setCode(Constants.ERR_CODE_BAD_REQUEST);
				response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
			}
		}

		return response;
	}
	
	//sua du lieu
	@PutMapping(value = "/device-management/managed-devices", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Integer> update(@RequestBody Device device) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if(deviceService.update(device) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
				response.setData(Constants.SUCCESS_CODE_FIELD_UNEXIST);
			} else {
				response.setData(Constants.CAUTION_CODE_FIELD_EXISTED);
				throw new Exception();
			}
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			if(response.getData() == Constants.CAUTION_CODE_FIELD_EXISTED) {
				response.setCode(Constants.ERR_CODE_BAD_REQUEST);
				response.setMessage(Constants.MSG_CAUTION + Result.CODE_IS_EXISTED.getMessage());
			} else {
				response.setData(0);
				response.setCode(Constants.ERR_CODE_BAD_REQUEST);
				response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
			}
		}

		return response;
	}
	
	//tim kiem theo id
	@GetMapping(value = "/device-management/managed-devices/search/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Device> searchDeviceById(@PathVariable("id") int id) {
		ServiceResponse<Device> response = new ServiceResponse<>();

		Device device = null;

		try {
			device = deviceService.searchDeviceById(id);
			response.setData(device);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}
	
	@PostMapping(value = "/device-management/managed-devices/advance-search", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Page<Device>> search(@RequestBody Device device, Pageable pageable) {
		ServiceResponse<Page<Device>> response = new ServiceResponse<>();
		
		Page<Device> devices = null;
		
		try {
			devices = deviceService.search(device, pageable);
			response.setData(devices);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}
	
	//xoa nhieu 
	@DeleteMapping(value = "/device-management/managed-devices/delete-all/{entityids}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
		ServiceResponse<Integer> deleteMultiple(@PathVariable("entityids") int[] entityIds) {
			ServiceResponse<Integer> response = new ServiceResponse<>();

		List<Device> device = new ArrayList<Device>();
		for (int i = 0; i < entityIds.length; i++) {
			device.add(deviceService.findById(entityIds[i]));
		}

		int result = deviceService.deleteAllDevice(device);
		if (result == 1) {
			response.setData(result);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} else {
			response.setData(0);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}
		return response;
	}
	
	//xoa theo id
	@DeleteMapping(value = "/device-management/managed-devices/delete/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Integer> delete(@PathVariable("id") int id) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		int result = deviceService.deleteById(id);
		if (result == 1) {
			response.setData(result);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} else {
			response.setData(0);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}

}


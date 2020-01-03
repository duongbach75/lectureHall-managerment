package vn.savis.lhm.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.savis.lhm.common.constants.Constants;
import vn.savis.lhm.common.util.Result;
import vn.savis.lhm.dto.ServiceResponse;
import vn.savis.lhm.entity.Schedule;
import vn.savis.lhm.service.ScheduleService;

@Controller
@RestController
public class ScheduleResource {

	@Autowired 
	private ScheduleService scheduleService;
	
	@GetMapping(value = "/schedule/All", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody 
    ServiceResponse<List<Schedule>> findAll() {
        ServiceResponse<List<Schedule>> response = new ServiceResponse<>();

		List<Schedule> schedule = null;

		try {
			schedule = scheduleService.findAllSchedule();
			response.setData(schedule);
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


	@GetMapping(value = "/schedule/paging", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
ServiceResponse<Page<Schedule>> findPaging(Pageable pageable) {

	    ServiceResponse<Page<Schedule>> response = new ServiceResponse<>();

		Page<Schedule> schedule = null;

		try {
			schedule = scheduleService.findPaging(pageable);
			response.setData(schedule);
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

	@GetMapping(value = "/schedule/findById/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
ServiceResponse<Schedule> findByid(@PathVariable("id") int id) {
    ServiceResponse<Schedule> response = new ServiceResponse<>();

    Schedule Schedule = null;
		try {
			Schedule = scheduleService.findById(id);
			response.setData(Schedule);
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

	@PostMapping(value = "/schedule", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
    ServiceResponse<Integer> create(@RequestBody Schedule schedule) {
        ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if(scheduleService.saveCreateSchedule(schedule) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
				response.setData(Constants.SUCCESS_CODE_FIELD_UNEXIST);
			} else {
				response.setData(Constants.CAUTION_CODE_FIELD_EXISTED);
				throw new Exception();
			}
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
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

	@PutMapping(value = "/schedule", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
    ServiceResponse<Integer> update(@RequestBody Schedule schedule) {
        ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if(scheduleService.updateSchedule(schedule) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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

	@DeleteMapping(value = "/schedule/deleteAll/{ids}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> delete(@PathVariable("ids") int[] ids) {
		if (scheduleService.findAllSchedule() != null) {
			return new ServiceResponse<Integer>(1, "success", scheduleService.deleteAllSchedule(ids));
		} else {
			return new ServiceResponse<Integer>(0, "error", null);
		}
	}

    @DeleteMapping(value = "/schedule/delete/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> delete(@PathVariable("id") int id) {
		if (scheduleService.findAllSchedule() != null) {
			return new ServiceResponse<Integer>(1, "success", scheduleService.deleteSchedule(id));
		} else {
			return new ServiceResponse<Integer>(0, "error", null);
		}
	}

//    private String convertStatus(String status) {
//    if (status.equals("1")) {
//        return "Kích hoạt";
//    } else {
//        return "Không kích hoạt";
//    }
//}
//
	@GetMapping(value = "/schedule/searchScheduleByName/{name}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
    ServiceResponse<List<Schedule>> searchScheduleByName(@PathVariable("name") String name) {
        ServiceResponse<List<Schedule>> response = new ServiceResponse<>();

		List<Schedule> schedule = null;

		try {
			schedule = scheduleService.searchScheduleByName(name);
			response.setData(schedule);
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

    @PostMapping(value = "/schedule/search", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE })
    public @ResponseBody
    ServiceResponse<Page<Schedule>> search(@RequestBody Schedule schedule, Pageable pageable) {
        ServiceResponse<Page<Schedule>> response = new ServiceResponse<>();

        Page<Schedule> sub = null;

        try {
            sub = scheduleService.search(schedule, pageable);
            response.setData(sub);
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


	
}

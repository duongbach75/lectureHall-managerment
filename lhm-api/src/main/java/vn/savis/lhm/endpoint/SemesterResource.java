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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.savis.lhm.common.constants.Constants;
import vn.savis.lhm.common.util.*;
import vn.savis.lhm.dto.ServiceResponse;
import vn.savis.lhm.entity.Semester;
import vn.savis.lhm.service.SemesterService;

@RestController
@Controller
public class SemesterResource {
@Autowired
private SemesterService semesterService;


@GetMapping(value = "/semester/All", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
public @ResponseBody
ServiceResponse<List<Semester>> findAll() {
    ServiceResponse<List<Semester>> response = new ServiceResponse<>();

	List<Semester> semester = null;

	try {
		semester = semesterService.findAllSemester();
		response.setData(semester);
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

@GetMapping(value = "/semester/paging", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
public @ResponseBody
ServiceResponse<Page<Semester>> findPaging(Pageable pageable) {
ServiceResponse<Page<Semester>> response = new ServiceResponse<>();

	Page<Semester> semester = null;

	try {
		semester = semesterService.findPaging(pageable);
		response.setData(semester);
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

@GetMapping(value = "/semester/findById/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
public @ResponseBody
ServiceResponse<Semester> findByid(@PathVariable("id") int id) {
ServiceResponse<Semester> response = new ServiceResponse<>();

Semester semester = null;
	try {
		semester = semesterService.findById(id);
		response.setData(semester);
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

@PostMapping(value = "/semester", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
		MediaType.APPLICATION_JSON_UTF8_VALUE })
public @ResponseBody
ServiceResponse<Integer> create(@RequestBody Semester semester) {
    ServiceResponse<Integer> response = new ServiceResponse<>();
	try {
		if(semesterService.saveCreateSemester(semester) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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

@PutMapping(value = "/semester", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
		MediaType.APPLICATION_JSON_UTF8_VALUE })
public @ResponseBody
ServiceResponse<Integer> update(@RequestBody Semester semester) {
    ServiceResponse<Integer> response = new ServiceResponse<>();
	try {
		if(semesterService.updateSemester(semester) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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

@DeleteMapping(value = "/semester/deleteAll/{ids}", produces = {
		MediaType.APPLICATION_JSON_UTF8_VALUE })
public ServiceResponse<Integer> delete(@PathVariable("ids") int[] ids) {
	if (semesterService.findAllSemester() != null) {
		return new ServiceResponse<Integer>(1, "success", semesterService.deleteAllSemester(ids));
	} else {
		return new ServiceResponse<Integer>(0, "error", null);
	}
}

@DeleteMapping(value = "/semester/delete/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
public ServiceResponse<Integer> delete(@PathVariable("id") int id) {
	if (semesterService.findAllSemester() != null) {
		return new ServiceResponse<Integer>(1, "success", semesterService.deleteSemester(id));
	} else {
		return new ServiceResponse<Integer>(0, "error", null);
	}
}


@GetMapping(value = "/semester/searchSemesterByName", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE })
public @ResponseBody
ServiceResponse<List<Semester>> searchSemesterByName(@RequestParam String name) {
    ServiceResponse<List<Semester>> response = new ServiceResponse<>();

	List<Semester> semester = null;

	try {
		semester = semesterService.searchSemesterByName(name);
		response.setData(semester);
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

@PostMapping(value = "/semester/search", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
        MediaType.APPLICATION_JSON_UTF8_VALUE })
public @ResponseBody
ServiceResponse<Page<Semester>> search(@RequestBody Semester semester, Pageable pageable) {
    ServiceResponse<Page<Semester>> response = new ServiceResponse<>();

    Page<Semester> sub = null;

    try {
        sub = semesterService.search(semester, pageable);
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

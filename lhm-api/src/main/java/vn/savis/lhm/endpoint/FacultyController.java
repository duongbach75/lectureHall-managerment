package vn.savis.lhm.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.savis.lhm.common.constants.Constants;
import vn.savis.lhm.common.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.savis.lhm.dto.ServiceResponse;
import vn.savis.lhm.entity.Amphitheater;
import vn.savis.lhm.entity.Classroom;
import vn.savis.lhm.entity.Faculty;
import vn.savis.lhm.repository.FacultyRepository;
import vn.savis.lhm.service.FacultyService;
import vn.savis.lhm.specification.FacultySpecificationsBuilder;

@Controller
@RequestMapping("/api/v1/categories")
public class FacultyController {
	long millis = System.currentTimeMillis();
	java.util.Date created_at = new java.util.Date(millis);
	java.util.Date updated_at = new java.util.Date(millis);

	@Autowired
	FacultyService facultyService;

	@Autowired
	FacultyRepository facultyRepository;

	// lay danh sach all giang vien
	@GetMapping(value = "/demo/faculty/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<List<Faculty>> findAll() {
		ServiceResponse<List<Faculty>> response = new ServiceResponse<>();

		List<Faculty> faculty = null;

		try {
			faculty = facultyService.findAll();
			response.setData(faculty);
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

	// tim kiem giang vien theo id
	@GetMapping(value = "demo/faculty/find/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Faculty> findById(@PathVariable("id") int id) {
		ServiceResponse<Faculty> response = new ServiceResponse<>();

		Faculty faculty = null;

		try {
			faculty = facultyService.findOne(id);
			response.setData(faculty);
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

	// lay all giang vien co phan trang
	@GetMapping(value = "demo/list/faculty/pading", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Page<Faculty>> findPage(Pageable pageable) {
		ServiceResponse<Page<Faculty>> response = new ServiceResponse<>();

		Page<Faculty> faculty = null;

		try {
			faculty = facultyService.findPaging(pageable);
			response.setData(faculty);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {

			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}

	// them moi giang vien
	@PostMapping(value = "demo/faculty/createorupdate", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Integer> create(@RequestBody Faculty faculty) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (facultyService.create(faculty) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
				response.setData(Constants.SUCCESS_CODE_FIELD_UNEXIST);
			} else {
				response.setData(Constants.CAUTION_CODE_FIELD_EXISTED);
				throw new Exception();
			}
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			if (response.getData() == Constants.CAUTION_CODE_FIELD_EXISTED) {
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

	// edit giang vien
	@PutMapping(value = "demo/faculty/update", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Integer> update(@RequestBody Faculty faculty) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (facultyService.update(faculty) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
				response.setData(Constants.SUCCESS_CODE_FIELD_UNEXIST);
			} else {
				response.setData(Constants.CAUTION_CODE_FIELD_EXISTED);
				throw new Exception();
			}
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			if (response.getData() == Constants.CAUTION_CODE_FIELD_EXISTED) {
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

	// xoa giang vien
	@DeleteMapping(value = "demo/delete/faculty/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody

		ServiceResponse<Integer> delete(@PathVariable("id") int id) {
		ServiceResponse<Integer> response = new ServiceResponse<>();

		int result = facultyService.delete(id);
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

	// tim kiem giang vien theo nhieu tieu chi
	@GetMapping(value = "search/faculty", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public List<Faculty> search(@RequestParam(value = "search") String search) {
		FacultySpecificationsBuilder builder = new FacultySpecificationsBuilder();
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}

		Specification<Faculty> spec = builder.build();
		return facultyRepository.findAll(spec);
	}

	// xoa nhieu giang vien
	@DeleteMapping(value = "faculty/deleteAll/{entityIds}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Integer> deleteMultiple(@PathVariable("entityIds") int[] entityIds) {
		ServiceResponse<Integer> response = new ServiceResponse<>();

		List<Faculty> faculty = new ArrayList<Faculty>();
		for (int i = 0; i < entityIds.length; i++) {
			faculty.add(facultyService.findOne(entityIds[i]));
		}

		int result = facultyService.deleteAllFaculty(faculty);
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

	// lay all giang vien theo khoa
	@GetMapping(value = "/faculty/subjectstype/{subjectstypeId}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<List<Faculty>> findBySubjectsTypeId(@PathVariable("subjectstypeId") int id) {
		ServiceResponse<List<Faculty>> response = new ServiceResponse<>();

		List<Faculty> faculty = null;

		try {
			faculty = facultyService.findBySubjectsTypeId(id);
			response.setData(faculty);
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

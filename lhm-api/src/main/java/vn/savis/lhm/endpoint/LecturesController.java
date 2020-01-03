package vn.savis.lhm.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import vn.savis.lhm.common.constants.Constants;
import vn.savis.lhm.common.util.Result;
import vn.savis.lhm.dto.ServiceResponse;
import vn.savis.lhm.entity.Faculty;
import vn.savis.lhm.entity.Lecturers;
import vn.savis.lhm.repository.LecturersRepository;
import vn.savis.lhm.service.LecturesService;
import vn.savis.lhm.specification.LecturersSpecificationsBuilder;

@Controller
@RequestMapping("/api/v1/categories")
public class LecturesController {
	long millis = System.currentTimeMillis();
	java.util.Date created_at = new java.util.Date(millis);
	java.util.Date updated_at = new java.util.Date(millis);
	
	@Autowired 
	LecturesService lecturersService;
	
	@Autowired
	LecturersRepository lecturersRepository;
	
	
	//lay danh sach all bai giang
	@GetMapping(value = "/demo/lecturers/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<List<Lecturers>> findAll() {
		ServiceResponse<List<Lecturers>> response = new ServiceResponse<>();

		List<Lecturers> lecturers = null;

		try {
			lecturers = lecturersService.findAll();
			response.setData(lecturers);
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
	// tim kiem bai giảng theo id
	@GetMapping(value = "demo/lecturers/find/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Lecturers> findById(@PathVariable("id") int id) {
		ServiceResponse<Lecturers> response = new ServiceResponse<>();

		Lecturers lecturers = null;

		try {
			lecturers = lecturersService.findOne(id);
			response.setData(lecturers);
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

	//lay danh sach bai giang co phan trang
	@GetMapping(value = "demo/list/lecturers/pading", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Page<Lecturers>> findPage(Pageable pageable) {
		ServiceResponse<Page<Lecturers>> response = new ServiceResponse<>();

		Page<Lecturers> lecturers = null;

		try {
			lecturers = lecturersService.findPaging(pageable);
			response.setData(lecturers);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {

			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}
	//them moi bai giang
	@PostMapping(value = "demo/lecturers/createorupdate", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Integer> create(@RequestBody Lecturers lecturers) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (lecturersService.create(lecturers) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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
	//edit bai giang
	@PutMapping(value = "demo/lecturers/update", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Integer> update(@RequestBody Lecturers lecturers) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (lecturersService.update(lecturers) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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
	
	//xoa 1 bai giang
	@DeleteMapping(value = "demo/delete/lecturers/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Integer> delete(@PathVariable("id") int id) {
			ServiceResponse<Integer> response = new ServiceResponse<>();
			int result = lecturersService.delete(id);
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

	//tim kiem bai giang nhieu tieu chi
    @GetMapping( value = "search/lecturers", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseBody
    public List<Lecturers> search(@RequestParam(value = "search") String search) {
    	LecturersSpecificationsBuilder builder = new LecturersSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
         
        Specification<Lecturers> spec = builder.build();
        return lecturersRepository.findAll(spec);
    }
    //xoa nhieu bai giang
	@DeleteMapping(value = "lecturees/deleteAll/{entityIds}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Integer> deleteMultiple(@PathVariable("entityIds") int[] entityIds) {
		ServiceResponse<Integer> response = new ServiceResponse<>();

		List<Lecturers> lecturers = new ArrayList<Lecturers>();
		for (int i = 0; i < entityIds.length; i++) {
			lecturers.add(lecturersService.findOne(entityIds[i]));
		}

		int result = lecturersService.deleteAllLecturers(lecturers);
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
	//tim theo id giang vien
	@GetMapping(value = "/lecturers/faculty/{facultyId}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
    ServiceResponse<List<Lecturers>> findByFacultyId(@PathVariable("facultyId") int id) {
        ServiceResponse<List<Lecturers>> response = new ServiceResponse<>();

		List<Lecturers> lecturers = null;

		try {
			lecturers = lecturersService.findByFacultyId(id);
			response.setData(lecturers);
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



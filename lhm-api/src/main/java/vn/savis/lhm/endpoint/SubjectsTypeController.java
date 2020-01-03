package vn.savis.lhm.endpoint;

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
import vn.savis.lhm.entity.Lecturers;
import vn.savis.lhm.entity.SubjectsType;
import vn.savis.lhm.repository.SubjectsTypeRepository;
import vn.savis.lhm.service.SubjectsTypeService;
import vn.savis.lhm.specification.SubjectsTypeSpecificationsBuilder;

@Controller
@RequestMapping("/api/v1/categories")
public class SubjectsTypeController {
	long millis = System.currentTimeMillis();
	java.util.Date created_at = new java.util.Date(millis);
	java.util.Date updated_at = new java.util.Date(millis);
	
	@Autowired 
	SubjectsTypeService subjectsTypeService;
	
	@Autowired
	SubjectsTypeRepository subjectsTypeRepository;
	
	
	//lay danh sach khoa
	@GetMapping(value = "/demo/subjectstype/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<List<SubjectsType>> findAll() {
		ServiceResponse<List<SubjectsType>> response = new ServiceResponse<>();

		List<SubjectsType> subjectstype = null;

		try {
			subjectstype = subjectsTypeService.findAll();
			response.setData(subjectstype);
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
	//tim kiem khoa theo id
	@GetMapping(value = "demo/subjectstype/find/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<SubjectsType> findById(@PathVariable("id") int id) {
		ServiceResponse<SubjectsType> response = new ServiceResponse<>();

		SubjectsType subjectstype = null;

		try {
			subjectstype = subjectsTypeService.findOne(id);
			response.setData(subjectstype);
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
	//lay danh sach khoa co phan trang
	@GetMapping(value = "demo/list/subjectstype/pading", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Page<SubjectsType>> findPage(Pageable pageable) {
		ServiceResponse<Page<SubjectsType>> response = new ServiceResponse<>();

		Page<SubjectsType> subjectstype = null;

		try {
			subjectstype = subjectsTypeService.findPaging(pageable);
			response.setData(subjectstype);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {

			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}

		return response;
	}
	//them moi khoa
	@PostMapping(value = "demo/subjectstype/createorupdate", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })

	public @ResponseBody ServiceResponse<Integer> create(@RequestBody SubjectsType subjectstype) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (subjectsTypeService.create(subjectstype) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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
	//sua khoa
	@PutMapping(value = "demo/subjectstype/update", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Integer> update(@RequestBody SubjectsType subjectstype) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (subjectsTypeService.update(subjectstype) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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
	
	//xoa khoa
	@DeleteMapping(value = "demo/delete/subjectstype/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ServiceResponse<Integer> delete(@PathVariable("id") int id) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		int result = subjectsTypeService.delete(id);
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
	//tim kiem khoa voi nhieu chi tieu
    @GetMapping( value = "search/subjectstype", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseBody
    public List<SubjectsType> search(@RequestParam(value = "search") String search) {
    	SubjectsTypeSpecificationsBuilder builder = new SubjectsTypeSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
         
        Specification<SubjectsType> spec = builder.build();
        return subjectsTypeRepository.findAll(spec);
    }
}

package vn.savis.lhm.endpoint;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.savis.lhm.common.constants.Constants;
import vn.savis.lhm.common.util.Result;
import vn.savis.lhm.dto.ServiceResponse;
import vn.savis.lhm.entity.Registration;
import vn.savis.lhm.entity.RegistrationType;
import vn.savis.lhm.service.RegistrationTypeService;
import vn.savis.lhm.specification.RegistrationTypeSpecificationsBuilder;


@RestController
@RequestMapping("/api/v1/categories")
public class RegistrationTypeResource {
	
	@Autowired
	private RegistrationTypeService registrationTypeService;
	
	@GetMapping(value = "/manage/registrationType/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<List<RegistrationType>> findAll() {

		ServiceResponse<List<RegistrationType>> response = new ServiceResponse<>();

		List<RegistrationType> registrationType = null;

		try {
			registrationType = registrationTypeService.findAll();
			response.setData(registrationType);
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



	@GetMapping(value = "/manage/registrationType/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<RegistrationType> findById(@PathVariable("id") int id) {
		ServiceResponse<RegistrationType> response = new ServiceResponse<>();

		RegistrationType registrationType = null;

		try {
			registrationType = registrationTypeService.findOne(id);
			response.setData(registrationType);
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

	@GetMapping(value = "/manage/registrationType/paging", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Page<RegistrationType>> findPage(Pageable pageable) {
		ServiceResponse<Page<RegistrationType>> response = new ServiceResponse<>();

		Page<RegistrationType> registrationTypes = null;

		try {
			registrationTypes = registrationTypeService.findPaging(pageable);
			response.setData(registrationTypes);
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

	@PostMapping(value = "/manage/registrationType", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> create(@RequestBody RegistrationType registrationType) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (registrationTypeService.create(registrationType) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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
				response.setMessage(
						Constants.MSG_CAUTION + Result.CODE_IS_EXISTED.getMessage());
			} else {
				response.setData(0);
				response.setCode(Constants.ERR_CODE_BAD_REQUEST);
				response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
			}
		}

		return response;
	}

	@PutMapping(value = "/manage/registrationType", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> update(@RequestBody RegistrationType registrationType) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (registrationTypeService.update(registrationType) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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
				response.setMessage(
						Constants.MSG_CAUTION + Result.CODE_IS_EXISTED.getMessage());
			} else {
				response.setData(0);
				response.setCode(Constants.ERR_CODE_BAD_REQUEST);
				response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
			}
		}

		return response;
	}

	@DeleteMapping(value = "/manage/registrationType/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> delete(@PathVariable("id") int id) {
		ServiceResponse<Integer> response = new ServiceResponse<>();

		int result = registrationTypeService.delete(id);
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
	
	@DeleteMapping(value = "/manage/registrationType/delete-muti/{ids}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> delete(@PathVariable("ids") int[] ids) {
ServiceResponse<Integer> response = new ServiceResponse<>();
		
		int result = registrationTypeService.deleteInBatch(ids);
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

	// advance-search
	@GetMapping(value = "/manage/registrationType/advance-search", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Page<RegistrationType>> getProducs( Pageable pageable, String search) {
		RegistrationTypeSpecificationsBuilder builder = new RegistrationTypeSpecificationsBuilder();
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}
		Specification<RegistrationType> spec = builder.build();
		
		ServiceResponse<Page<RegistrationType>> response = new ServiceResponse<>();
		
		Page<RegistrationType> registrations = null;

		try {
			registrations = registrationTypeService.findAll(spec, pageable);
			response.setData(registrations);
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

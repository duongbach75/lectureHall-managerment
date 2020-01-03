package vn.savis.lhm.endpoint;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import vn.savis.lhm.entity.Amphitheater;
import vn.savis.lhm.entity.Registration;
import vn.savis.lhm.service.RegistrationService;
import vn.savis.lhm.specification.RegistrationSpecificationsBuilder;

@RestController
@RequestMapping("/api/v1/categories")
public class RegistrationResource {

	@Autowired
	private RegistrationService registrationService;

	@GetMapping(value = "/manage/registration/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<List<Registration>> findAll() {

		ServiceResponse<List<Registration>> response = new ServiceResponse<>();

		List<Registration> amphitheaters = null;

		try {
			amphitheaters = registrationService.findAll();
			response.setData(amphitheaters);
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

	@GetMapping(value = "/manage/registration/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Registration> findById(@PathVariable("id") int id) {
		ServiceResponse<Registration> response = new ServiceResponse<>();

		Registration registration = null;

		try {
			registration = registrationService.findOne(id);
			response.setData(registration);
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

	@GetMapping(value = "/manage/registration/paging", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Page<Registration>> findPage(Pageable pageable) {
		ServiceResponse<Page<Registration>> response = new ServiceResponse<>();

		Page<Registration> registrations = null;

		try {
			registrations = registrationService.findPaging(pageable);
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

	//create new
	@PostMapping(value = "/manage/registration", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> create(@RequestBody Registration registration) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (registrationService.create(registration) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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

	@PutMapping(value = "/manage/registration", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> update(@RequestBody Registration registration) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (registrationService.update(registration) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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

	@DeleteMapping(value = "/manage/registration/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> delete(@PathVariable("id") int id) {
		ServiceResponse<Integer> response = new ServiceResponse<>();

		int result = registrationService.delete(id);
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

	@DeleteMapping(value = "/manage/registration/delete-muti/{ids}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> delete(@PathVariable("ids") int[] ids) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		
		int result = registrationService.deleteInBatch(ids);
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

	@GetMapping(value = "/manage/registration/advance-search", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Page<Registration>> getProducs(Pageable pageable, String search) {
		RegistrationSpecificationsBuilder builder = new RegistrationSpecificationsBuilder();
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}
		Specification<Registration> spec = builder.build();
		
		ServiceResponse<Page<Registration>> response = new ServiceResponse<>();

		Page<Registration> registrations = null;

		try {
			registrations = registrationService.findAll(spec, pageable);
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

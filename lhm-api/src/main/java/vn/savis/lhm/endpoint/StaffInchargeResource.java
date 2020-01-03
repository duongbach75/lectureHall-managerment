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
import vn.savis.lhm.entity.StaffIncharge;
import vn.savis.lhm.service.StaffInchargeService;
import vn.savis.lhm.specification.StaffInchargeSpecificationsBuilder;

@RestController
@RequestMapping("/api/v1/categories")
public class StaffInchargeResource {

	@Autowired
	private StaffInchargeService staffInchargeService;

	@GetMapping(value = "/manage/staffIncharge/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<List<StaffIncharge>> findAll() {

		ServiceResponse<List<StaffIncharge>> response = new ServiceResponse<>();

		List<StaffIncharge> staffIncharge = null;

		try {
			staffIncharge = staffInchargeService.findAll();
			response.setData(staffIncharge);
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

	@GetMapping(value = "/manage/staffIncharge/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<StaffIncharge> findById(@PathVariable("id") int id) {
		ServiceResponse<StaffIncharge> response = new ServiceResponse<>();

		StaffIncharge staffIncharge = null;

		try {
			staffIncharge = staffInchargeService.findOne(id);
			response.setData(staffIncharge);
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

	@GetMapping(value = "/manage/staffIncharge/paging", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Page<StaffIncharge>> findPage(Pageable pageable) {
		ServiceResponse<Page<StaffIncharge>> response = new ServiceResponse<>();

		Page<StaffIncharge> staffIncharges = null;

		try {
			staffIncharges = staffInchargeService.findPaging(pageable);
			response.setData(staffIncharges);
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

	// create new
	@PostMapping(value = "/manage/staffIncharge", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> create(@RequestBody StaffIncharge staffIncharge) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (staffInchargeService.create(staffIncharge) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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

	// update
	@PutMapping(value = "/manage/staffIncharge", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> update(@RequestBody StaffIncharge staffIncharge) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (staffInchargeService.update(staffIncharge) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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

	// delete a staff by id
	@DeleteMapping(value = "/manage/staffIncharge/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> delete(@PathVariable("id") int id) {
		ServiceResponse<Integer> response = new ServiceResponse<>();

		int result = staffInchargeService.delete(id);
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

	// delete mutiple by ids
	@DeleteMapping(value = "/manage/staffIncharge/delete-muti/{ids}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> delete(@PathVariable("ids") int[] ids) {
		ServiceResponse<Integer> response = new ServiceResponse<>();

		int result = staffInchargeService.deleteInBatch(ids);
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

	@GetMapping(value = "/manage/staffIncharge/advance-search", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Page<StaffIncharge>> getProducs(Pageable pageable, String search) {
		StaffInchargeSpecificationsBuilder builder = new StaffInchargeSpecificationsBuilder();
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}
		Specification<StaffIncharge> spec = builder.build();
		ServiceResponse<Page<StaffIncharge>> response = new ServiceResponse<>();

		Page<StaffIncharge> staffIncharges = null;

		try {
			staffIncharges = staffInchargeService.findAll(spec, pageable);
			response.setData(staffIncharges);
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

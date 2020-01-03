package vn.savis.lhm.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.savis.lhm.common.constants.Constants;
import vn.savis.lhm.common.util.Result;
import vn.savis.lhm.dto.ServiceResponse;
import vn.savis.lhm.entity.Amphitheater;
import vn.savis.lhm.service.AmphitheaterService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: SonNH - SAVIS
 * @created: 9/6/19
 * @Time: 09:50
 * @modified 9/6/19
 **/


@RestController
@RequestMapping("/api/v1/categories")
public class AmphitheaterResource {
	@Autowired
	private AmphitheaterService amphitheaterService;
    //Testok
	//searchbystatusandname
	@GetMapping(value = "/amphitheater-management/managed-amphitheater/search/{name}/{status}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Page<Amphitheater>> searchAllAmphitheater(@PathVariable String name,
															  @PathVariable String status, Pageable pageable) {
		ServiceResponse<Page<Amphitheater>> response = new ServiceResponse<>();

		Page<Amphitheater> amphitheaters = null;

		try {
			amphitheaters = amphitheaterService.searchAllAmphitheater(name, status, pageable);
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

    //testok
	@GetMapping(value = "/amphitheater-management/managed-amphitheater/search-name/{name}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Page<Amphitheater>> searchNameAmphitheater(@PathVariable String name,
                                                               Pageable pageable) {
		ServiceResponse<Page<Amphitheater>> response = new ServiceResponse<>();

		Page<Amphitheater> amphitheaters = null;

		try {
			amphitheaters = amphitheaterService.searchNameAmphitheater(name, pageable);
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
    //testok
	@GetMapping(value = "/amphitheater-management/managed-amphitheater/search-status/{status}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Page<Amphitheater>> searchStatusAmphitheater(@PathVariable String status,
                                                                 Pageable pageable) {
		ServiceResponse<Page<Amphitheater>> response = new ServiceResponse<>();

		Page<Amphitheater> amphitheaters = null;

		try {
			amphitheaters = amphitheaterService.searchStatusAmphitheater(status, pageable);
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
	//testok
	//select page db in amphitheater
	@GetMapping(value = "/amphitheater-management/managed-amphitheater", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Page<Amphitheater>> getAllAmphitheater(Pageable pageable) {
		ServiceResponse<Page<Amphitheater>> response = new ServiceResponse<>();

		Page<Amphitheater> amphitheaters = null;

		try {
			amphitheaters = amphitheaterService.findPaging(pageable);
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
    //testok
	//create new Amphitheater
	@PostMapping(value = "/amphitheater-management/managed-amphitheater", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Integer> create(@RequestBody Amphitheater amphitheaters) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (amphitheaterService.create(amphitheaters) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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
    //test ok
	// get all list Amphitheater
	@GetMapping(value = "/amphitheater-management/managed-amphitheater/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<List<Amphitheater>> getListAmphitheater() {
		ServiceResponse<List<Amphitheater>> response = new ServiceResponse<>();

		List<Amphitheater> amphitheaters = null;

		try {
			amphitheaters = amphitheaterService.findAll();
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

	@GetMapping(value = "/amphitheater-management/managed-amphitheater/find-id/{id}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Amphitheater> findOne(@PathVariable("id") int id) {
		ServiceResponse<Amphitheater> response = new ServiceResponse<>();

		Amphitheater amphitheaters = null;

		try {
			amphitheaters = amphitheaterService.findOne(id);
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

	/**
	 * update
	 */
	@PutMapping(value = "/amphitheater-management/managed-amphitheater", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Integer> update(@RequestBody Amphitheater amphitheaters) {
		ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if (amphitheaterService.update(amphitheaters) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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

	/**
	 * delete by id
	 */
	@DeleteMapping(value = "/amphitheater-management/managed-amphitheater/delete/{id}", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Integer> delete(@PathVariable("id") int id) {
		ServiceResponse<Integer> response = new ServiceResponse<>();

		int result = amphitheaterService.delete(id);
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

	/**
	 * delete multiple
	 */
	@DeleteMapping(value = "/amphitheater-management/managed-amphitheater/delete-multiple/{entityids}", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Integer> deleteMultiple(@PathVariable("entityids") int[] entityIds) {
		ServiceResponse<Integer> response = new ServiceResponse<>();

		List<Amphitheater> amphitheaters = new ArrayList<Amphitheater>();
		for (int i = 0; i < entityIds.length; i++) {
			amphitheaters.add(amphitheaterService.findOne(entityIds[i]));
		}

		int result = amphitheaterService.deleteAllBatch(amphitheaters);
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



	// tim kiem
	@PostMapping(value = "/amphitheater-management/managed-amphitheater/advance-search", consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
	ServiceResponse<Page<Amphitheater>> search(@RequestBody Amphitheater amphitheater,
                                               Pageable pageable) {
		ServiceResponse<Page<Amphitheater>> response = new ServiceResponse<>();

		Page<Amphitheater> amphitheaters = null;

		try {
			amphitheaters = amphitheaterService.search(amphitheater, pageable);
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

}

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
import vn.savis.lhm.common.util.Result;
import vn.savis.lhm.dto.ServiceResponse;
import vn.savis.lhm.entity.Subjects;
import vn.savis.lhm.service.SubjectsService;

@Controller
@RestController
public class SubjectsResource {


	@Autowired
	private SubjectsService subjectsService;
	
	@GetMapping(value = "/subjects/All", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
    ServiceResponse<List<Subjects>> findAll() {
        ServiceResponse<List<Subjects>> response = new ServiceResponse<>();

		List<Subjects> subjects = null;

		try {
            subjects = subjectsService.findAllSubjects();
			response.setData(subjects);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO: handle exception
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
			e.printStackTrace();
		}

		return response;
	}

	@GetMapping(value = "/subjects/paging", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
ServiceResponse<Page<Subjects>> findPaging(Pageable pageable) {
    ServiceResponse<Page<Subjects>> response = new ServiceResponse<>();

		Page<Subjects> subjects = null;

		try {
			subjects = subjectsService.findPaging(pageable);
			response.setData(subjects);
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

	@GetMapping(value = "/subjects/findById/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
ServiceResponse<Subjects> findByid(@PathVariable("id") int id) {
    ServiceResponse<Subjects> response = new ServiceResponse<>();

    Subjects subjects = null;
		try {
			subjects = subjectsService.findById(id);
			response.setData(subjects);
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

	@PostMapping(value = "/subjects", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
    ServiceResponse<Integer> create(@RequestBody Subjects subjects) {
        ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if(subjectsService.saveCreateSubjects(subjects) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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

	@PutMapping(value = "/subjects", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
    ServiceResponse<Integer> update(@RequestBody Subjects subjects) {
        ServiceResponse<Integer> response = new ServiceResponse<>();
		try {
			if(subjectsService.updateSubjects(subjects) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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
	@DeleteMapping(value = "/subjects/deleteAll/{ids}", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> delete(@PathVariable("ids") int[] ids) {
		if (subjectsService.findAllSubjects() != null) {
			return new ServiceResponse<Integer>(1, "success", subjectsService.deleteAllSubjects(ids));
		} else {
			return new ServiceResponse<Integer>(0, "error", null);
		}
	}

    @DeleteMapping(value = "/schedule/subjects/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ServiceResponse<Integer> delete(@PathVariable("id") int id) {
		if (subjectsService.findAllSubjects() != null) {
			return new ServiceResponse<Integer>(1, "success", subjectsService.deleteSubjects(id));
		} else {
			return new ServiceResponse<Integer>(0, "error", null);
		}
	}

	@GetMapping(value = "/subjects/searchSubjectsByName", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody
    ServiceResponse<List<Subjects>> searchSubjectsByName(@RequestParam String name) {
        ServiceResponse<List<Subjects>> response = new ServiceResponse<>();

		List<Subjects> subjects = null;

		try {
			subjects = subjectsService.searchSubjectsByName(name);
			response.setData(subjects);
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

    @PostMapping(value = "/subjects/search", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE })
    public @ResponseBody
    ServiceResponse<Page<Subjects>> search(@RequestBody Subjects subjects, Pageable pageable) {
        ServiceResponse<Page<Subjects>> response = new ServiceResponse<>();

        Page<Subjects> sub = null;

        try {
            sub = subjectsService.search(subjects, pageable);
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

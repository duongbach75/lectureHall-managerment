package vn.savis.lhm.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vn.savis.lhm.common.constants.Constants;
import vn.savis.lhm.common.util.Result;
import vn.savis.lhm.dto.ServiceResponse;
import vn.savis.lhm.entity.Classroom;
import vn.savis.lhm.service.ClassroomService;

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
public class ClassroomResource {
	
	@Autowired
	private ClassroomService classroomService;
        //GetAll class room
		@GetMapping(value = "/classroom-management/managed-classroom/all", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
		public @ResponseBody
        ServiceResponse<List<Classroom>> findAll() {
            ServiceResponse<List<Classroom>> response = new ServiceResponse<>();

			List<Classroom> classroom = null;

			try {
                classroom = classroomService.findAllClassroom();
				response.setData(classroom);
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
		//Get class room by amphitheaterId
		@GetMapping(value = "/classroom-management/managed-classroom/amphitheater/{amphitheater-id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
		public @ResponseBody
        ServiceResponse<List<Classroom>> findByAmphitheaterAmphitheaterId(@PathVariable("amphitheater-id") int amphitheaterId) {
            ServiceResponse<List<Classroom>> response = new ServiceResponse<>();

			List<Classroom> classroom = null;

			try {
				classroom = classroomService.findByAmphitheaterAmphitheaterId(amphitheaterId);
				response.setData(classroom);
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
		//paging
		@GetMapping(value = "/classroom-management/managed-classroom", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
			public @ResponseBody
        ServiceResponse<Page<Classroom>> findPaging(Pageable pageable) {
            ServiceResponse<Page<Classroom>> response = new ServiceResponse<>();

				Page<Classroom> classroom = null;

				try {
					classroom = classroomService.findPaging(pageable);
					response.setData(classroom);
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

		//findClassroomByID
        @GetMapping(value = "/classroom-management/managed-classroom/find-id/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
        public @ResponseBody
        ServiceResponse<Classroom> findOne(@PathVariable("id") int id) {
            ServiceResponse<Classroom> response = new ServiceResponse<>();

				Classroom classroom = null;
				try {
					classroom = classroomService.findById(id);
					response.setData(classroom);
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

		//create data class room
		@PostMapping(value = "/classroom-management/managed-classroom", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
				MediaType.APPLICATION_JSON_UTF8_VALUE })
		public @ResponseBody
        ServiceResponse<Integer> create(@RequestBody Classroom classroom) {
            ServiceResponse<Integer> response = new ServiceResponse<>();
			try {
				if(classroomService.saveCreateClassroom(classroom) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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

		//edit data class room
		@PutMapping(value = "/classroom-management/managed-classroom", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
				MediaType.APPLICATION_JSON_UTF8_VALUE })
		public @ResponseBody
        ServiceResponse<Integer> update(@RequestBody Classroom classroom) {
            ServiceResponse<Integer> response = new ServiceResponse<>();
			try {
				if(classroomService.updateClassroom(classroom) == Constants.SUCCESS_CODE_FIELD_UNEXIST) {
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

		//tim kiem theo ten
		@GetMapping(value = "/classroom-management/managed-classroom/search-name/{name}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE })
		public @ResponseBody
        ServiceResponse<List<Classroom>> searchClassroomByName(@PathVariable("name") String name) {
            ServiceResponse<List<Classroom>> response = new ServiceResponse<>();

			List<Classroom> classroom = null;

			try {
				classroom = classroomService.searchClassroomByName(name);
				response.setData(classroom);
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

		//xoa tat ca theo id
		@DeleteMapping(value = "/classroom-management/managed-classroom/delete-all/{entityids}", produces = {
					MediaType.APPLICATION_JSON_UTF8_VALUE })
		public @ResponseBody
        ServiceResponse<Integer> deleteMultiple(@PathVariable("entityids") int[] entityIds) {
            ServiceResponse<Integer> response = new ServiceResponse<>();

			List<Classroom> classroom = new ArrayList<Classroom>();
			for (int i = 0; i < entityIds.length; i++) {
				classroom.add(classroomService.findById(entityIds[i]));
			}

			int result = classroomService.deleteAllClassroom(classroom);
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

		//deleteClassroomByID
        @DeleteMapping(value = "/classroom-management/managed-classroom/delete/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
        public @ResponseBody
        ServiceResponse<Integer> delete(@PathVariable("id") int id) {
            ServiceResponse<Integer> response = new ServiceResponse<>();
            int result = classroomService.deleteClassroom(id);
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

    //tim kiem
    @PostMapping(value = "/classroom-management/managed-classroom/advance-search", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE })
    public @ResponseBody
    ServiceResponse<Page<Classroom>> search(@RequestBody Classroom classroom, Pageable pageable) {
        ServiceResponse<Page<Classroom>> response = new ServiceResponse<>();

        Page<Classroom> classrooms = null;

        try {
            classrooms = classroomService.search(classroom, pageable);
            response.setData(classrooms);
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


    @GetMapping(value = "/classroom-management/managed-classroom/search-date/{date}/{id}", produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE })
    public @ResponseBody
    ServiceResponse<Page<Classroom>> findByClassroom(@PathVariable("date") String date, @PathVariable("id") int id, Pageable pageable) {
        ServiceResponse<Page<Classroom>> response = new ServiceResponse<>();

        Page<Classroom> classrooms = null;

        try {
            classrooms = classroomService.findByClassroom(date, id, pageable);
            response.setData(classrooms);
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

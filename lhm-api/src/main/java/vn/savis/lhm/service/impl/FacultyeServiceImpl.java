package vn.savis.lhm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.savis.lhm.common.constants.CommonConstants;
import vn.savis.lhm.entity.Faculty;
import vn.savis.lhm.repository.FacultyRepository;
import vn.savis.lhm.service.FacultyService;

@Service
public class FacultyeServiceImpl implements FacultyService {
	
	@Autowired
	FacultyRepository facultyRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	private Logger log = LoggerFactory.getLogger(FacultyeServiceImpl.class);
	

	@Override
	public List<Faculty> findAll() {
		try {
			return facultyRepository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Page<Faculty> findPaging(Pageable pageable) {
		try {
			/**
			 * Hàm findAll này lấy ra đối tượng page của spring đối tượng page này gồm các
			 * thông tin: data, tổng số trang, tổng số bản ghi trên một trang,....
			 */
			return facultyRepository.findAll(pageable);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Faculty findOne(int id) {
		try {
			return facultyRepository.findById(id);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public int create(Faculty faculty) {
		try {
			faculty.setCreatedBy(CommonConstants.DEFAULT_USER);
			faculty.setCreateTime(new Date());
			faculty.setStatuss("0");

			facultyRepository.save(faculty);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public int update(Faculty faculty) {
		try {
			Optional<Faculty> oldfaculty=facultyRepository.findById(faculty.getIdFaculty());
			if(oldfaculty !=null) {
				faculty.setCreatedBy(CommonConstants.DEFAULT_USER);
				faculty.setStatuss("0");
				facultyRepository.save(faculty);
				return 1;
			}
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public int delete(int id) {
		try {
			Faculty  faculty = facultyRepository.findById(id);
			if (faculty != null) {
				facultyRepository.delete(faculty);
				return 1;
			} else {
				return 0;
			}

		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return 0;
		}
	}

    @Override
    public int deleteAllFaculty(Iterable<Faculty> entities) {
        try {
        	facultyRepository.deleteInBatch(entities);
            return 1;
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return 0;
    }

	@Override
	public List<Faculty> findBySubjectsTypeId(int id) {
        List<Faculty> faculty = null;

        try {
            return facultyRepository.findBySubjectsTypeIdSubjectsType(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return faculty;

	}


//	@Override
//	public Faculty findByIdSubjectsType(int id) {
//		try {
//			return facultyRepository.findByidSubjectsType(id);
//		} catch (Exception e) {
//			// TODO: handle exception
//			log.error(e.getMessage());
//			return null;
//		}
//	}

}

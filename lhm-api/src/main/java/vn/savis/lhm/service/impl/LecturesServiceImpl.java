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
import vn.savis.lhm.entity.Lecturers;
import vn.savis.lhm.repository.LecturersRepository;
import vn.savis.lhm.service.LecturesService;

@Service
public class LecturesServiceImpl implements LecturesService{
	@Autowired
	LecturersRepository lecturersRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	private Logger log = LoggerFactory.getLogger(LecturesServiceImpl.class);
	

	@Override
	public List<Lecturers> findAll() {
		try {
			return lecturersRepository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Page<Lecturers> findPaging(Pageable pageable) {
		try {
			/**
			 * Hàm findAll này lấy ra đối tượng page của spring đối tượng page này gồm các
			 * thông tin: data, tổng số trang, tổng số bản ghi trên một trang,....
			 */
			return lecturersRepository.findAll(pageable);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Lecturers findOne(int id) {
		try {
			return lecturersRepository.findById(id);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public int create(Lecturers lecturers) {
		try {
			lecturers.setCreatedBy(CommonConstants.DEFAULT_USER);
			lecturers.setCreateTime(new Date());
			lecturers.setStatuss("0");

			lecturersRepository.save(lecturers);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public int update(Lecturers lecturers) {
		try {
			Optional<Lecturers> oldlecturers=lecturersRepository.findById(lecturers.getIdLecturers());
			if(oldlecturers !=null) {
				lecturers.setCreatedBy(CommonConstants.DEFAULT_USER);
				lecturers.setStatuss("0");
				lecturersRepository.save(lecturers);
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
			Lecturers  lecturers = lecturersRepository.findById(id);
			if (lecturers != null) {
				lecturersRepository.delete(lecturers);
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
	public int deleteAllLecturers(Iterable<Lecturers> entities) {
        try {
        	lecturersRepository.deleteInBatch(entities);
            return 1;
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return 0;
	}

	@Override
	public List<Lecturers> findByFacultyId(int id) {
        List<Lecturers> lecturers = null;

        try {
            return lecturersRepository.findByFacultyIdFaculty(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return lecturers;
	}



}

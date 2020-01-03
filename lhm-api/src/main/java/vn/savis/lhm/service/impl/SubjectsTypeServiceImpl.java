package vn.savis.lhm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ietf.jgss.Oid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.savis.lhm.common.constants.CommonConstants;
import vn.savis.lhm.entity.SubjectsType;
import vn.savis.lhm.repository.SubjectsTypeRepository;
import vn.savis.lhm.service.SubjectsTypeService;

@Service
public class SubjectsTypeServiceImpl implements SubjectsTypeService{
	
	@Autowired
	SubjectsTypeRepository subjectsTypeRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	private Logger log = LoggerFactory.getLogger(SubjectsTypeServiceImpl.class);
	
	//lay all ban ghi
	@Override
	public List<SubjectsType> findAll() {
		try {
			return subjectsTypeRepository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public SubjectsType findOne(int id) {
		try {
			return subjectsTypeRepository.findById(id);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Page<SubjectsType> findPaging(Pageable pageable) {
		try {
			/**
			 * Hàm findAll này lấy ra đối tượng page của spring đối tượng page này gồm các
			 * thông tin: data, tổng số trang, tổng số bản ghi trên một trang,....
			 */
			return subjectsTypeRepository.findAll(pageable);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public int create(SubjectsType subjectstype) {
		try {
			subjectstype.setCreatedBy(CommonConstants.DEFAULT_USER);
			subjectstype.setCreateTime(new Date());
			subjectstype.setStatuss("0");

			subjectsTypeRepository.save(subjectstype);
			return 1;
		
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public int update(SubjectsType subjectstype) {
		// TODO Auto-generated method stub
		try {
			Optional<SubjectsType> oldsubjectstype=subjectsTypeRepository.findById(subjectstype.getIdSubjectsType());
			if(oldsubjectstype !=null) {
				subjectstype.setCreatedBy(CommonConstants.DEFAULT_USER);
				subjectstype.setUpdatedBy(CommonConstants.DEFAULT_USER);
				subjectstype.setUpdateTime(new Date());

				subjectsTypeRepository.save(subjectstype);
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
			SubjectsType  subjectstype = subjectsTypeRepository.findById(id);
			// kiểm tra sự tồn tại của user trước khi xóa
			if (subjectstype != null) {
				subjectsTypeRepository.delete(subjectstype);
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

}

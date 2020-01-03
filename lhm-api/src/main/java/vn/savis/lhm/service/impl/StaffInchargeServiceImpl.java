package vn.savis.lhm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.savis.lhm.common.constants.CommonConstants;
import vn.savis.lhm.entity.StaffIncharge;
import vn.savis.lhm.entity.StaffIncharge;
import vn.savis.lhm.repository.StaffInchargeRepository;
import vn.savis.lhm.service.StaffInchargeService;


@Service
public class StaffInchargeServiceImpl implements StaffInchargeService {

	@Autowired
	private StaffInchargeRepository staffInchargeRepository;

	private Logger log = org.slf4j.LoggerFactory.getLogger(StaffInchargeServiceImpl.class);

	@Override
	public List<StaffIncharge> findAll() {
		try {
			return staffInchargeRepository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Page<StaffIncharge> findPaging(Pageable pageable) {
		try {
			return staffInchargeRepository.findAll(pageable);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public StaffIncharge findOne(int id) {
		Optional<StaffIncharge> result = staffInchargeRepository.findById(id);

		StaffIncharge theObject = null;
		if (result.isPresent()) {
			theObject = result.get();
		} else {
			// we didn't fine the contact
			throw new RuntimeException("Did not find StaffIncharge id - " + id);
		}
		return theObject;
	}

	@Override
	public int create(StaffIncharge staffIncharge) {
		try {
			staffIncharge.setCreatedBy(CommonConstants.DEFAULT_USER);
			staffIncharge.setCreateTime(new Date());
			staffIncharge.setStatuss("0");
			staffInchargeRepository.save(staffIncharge);
			
			return 1;
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public int update(StaffIncharge staffIncharge) {
		try {
			StaffIncharge oldStaffIncharge = staffInchargeRepository.findById(staffIncharge.getIdStaffInCharge()).get();
			if (oldStaffIncharge != null) {
				staffIncharge.setCreatedBy(oldStaffIncharge.getCreatedBy());
				staffIncharge.setCreateTime(oldStaffIncharge.getCreateTime());
				staffIncharge.setUpdatedBy(CommonConstants.DEFAULT_USER);
				staffIncharge.setUpdateTime(new Date());
				staffInchargeRepository.save(staffIncharge);
				return 1;
			}
			return 0;
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public int delete(int id) {
		try {
			StaffIncharge oldStaffIncharge = staffInchargeRepository.findById(id).get();
			if (oldStaffIncharge != null) {
				oldStaffIncharge.setUpdatedBy(CommonConstants.DEFAULT_USER);
				oldStaffIncharge.setUpdateTime(new Date());
				oldStaffIncharge.setStatuss("0");
				staffInchargeRepository.save(oldStaffIncharge);
				return 1;
			}
			return 0;
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public Page<StaffIncharge> findAll(Specification spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return staffInchargeRepository.findAll(spec, pageable);
	}

	@Override
	public int deleteInBatch(int[] ids) {
		try {
			for (int i = 0; i < ids.length; i++) {
				StaffIncharge reg = staffInchargeRepository.findById(ids[i]).get();
				reg.setUpdatedBy("admin");
				reg.setUpdateTime(new Date());
				reg.setStatuss("0");
				staffInchargeRepository.save(reg);
				
			}
			return 1;
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

}

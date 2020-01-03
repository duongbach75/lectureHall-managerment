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
import vn.savis.lhm.common.constants.Constants;
import vn.savis.lhm.entity.Registration;
import vn.savis.lhm.repository.RegistrationRepository;
import vn.savis.lhm.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;

	private Logger log = org.slf4j.LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Override
	public List<Registration> findAll() {
		try {
			return registrationRepository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Page<Registration> findPaging(Pageable pageable) {
		try {
			return registrationRepository.findAll(pageable);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Registration findOne(int id) {
		Optional<Registration> result = registrationRepository.findById(id);

		Registration theObject = null;
		if (result.isPresent()) {
			theObject = result.get();
		} else {
			// we didn't fine the contact
			throw new RuntimeException("Did not find Product id - " + id);
		}
		return theObject;
	}

	@Override
	public int create(Registration registration) {
		try {
			registration.setCreatedBy(CommonConstants.DEFAULT_USER);
			registration.setCreateTime(new Date());
			registration.setStatuss("0");
			registrationRepository.save(registration);
			return 1;
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public int update(Registration registration) {
		try {
			Registration oldRegistration = registrationRepository.findById(registration.getIdRegistration()).get();
			if (oldRegistration != null) {
				registration.setCreatedBy(oldRegistration.getCreatedBy());
				registration.setCreateTime(oldRegistration.getCreateTime());
				registration.setUpdatedBy(CommonConstants.DEFAULT_USER);
				registration.setUpdateTime(new Date());
				registrationRepository.save(registration);
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
			Registration oldRegistration = registrationRepository.findById(id).get();
			if (oldRegistration != null) {
				oldRegistration.setUpdatedBy(CommonConstants.DEFAULT_USER);
				oldRegistration.setUpdateTime(new Date());
				oldRegistration.setStatuss("0");
				registrationRepository.save(oldRegistration);
				return 1;
			}
			return 0;
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public Page<Registration> findAll(Specification spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return registrationRepository.findAll(spec, pageable);
	}

	@Override
	public int deleteInBatch(int[] ids) {
		try {
			for (int i = 0; i < ids.length; i++) {
				Registration reg = registrationRepository.findById(ids[i]).get();
				reg.setUpdatedBy("admin");
				reg.setUpdateTime(new Date());
				reg.setStatuss("0");
				registrationRepository.save(reg);
				
			}
			return 1;
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

}

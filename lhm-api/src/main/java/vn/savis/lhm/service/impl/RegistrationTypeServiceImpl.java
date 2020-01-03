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
import vn.savis.lhm.entity.RegistrationType;
import vn.savis.lhm.repository.RegistrationTypeRepository;
import vn.savis.lhm.service.RegistrationTypeService;

@Service
public class RegistrationTypeServiceImpl implements RegistrationTypeService {

	@Autowired
	private RegistrationTypeRepository registrationTypeRepository;

	private Logger log = org.slf4j.LoggerFactory.getLogger(RegistrationTypeServiceImpl.class);

	@Override
	public List<RegistrationType> findAll() {
		try {
			return registrationTypeRepository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Page<RegistrationType> findPaging(Pageable pageable) {
		try {
			return registrationTypeRepository.findAll(pageable);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public RegistrationType findOne(int id) {
		Optional<RegistrationType> result = registrationTypeRepository.findById(id);

		RegistrationType theObject = null;
		if (result.isPresent()) {
			theObject = result.get();
		} else {
			// we didn't fine the contact
			throw new RuntimeException("Did not find registrationType id - " + id);
		}
		return theObject;
	}

	@Override
	public int create(RegistrationType registrationType) {
		try {
			registrationType.setCreatedBy(CommonConstants.DEFAULT_USER);
			registrationType.setCreateTime(new Date());
			registrationType.setStatuss("0");
			registrationTypeRepository.save(registrationType);
			return 1;
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public int update(RegistrationType registrationType) {
		try {
			RegistrationType oldRegistration = registrationTypeRepository.findById(registrationType.getIdRegistrationType()).get();
			if (oldRegistration != null) {
				registrationType.setCreatedBy(oldRegistration.getCreatedBy());
				registrationType.setCreateTime(oldRegistration.getCreateTime());
				registrationType.setUpdatedBy(CommonConstants.DEFAULT_USER);
				registrationType.setUpdateTime(new Date());
				registrationTypeRepository.save(registrationType);
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
			RegistrationType oldRegistrationType = registrationTypeRepository.findById(id).get();
			if (oldRegistrationType != null) {
				oldRegistrationType.setUpdatedBy(CommonConstants.DEFAULT_USER);
				oldRegistrationType.setUpdateTime(new Date());
				oldRegistrationType.setStatuss("0");
				registrationTypeRepository.save(oldRegistrationType);
				return 1;
			}
			return 0;
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public Page<RegistrationType> findAll(Specification spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return registrationTypeRepository.findAll(spec, pageable);
	}

	@Override
	public int deleteInBatch(int[] ids) {
		try {
			for (int i = 0; i < ids.length; i++) {
				RegistrationType reg = registrationTypeRepository.findById(ids[i]).get();
				reg.setUpdatedBy("admin");
				reg.setUpdateTime(new Date());
				reg.setStatuss("0");
				registrationTypeRepository.save(reg);
				
			}
			return 1;
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

}

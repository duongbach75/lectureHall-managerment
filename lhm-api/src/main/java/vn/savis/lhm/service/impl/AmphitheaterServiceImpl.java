package vn.savis.lhm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.savis.lhm.common.constants.CommonConstants;
import vn.savis.lhm.entity.Amphitheater;
import vn.savis.lhm.repository.AmphitheaterRepository;
import vn.savis.lhm.service.AmphitheaterService;
import vn.savis.lhm.specification.AmphitheaterSpecifications;

import java.util.Date;
import java.util.List;

/**
 * @author: SonNH - SAVIS
 * @created: 9/12/19
 * @Time: 15:23
 * @modified 9/12/19
 **/
@Service
public class AmphitheaterServiceImpl implements AmphitheaterService {

	@Autowired
	private AmphitheaterRepository amphitheaterRepository;

	
	private Logger LOGGER = LoggerFactory.getLogger(AmphitheaterServiceImpl.class);
	
	@Override
	public Page<Amphitheater> searchAllAmphitheater(String name, String status, Pageable pageable) {
		try {
			return amphitheaterRepository.findAllAmphitheater(name, status, pageable);
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Page<Amphitheater> searchNameAmphitheater(String name, Pageable pageable) {
		try {
			return amphitheaterRepository.findNameAmphitheater(name, pageable);
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Page<Amphitheater> searchStatusAmphitheater(String status, Pageable pageable) {
		try {
			return amphitheaterRepository.findStatusAmphitheater(status, pageable);
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	

	@Override
	public Page<Amphitheater> findPaging(Pageable pageable) {
		try {
			return amphitheaterRepository.findAll(pageable);
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}


	@Override
	public int create(Amphitheater amphitheaters) {
		try {
			Amphitheater amphitheaterExisting = new Amphitheater();
			amphitheaterExisting.setIdAmphitheater(amphitheaters.getIdAmphitheater());
			amphitheaterExisting.setNameAmphitheater(amphitheaters.getNameAmphitheater());
			amphitheaterExisting.setCreateTime(new Date());
			amphitheaterExisting.setCreatedBy(CommonConstants.DEFAULT_USER);
			amphitheaterExisting.setStatuss(amphitheaters.getStatuss());

			amphitheaterRepository.save(amphitheaterExisting);
			return 1;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public List<Amphitheater> findAll() {
		try {
			return amphitheaterRepository.findAll();
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Amphitheater findOne(int id) {
		Amphitheater amphitheaters = null;
		try {
			amphitheaters = amphitheaterRepository.findByIdAmphitheater(id);
		} catch (Exception e) {
		
			LOGGER.error(e.getMessage());
		}
		return amphitheaters;
	}

	@Override
	public int update(Amphitheater amphitheaters) {
		try {
			Amphitheater amphitheaterExisting = amphitheaterRepository.findByIdAmphitheater(amphitheaters.getIdAmphitheater());
			amphitheaterExisting.setNameAmphitheater(amphitheaters.getNameAmphitheater());
			amphitheaterExisting.setStatuss(amphitheaters.getStatuss());
			amphitheaterExisting.setUpdateTime(new Date());
			amphitheaterExisting.setUpdatedBy(CommonConstants.DEFAULT_USER);

			amphitheaterRepository.save(amphitheaterExisting);

			return 1;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public int delete(int id) {
		try {
			amphitheaterRepository.deleteById(id);
			return 1;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public int deleteAllBatch(Iterable<Amphitheater> amphitheaters) {
		try {
			amphitheaterRepository.deleteInBatch(amphitheaters);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return 0;
		}
		return 1;
	}

	@Override
	public Page<Amphitheater> search(Amphitheater amphitheater, Pageable pageable) {
		Page<Amphitheater> amphitheaters = null;
		try {
			amphitheaters = amphitheaterRepository.findAll(AmphitheaterSpecifications.advanceFilter(amphitheater), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return amphitheaters;
	}

}

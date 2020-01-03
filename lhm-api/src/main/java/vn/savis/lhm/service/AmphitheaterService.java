package vn.savis.lhm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.savis.lhm.entity.Amphitheater;

import java.util.List;

/**
 * @author: SonNH - SAVIS
 * @created: 9/12/19
 * @Time: 15:23
 * @modified 9/12/19
 **/
@Service
public interface AmphitheaterService {

//	List<Amphitheater> searchAmphitheaterByName(String name);

	Page<Amphitheater> findPaging(Pageable pageable);

	int create(Amphitheater amphitheaters);

	List<Amphitheater> findAll();

	Amphitheater findOne(int id);

	int update(Amphitheater amphitheaters);

	int delete(int id);

	int deleteAllBatch(Iterable<Amphitheater> amphitheaters);

	Page<Amphitheater> searchAllAmphitheater(String name, String status, Pageable pageable);

	Page<Amphitheater> searchNameAmphitheater(String name, Pageable pageable);

	Page<Amphitheater> searchStatusAmphitheater(String status, Pageable pageable);

	Page<Amphitheater> search(Amphitheater amphitheater, Pageable pageable);



}

package vn.savis.lhm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.savis.lhm.entity.Amphitheater;

public interface AmphitheaterRepository extends JpaRepository<Amphitheater, Integer> {
	
		// search by name
		@Query(value = "SELECT * FROM amphitheater WHERE name_amphitheater LIKE %:name% AND statuss LIKE %:status%", nativeQuery = true)
        Page<Amphitheater> findAllAmphitheater(@Param("name") String name, @Param("status") String status, Pageable pageable);
		
		@Query(value = "SELECT * FROM amphitheater WHERE name_amphitheater LIKE %:name%", nativeQuery = true)
        Page<Amphitheater> findNameAmphitheater(@Param("name") String name, Pageable pageable);
		
		@Query(value = "SELECT * FROM amphitheater WHERE statuss LIKE %:status%", nativeQuery = true)
        Page<Amphitheater> findStatusAmphitheater(@Param("status") String status, Pageable pageable);

		Amphitheater findByIdAmphitheater(int idAmphitheater);

	Page<Amphitheater> findAll(Specification<Amphitheater> advanceFilter, Pageable pageable);


	
	
}

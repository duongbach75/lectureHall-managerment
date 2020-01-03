package vn.savis.lhm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.savis.lhm.entity.Registration;
import vn.savis.lhm.entity.RegistrationType;

public interface RegistrationTypeRepository extends JpaRepository<RegistrationType, Integer>, JpaSpecificationExecutor<Registration>{

}

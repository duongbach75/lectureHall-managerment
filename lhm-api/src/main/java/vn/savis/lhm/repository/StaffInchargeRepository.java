package vn.savis.lhm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.savis.lhm.entity.StaffIncharge;

public interface StaffInchargeRepository extends JpaRepository<StaffIncharge, Integer>, JpaSpecificationExecutor<StaffIncharge>{

}

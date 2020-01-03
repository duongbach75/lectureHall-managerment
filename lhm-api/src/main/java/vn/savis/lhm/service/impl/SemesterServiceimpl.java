package vn.savis.lhm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.savis.lhm.entity.Schedule;
import vn.savis.lhm.entity.Semester;
import vn.savis.lhm.repository.SemesterRespository;
import vn.savis.lhm.service.SemesterService;
import vn.savis.lhm.specification.SemesterSpecification;
@Service
public class SemesterServiceimpl implements SemesterService {
	@Autowired
	private SemesterRespository semesterRespository;

	@Override
	public List<Semester> findAllSemester() {
		List<Semester> semester= null;
	  try {
		  List<Semester> a= semesterRespository.findAll();
		return a;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		return semester;
	}

	@Override
	public Page<Semester> findPaging(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Semester>  semester = null;
		try {
			semester= semesterRespository.findAll(pageable);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return semester;
	}

	@Override
	public Semester findById(int idSemester) {
		// TODO Auto-generated method stub
		Semester semester= null;
		try {
			semester= semesterRespository.findByIdSemester(idSemester);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return semester;
	}

	@Override
	public int saveCreateSemester(Semester Semester) {
		// TODO Auto-generated method stub
		try {
			Semester sj= new Semester();
			sj.setIdSemester(Semester.getIdSemester());
			sj.setNameSemester(Semester.getNameSemester());
			sj.setCreateTime(new Date());
			sj.setCreatedBy("admin");
			semesterRespository.save(sj);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;}
}

	@Override
	public int updateSemester(Semester Semester) {
		// TODO Auto-generated method stub
				try {
					Semester sjExit = findById(Semester.getIdSemester());
					
					Semester.setCreatedBy(sjExit.getCreatedBy() );
					Semester.setCreateTime(sjExit.getCreateTime());
					Semester.setUpdateTime(new Date());
					Semester.setUpdatedBy("admin");
					
					semesterRespository.save(Semester);
					return 1;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return 0;
				}
	}

	@Override
	public int deleteAllSemester(int[] id){ 
		try {
		// TODO Auto-generated method stub	try 
		for (int i = 0; i < id.length; i++) {
			Semester reg = semesterRespository.findByIdSemester(id[i]);
			reg.setUpdatedBy("admin");
			reg.setUpdateTime(new Date());
			reg.setStatuss("0");
			semesterRespository.save(reg);
		}
		return id.length;
	} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
}

	@Override
	public int deleteSemester(int idSemester) {		
		try {
		Semester old = semesterRespository.findByIdSemester(idSemester);
		if (old != null) {
			old.setUpdatedBy("admin");
			old.setUpdateTime(new Date());
			old.setStatuss("0");
			semesterRespository.save(old);
			return 1;
		}
		return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}



	@Override
	public List<Semester> searchSemesterByName(String name) {
	// TODO Auto-generated method stub
		try {
			return semesterRespository.findAllByNameSemester(name);
		} catch (Exception e) {
			// TODO: handle exception
			return null;

		}

	}

	@Override
	public Page<Semester> search(Semester semester, Pageable pageable) {
		// TODO Auto-generated method stub
		 Page<Semester> Semester  = null;
	        try {
	        	Semester = semesterRespository.findAll(SemesterSpecification.advanceFilter(semester), pageable);
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	        return Semester;
	
	}

}

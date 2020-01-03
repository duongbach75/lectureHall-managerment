package vn.savis.lhm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.savis.lhm.entity.Schedule;
import vn.savis.lhm.entity.Semester;
import vn.savis.lhm.entity.Subjects;
import vn.savis.lhm.repository.SubjectRespository;
import vn.savis.lhm.service.SubjectsService;
import vn.savis.lhm.specification.SubjectSpecification;

@Service
public class SubjectsServiceimpl implements SubjectsService {
	@Autowired
	private SubjectRespository subjectRespository;

	@Override
	public List<Subjects> findAllSubjects() {
		  try {
			  List<Subjects> a= subjectRespository.findAll();
			return a;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

		}
		@Override
		public Page<Subjects> findPaging(Pageable pageable) {
			// TODO Auto-generated method stub
			Page<Subjects>  subject = null;
			try {
				subject= subjectRespository.findAll(pageable);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return subject;
		}
		@Override
		public Subjects findById(int idSubjects) {
			// TODO Auto-generated method stub
			Subjects subjects= null;
			try {
				subjects= subjectRespository.findByIdSubjects(idSubjects);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			return subjects;
		}
	@Override
	public int saveCreateSubjects(Subjects subjects) {
		// TODO Auto-generated method stub
try {
	Subjects sj= new Subjects();
	sj.setIdSubjects(subjects.getIdSubjects());
	sj.setSemester(subjects.getSemester());
	sj.setNameSubjects(subjects.getNameSubjects());
	sj.setSubjectsType(subjects.getSubjectsType());
	sj.setNumberOfCredits(subjects.getNumberOfCredits());
	sj.setCreateTime(new Date());
	sj.setCreatedBy("admin");
	sj.setStatuss("1");
	subjectRespository.save(sj);
	return 1;
} catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
	return 0;
}
	}

	@Override
	public int updateSubjects(Subjects subjects) {
		// TODO Auto-generated method stub
		try {

Subjects sjExit = findById(subjects.getIdSubjects());
			
			subjects.setCreatedBy(sjExit.getCreatedBy() );
			subjects.setCreateTime(sjExit.getCreateTime());
			subjects.setUpdateTime(new Date());
			subjects.setUpdatedBy("admin");
			subjectRespository.save(subjects);
			return 1;
} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteAllSubjects(int[] id) {
		// TODO Auto-generated method stub
		try {
		// TODO Auto-generated method stub	try 
		for (int i = 0; i < id.length; i++) {
			Subjects reg = subjectRespository.findByIdSubjects(id[i]);
			reg.setUpdatedBy("admin");
			reg.setUpdateTime(new Date());
			reg.setStatuss("0");
			subjectRespository.save(reg);
		}
		return id.length;
	} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
}


	@Override
	public int deleteSubjects(int idSubjects) {
		// TODO Auto-generated method stub		
		try {
		Subjects old = subjectRespository.findByIdSubjects(idSubjects);
		if (old != null) {
			old.setUpdatedBy("admin");
			old.setUpdateTime(new Date());
			old.setStatuss("0");
			subjectRespository.save(old);
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
	public List<Subjects> searchSubjectsByName(String name) {
		// TODO Auto-generated method stub
		try {
			return subjectRespository.findAllByNameSubjects(name);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public Page<Subjects> search(Subjects subjects, Pageable pageable) {
		// TODO Auto-generated method stub
		 Page<Subjects> Subjects = null;
	        try {
	            Subjects = subjectRespository.findAll(SubjectSpecification.advanceFilter(subjects), pageable);
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	        return Subjects;
	
	}
	
}

package vn.savis.lhm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.savis.lhm.entity.Classroom;

import java.util.List;

/**
 * @author: SonNH - SAVIS
 * @created: 9/6/19
 * @Time: 09:50
 * @modified 9/6/19
 **/

public interface ClassroomService {
    List<Classroom> findAllClassroom();

    Page<Classroom> findPaging(Pageable pageable);

    Classroom findById(int classroomId);

    int saveCreateClassroom(Classroom classroom);

    int updateClassroom(Classroom classroom);

    List<Classroom> searchClassroomByName(String name);

    int deleteAllClassroom(Iterable<Classroom> entities);

    int deleteClassroom(int classroomId);

    Page<Classroom> search(Classroom classroom, Pageable pageable);

    List<Classroom> findByAmphitheaterAmphitheaterId(int id);

    Page<Classroom> findByClassroom(String date,int idAmphitheater, Pageable pageable);
}

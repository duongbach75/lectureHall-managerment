package vn.savis.lhm.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.savis.lhm.common.constants.CommonConstants;
import vn.savis.lhm.common.constants.Constants;
import vn.savis.lhm.entity.Classroom;
import vn.savis.lhm.repository.ClassroomRepository;
import vn.savis.lhm.service.ClassroomService;
import vn.savis.lhm.specification.ClassroomSpecifications;


import java.util.Date;
import java.util.List;

/**
 * @author: SonNH - SAVIS
 * @created: 9/6/19
 * @Time: 09:50
 * @modified 9/6/19
 **/

@Service
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    private ClassroomRepository classRoomRepository;

    private Logger LOGGER = LoggerFactory.getLogger(ClassroomServiceImpl.class);

    @Override
    public List<Classroom> findAllClassroom() {

        List<Classroom> classRoom = null;

        try {
            List<Classroom> a = classRoomRepository.findAll();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return classRoom;
    }

    @Override
    public int saveCreateClassroom(Classroom classRoom) {
        try {
            Classroom classRoomExisting = new Classroom();
            classRoomExisting.setIdClassroom(classRoom.getIdClassroom());
            classRoomExisting.setStaffIncharge(classRoom.getStaffIncharge());
            classRoomExisting.setAmphitheater(classRoom.getAmphitheater());
            classRoomExisting.setNameClassroom(classRoom.getNameClassroom());
            classRoomExisting.setSymbol(classRoom.getSymbol());
            classRoomExisting.setAmount(classRoom.getAmount());
            classRoomExisting.setSize(classRoom.getSize());
            classRoomExisting.setChucNang(classRoom.getChucNang());
            classRoomExisting.setCreateTime(new Date());
            classRoomExisting.setCreatedBy(CommonConstants.DEFAULT_USER);
            classRoomExisting.setStatuss(CommonConstants.STATUS.TRUE);
            classRoomRepository.save(classRoomExisting);
            return 1;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateClassroom(Classroom classRoom) {
        try {
            Classroom classRoomExisting = classRoomRepository.findByIdClassroom(classRoom.getIdClassroom());
            classRoomExisting.setIdClassroom(classRoom.getIdClassroom());
            classRoomExisting.setAmphitheater(classRoom.getAmphitheater());
			classRoomExisting.setStaffIncharge(classRoom.getStaffIncharge());
            classRoomExisting.setNameClassroom(classRoom.getNameClassroom());
            classRoomExisting.setSymbol(classRoom.getSymbol());
            classRoomExisting.setAmount(classRoom.getAmount());
            classRoomExisting.setSize(classRoom.getSize());
            classRoomExisting.setChucNang(classRoom.getChucNang());
            classRoomExisting.setUpdateTime(new Date());
            classRoomExisting.setUpdatedBy(CommonConstants.DEFAULT_USER);
            classRoomExisting.setStatuss(classRoom.getStatuss());
            classRoomRepository.save(classRoomExisting);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return 0;
        }

    }


    @Override
    public List<Classroom> searchClassroomByName(String name) {
        List<Classroom> listCR = null;
        try {
            return classRoomRepository.findAllByNameClassroom(name);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return listCR;
    }

    @Override
    public int deleteAllClassroom(Iterable<Classroom> entities) {
        try {
            classRoomRepository.deleteInBatch(entities);
            return 1;
        }catch(Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public Classroom findById(int classRoomId) {
        Classroom classroom = null;
        try {
            classroom = classRoomRepository.findByIdClassroom(classRoomId);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return classroom;
    }

    @Override
    public int deleteClassroom(int classRoomId) {
        // TODO Auto-generated method stub
        try {
            classRoomRepository.deleteById(classRoomId);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return 0;
        }

    }

    @Override
    public Page<Classroom> findPaging(Pageable pageable) {
        Page<Classroom> classroom = null;
        try {
            classroom = classRoomRepository.findAll(pageable);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return classroom;
    }


    @Override
    public Page<Classroom> search(Classroom classRoom, Pageable pageable) {
        Page<Classroom> classrooms = null;
        try {
            classrooms = classRoomRepository.findAll(ClassroomSpecifications.advanceFilter(classRoom), pageable);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return classrooms;
    }

    @Override
    public List<Classroom> findByAmphitheaterAmphitheaterId(int idAmphitheater) {
        List<Classroom> classroom = null;

        try {
            return classRoomRepository.findByAmphitheaterIdAmphitheater(idAmphitheater);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return classroom;
    }

    @Override
    public Page<Classroom> findByClassroom(String date,int idAmphitheater, Pageable pageable) {
        Page<Classroom> classrooms = null;
        try {
            classrooms = classRoomRepository.findByClassroom(date,idAmphitheater,pageable);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return classrooms;
    }
}

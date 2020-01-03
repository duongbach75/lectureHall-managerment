package vn.savis.lhm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 */
@Entity
@Table(name = "schedule", catalog = "qlgd")
public class Schedule implements java.io.Serializable {

	private Integer idSchedule;
	private Classroom classroom;
	private Subjects subjects;
	private String studyTime;
	private Date createTime;
	private String createdBy;
	private Date updateTime;
	private String updatedBy;
	private String statuss;

	public Schedule() {
	}

	

	public Schedule(Classroom classroom, Subjects subjects, String studyTime, Date createTime, String createdBy,
			Date updateTime, String updatedBy, String statuss) {
	
		this.classroom = classroom;
		this.subjects = subjects;
		this.studyTime = studyTime;
		this.createTime = createTime;
		this.createdBy = createdBy;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.statuss = statuss;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_schedule", unique = true, nullable = false)
	public Integer getIdSchedule() {
		return this.idSchedule;
	}

	public void setIdSchedule(Integer idSchedule) {
		this.idSchedule = idSchedule;
	}

	@ManyToOne
	@JoinColumn(name = "id_classroom", nullable = false)
	public Classroom getClassroom() {
		return this.classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	@ManyToOne
	@JoinColumn(name = "id_subjects", nullable = false)
	public Subjects getSubjects() {
		return this.subjects;
	}

	public void setSubjects(Subjects subjects) {
		this.subjects = subjects;
	}

	@Column(name = "study_time", nullable = false, length = 50)
	public String getStudyTime() {
		return this.studyTime;
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "created_by", nullable = false, length = 50)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "updated_by", length = 50)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name = "statuss")
	public String getStatuss() {
		return statuss;
	}

	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}

}

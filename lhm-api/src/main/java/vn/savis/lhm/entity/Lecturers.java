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
@Table(name = "lecturers", catalog = "qlgd")
public class Lecturers implements java.io.Serializable {

	private Integer idLecturers;
	private Faculty faculty;
	private String nameLecturers;
	private String describes;
	private Date createTime;
	private String createdBy;
	private Date updateTime;
	private String updatedBy;
	private String statuss;

	public Lecturers() {
	}

	

	public Lecturers(Faculty faculty, String nameLecturers, String describes, Date createTime, String createdBy,
			Date updateTime, String updatedBy, String statuss) {
		
		this.faculty = faculty;
		this.nameLecturers = nameLecturers;
		this.describes = describes;
		this.createTime = createTime;
		this.createdBy = createdBy;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.statuss = statuss;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_lecturers", unique = true, nullable = false)
	public Integer getIdLecturers() {
		return this.idLecturers;
	}

	public void setIdLecturers(Integer idLecturers) {
		this.idLecturers = idLecturers;
	}

	@ManyToOne
	@JoinColumn(name = "id_faculty", nullable = false)
	public Faculty getFaculty() {
		return this.faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	@Column(name = "name_lecturers", nullable = false, length = 50)
	public String getNameLecturers() {
		return this.nameLecturers;
	}

	public void setNameLecturers(String nameLecturers) {
		this.nameLecturers = nameLecturers;
	}

	@Column(name = "describes", nullable = false, length = 50)
	public String getDescribes() {
		return this.describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
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

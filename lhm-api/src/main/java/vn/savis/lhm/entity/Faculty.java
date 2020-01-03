package vn.savis.lhm.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 */
@Entity
@Table(name = "faculty", catalog = "qlgd")
public class Faculty implements java.io.Serializable {

	private Integer idFaculty;
	private SubjectsType subjectsType;
	private String nameFaculty;
	private Date createTime;
	private String createdBy;
	private Date updateTime;
	private String updatedBy;
	private String statuss;

	public Faculty() {
	}

	


	public Faculty(SubjectsType subjectsType, String nameFaculty, Date createTime, String createdBy, Date updateTime,
			String updatedBy, String statuss) {
	
		this.subjectsType = subjectsType;
		this.nameFaculty = nameFaculty;
		this.createTime = createTime;
		this.createdBy = createdBy;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.statuss = statuss;
	}




	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_faculty", unique = true, nullable = false)
	public Integer getIdFaculty() {
		return this.idFaculty;
	}

	public void setIdFaculty(Integer idFaculty) {
		this.idFaculty = idFaculty;
	}

	@ManyToOne
	@JoinColumn(name = "id_subjects_type", nullable = false)
	public SubjectsType getSubjectsType() {
		return this.subjectsType;
	}

	public void setSubjectsType(SubjectsType subjectsType) {
		this.subjectsType = subjectsType;
	}

	@Column(name = "name_faculty", nullable = false, length = 50)
	public String getNameFaculty() {
		return this.nameFaculty;
	}

	public void setNameFaculty(String nameFaculty) {
		this.nameFaculty = nameFaculty;
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

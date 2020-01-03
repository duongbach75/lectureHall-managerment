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
@Table(name = "subjects", catalog = "qlgd")
public class Subjects implements java.io.Serializable {

	private Integer idSubjects;
	private Semester semester;
	private SubjectsType subjectsType;
	private String nameSubjects;
	private int numberOfCredits;
	private Date createTime;
	private String createdBy;
	private Date updateTime;
	private String updatedBy;
	private String statuss;

	public Subjects() {
	}

	public Subjects(Semester semester, SubjectsType subjectsType, String nameSubjects, int numberOfCredits) {
		this.semester = semester;
		this.subjectsType = subjectsType;
		this.nameSubjects = nameSubjects;
		this.numberOfCredits = numberOfCredits;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_subjects", unique = true, nullable = false)
	public Integer getIdSubjects() {
		return this.idSubjects;
	}

	public void setIdSubjects(Integer idSubjects) {
		this.idSubjects = idSubjects;
	}

	@ManyToOne
	@JoinColumn(name = "id_semester", nullable = false)
	public Semester getSemester() {
		return this.semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	@ManyToOne
	@JoinColumn(name = "id_subjects_type", nullable = false)
	public SubjectsType getSubjectsType() {
		return this.subjectsType;
	}

	public void setSubjectsType(SubjectsType subjectsType) {
		this.subjectsType = subjectsType;
	}

	@Column(name = "name_subjects", nullable = false, length = 50)
	public String getNameSubjects() {
		return this.nameSubjects;
	}

	public void setNameSubjects(String nameSubjects) {
		this.nameSubjects = nameSubjects;
	}

	@Column(name = "number_of_credits", nullable = false)
	public int getNumberOfCredits() {
		return this.numberOfCredits;
	}

	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "created_by", length = 50)
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

	@Column(name = "statuss", length = 2)
	public String getStatuss() {
		return this.statuss;
	}

	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}


}

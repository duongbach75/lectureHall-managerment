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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 */
@Entity
@Table(name = "semester", catalog = "qlgd")
public class Semester implements java.io.Serializable {

	private Integer idSemester;
	private String nameSemester;
	private Date createTime;
	private String createdBy;
	private Date updateTime;
	private String updatedBy;
	private String statuss;

	public Semester() {
	}

	


	public Semester(String nameSemester, Date createTime, String createdBy, Date updateTime, String updatedBy,
			String statuss) {
		
		this.nameSemester = nameSemester;
		this.createTime = createTime;
		this.createdBy = createdBy;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.statuss = statuss;
	}




	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_semester", unique = true, nullable = false)
	public Integer getIdSemester() {
		return this.idSemester;
	}

	public void setIdSemester(Integer idSemester) {
		this.idSemester = idSemester;
	}

	@Column(name = "name_semester", nullable = false)
	public String getNameSemester() {
		return this.nameSemester;
	}

	public void setNameSemester(String nameSemester) {
		this.nameSemester = nameSemester;
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

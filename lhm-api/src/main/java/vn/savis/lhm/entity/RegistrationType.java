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
@Table(name = "registrationtype", catalog = "qlgd")
public class RegistrationType implements java.io.Serializable {

	private Integer idRegistrationType;
	private String nameRegistrationType;
	private Date createTime;
	private String createdBy;
	private Date updateTime;
	private String updatedBy;
	private String statuss;

	public RegistrationType() {
	}

	public RegistrationType(String nameRegistrationType, String statuss) {
		this.nameRegistrationType = nameRegistrationType;
		this.statuss = statuss;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_registration_type", unique = true, nullable = false)
	public Integer getIdRegistrationType() {
		return this.idRegistrationType;
	}

	public void setIdRegistrationType(Integer idRegistrationType) {
		this.idRegistrationType = idRegistrationType;
	}

	@Column(name = "name_registration_type", nullable = false, length = 50)
	public String getNameRegistrationType() {
		return this.nameRegistrationType;
	}

	public void setNameRegistrationType(String nameRegistrationType) {
		this.nameRegistrationType = nameRegistrationType;
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

	@Column(name = "statuss", nullable = false, length = 50)
	public String getStatuss() {
		return this.statuss;
	}

	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}


}

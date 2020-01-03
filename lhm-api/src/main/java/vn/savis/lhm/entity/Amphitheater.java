package vn.savis.lhm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 */
@Entity
@Table(name = "amphitheater", catalog = "qlgd")
public class Amphitheater implements java.io.Serializable {

	private Integer idAmphitheater;
	private String nameAmphitheater;
	private Date createTime;
	private String createdBy;
	private Date updateTime;
	private String updatedBy;
	private String statuss;

	public Amphitheater() {
	}

	public Amphitheater(String nameAmphitheater, Date createTime, String createdBy, Date updateTime, String updatedBy,
			String statuss) {
		this.nameAmphitheater = nameAmphitheater;
		this.createTime = createTime;
		this.createdBy = createdBy;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.statuss = statuss;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_amphitheater", unique = true, nullable = false)
	public Integer getIdAmphitheater() {
		return this.idAmphitheater;
	}

	public void setIdAmphitheater(Integer idAmphitheater) {
		this.idAmphitheater = idAmphitheater;
	}

	@Column(name = "name_amphitheater", length = 200, nullable = false)
	public String getNameAmphitheater() {
		return this.nameAmphitheater;
	}

	public void setNameAmphitheater(String nameAmphitheater) {
		this.nameAmphitheater = nameAmphitheater;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_time", length = 10)
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

	@Temporal(TemporalType.DATE)
	@Column(name = "update_time", length = 10)
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

	@Column(name = "statuss", length = 50)
	public String getStatuss() {
		return this.statuss;
	}

	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}

}

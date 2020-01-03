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
@Table(name = "device", catalog = "qlgd")
public class Device implements java.io.Serializable {

	private Integer idDevice;
	private Classroom classroom;
	private String nameDevice;
	private Integer amount;
	private Date createTime;
	private String createdBy;
	private Date updateTime;
	private String updatedBy;
	private String statuss;

	public Device() {
	}

	public Device(Classroom classroom, String nameDevice , Integer amount, Date createTime, String createdBy,
			Date updateTime, String updatedBy, String statuss) {
		this.classroom = classroom;
		this.nameDevice = nameDevice;
		this.amount = amount;
		this.createTime = createTime;
		this.createdBy = createdBy;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.statuss = statuss;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_device", unique = true, nullable = false)
	public Integer getIdDevice() {
		return this.idDevice;
	}

	public void setIdDevice(Integer idDevice) {
		this.idDevice = idDevice;
	}

	@ManyToOne
	@JoinColumn(name = "id_classroom", nullable = false)
	public Classroom getClassroom() {
		return this.classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	@Column(name = "name_device", nullable = false, length = 50)
	public String getNameDevice() {
		return this.nameDevice;
	}

	public void setNameDevice(String nameDevice) {
		this.nameDevice = nameDevice;
	}

	@Column(name = "amount", nullable = false, length = 50)
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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

	@Column(name = "statuss", nullable = false, length = 50)
	public String getStatuss() {
		return this.statuss;
	}

	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}

}

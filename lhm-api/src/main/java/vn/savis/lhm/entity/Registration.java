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
@Table(name = "registration", catalog = "qlgd")
public class Registration implements java.io.Serializable {

	private Integer idRegistration;
	private Classroom classroom;
	private RegistrationType registrationType;
	private Subjects subjects;
	private String userName;
	private Date registrationDate;
	private String reason;
	private Date fromDate;
	private Date toDate;
	private String statuss;
	private Date createTime;
	private String createdBy;
	private Date updateTime;
	private String updatedBy;

	public Registration() {
	}

	public Registration(Classroom classroom, RegistrationType registrationType, Subjects subjects, String userName) {
		this.classroom = classroom;
		this.registrationType = registrationType;
		this.subjects = subjects;
		this.userName = userName;
	}
	
	public Registration(Classroom classroom, RegistrationType registrationType, Subjects subjects, String userName,
			Date registrationDate, String reason, Date fromDate, Date toDate, String statuss, Date createTime,
			String createdBy, Date updateTime, String updatedBy) {
		
		this.classroom = classroom;
		this.registrationType = registrationType;
		this.subjects = subjects;
		this.userName = userName;
		this.registrationDate = registrationDate;
		this.reason = reason;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.statuss = statuss;
		this.createTime = createTime;
		this.createdBy = createdBy;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
	}

	

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_registration", unique = true, nullable = false)
	public Integer getIdRegistration() {
		return this.idRegistration;
	}

	public void setIdRegistration(Integer idRegistration) {
		this.idRegistration = idRegistration;
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
	@JoinColumn(name = "id_registration_type", nullable = false)
	public RegistrationType getRegistrationtype() {
		return this.registrationType;
	}

	public void setRegistrationtype(RegistrationType registrationType) {
		this.registrationType = registrationType;
	}

	@ManyToOne
	@JoinColumn(name = "id_subject", nullable = false)
	public Subjects getSubjects() {
		return this.subjects;
	}

	public void setSubjects(Subjects subjects) {
		this.subjects = subjects;
	}

	@Column(name = "user_name", nullable = false)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "registration_date", length = 10)
	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Column(name = "reason")
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "from_date", length = 10)
	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "to_date", length = 10)
	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Column(name = "statuss", length = 50)
	public String getStatuss() {
		return this.statuss;
	}

	public void setStatuss(String statuss) {
		this.statuss = statuss;
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

}

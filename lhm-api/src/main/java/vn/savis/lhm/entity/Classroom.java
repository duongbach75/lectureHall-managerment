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
@Table(name = "classroom", catalog = "qlgd")
public class Classroom implements java.io.Serializable {

	private Integer idClassroom;
	private StaffIncharge staffIncharge;
	private Amphitheater amphitheater;
	private String symbol;
	private String nameClassroom;
	private int amount;
	private String size;
	private String chucNang;
	private Date createTime;
	private String createdBy;
	private Date updateTime;
	private String updatedBy;
	private String statuss;

	public Classroom() {
	}

	public Classroom(Amphitheater amphitheater, String symbol, String nameClassroom, int amount, String size,
			String chucNang, String statuss) {
		this.amphitheater = amphitheater;
		this.symbol = symbol;
		this.nameClassroom = nameClassroom;
		this.amount = amount;
		this.size = size;
		this.chucNang = chucNang;
		this.statuss = statuss;
	}

    @ManyToOne
    @JoinColumn(name = "id_staff_in_charge")
    public StaffIncharge getStaffIncharge() {
        return staffIncharge;
    }


    public void setStaffIncharge(StaffIncharge staffIncharge) {
        this.staffIncharge = staffIncharge;
    }

    @Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_classroom", unique = true, nullable = false)
	public Integer getIdClassroom() {
		return this.idClassroom;
	}

	public void setIdClassroom(Integer idClassroom) {
		this.idClassroom = idClassroom;
	}

	@ManyToOne
	@JoinColumn(name = "id_amphitheater", nullable = false)
	public Amphitheater getAmphitheater() {
		return this.amphitheater;
	}

	public void setAmphitheater(Amphitheater amphitheater) {
		this.amphitheater = amphitheater;
	}

	@Column(name = "symbol", nullable = false, length = 50)
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Column(name = "name_classroom", nullable = false)
	public String getNameClassroom() {
		return this.nameClassroom;
	}

	public void setNameClassroom(String nameClassroom) {
		this.nameClassroom = nameClassroom;
	}

	@Column(name = "amount", nullable = false)
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Column(name = "size", nullable = false, length = 50)
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "chuc_nang", nullable = false, length = 50)
	public String getChucNang() {
		return this.chucNang;
	}

	public void setChucNang(String chucNang) {
		this.chucNang = chucNang;
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

	@Column(name = "statuss", nullable = false, length = 50)
	public String getStatuss() {
		return this.statuss;
	}

	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}


}

package entities;
// Generated 2017-10-02 19:26:42 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
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
 * Staff generated by hbm2java
 */
@Entity
@Table(name = "staff", catalog = "simplegymdb")
public class Staff implements java.io.Serializable {

	private Integer staffId;
	private Address address;
	private Classes classes;
	private String firstName;
	private String lastName;
	private String email;
	private String mobilePhone;
	private String position;
	private BigDecimal salary;
	private Date lastUpdate;
	private Set<Classes> classeses = new HashSet<Classes>(0);

	public Staff() {
	}

	public Staff(Address address, String firstName, String lastName, String mobilePhone, Date lastUpdate) {
		this.address = address;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobilePhone = mobilePhone;
		this.lastUpdate = lastUpdate;
	}

	public Staff(Address address, Classes classes, String firstName, String lastName, String email, String mobilePhone,
			String position, BigDecimal salary, Date lastUpdate, Set<Classes> classeses) {
		this.address = address;
		this.classes = classes;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.position = position;
		this.salary = salary;
		this.lastUpdate = lastUpdate;
		this.classeses = classeses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "staff_id", unique = true, nullable = false)
	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", nullable = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classes_id")
	public Classes getClasses() {
		return this.classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	@Column(name = "first_name", nullable = false, length = 20)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 40)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "mobile_phone", nullable = false, length = 12)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "position", length = 30)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "salary", precision = 10)
	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_update", nullable = false, length = 10)
	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
	public Set<Classes> getClasseses() {
		return this.classeses;
	}

	public void setClasseses(Set<Classes> classeses) {
		this.classeses = classeses;
	}

}

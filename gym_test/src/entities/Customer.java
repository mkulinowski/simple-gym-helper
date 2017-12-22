package entities;
// Generated 2017-10-02 19:26:42 by Hibernate Tools 5.2.3.Final

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
 * Customer generated by hbm2java
 */
@Entity
@Table(name = "customer", catalog = "simplegymdb")
public class Customer implements java.io.Serializable {

	private Integer customerId;
	private Address address;
	private PaymentOption paymentOption;
	private String firstName;
	private String lastName;
	private String email;
	private String mobilePhone;
	private Date payOptUpdate;
	private Date joinDate;
	private Set<Payment> payments = new HashSet<Payment>(0);
	private Set<Activities> activitieses = new HashSet<Activities>(0);

	public Customer() {
	}

	public Customer(PaymentOption paymentOption, String firstName, String lastName, Date payOptUpdate, Date joinDate) {
		this.paymentOption = paymentOption;
		this.firstName = firstName;
		this.lastName = lastName;
		this.payOptUpdate = payOptUpdate;
		this.joinDate = joinDate;
	}

	public Customer(Address address, PaymentOption paymentOption, String firstName, String lastName, String email,
			String mobilePhone, Date payOptUpdate, Date joinDate, Set<Payment> payments, Set<Activities> activitieses) {
		this.address = address;
		this.paymentOption = paymentOption;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.payOptUpdate = payOptUpdate;
		this.joinDate = joinDate;
		this.payments = payments;
		this.activitieses = activitieses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "customer_id", unique = true, nullable = false)
	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_opt_id", nullable = false)
	public PaymentOption getPaymentOption() {
		return this.paymentOption;
	}

	public void setPaymentOption(PaymentOption paymentOption) {
		this.paymentOption = paymentOption;
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

	@Column(name = "mobile_phone", length = 12)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "pay_opt_update", nullable = false, length = 10)
	public Date getPayOptUpdate() {
		return this.payOptUpdate;
	}

	public void setPayOptUpdate(Date payOptUpdate) {
		this.payOptUpdate = payOptUpdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "join_date", nullable = false, length = 10)
	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", address=" + address + ", paymentOption=" + paymentOption
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", mobilePhone="
				+ mobilePhone + ", payOptUpdate=" + payOptUpdate + ", joinDate=" + joinDate + ", payments=" + payments
				+ ", activitieses=" + activitieses + "]";
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Activities> getActivitieses() {
		return this.activitieses;
	}

	public void setActivitieses(Set<Activities> activitieses) {
		this.activitieses = activitieses;
	}

}
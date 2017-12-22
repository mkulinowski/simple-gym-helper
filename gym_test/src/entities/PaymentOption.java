package entities;
// Generated 2017-10-02 19:26:42 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
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

/**
 * PaymentOption generated by hbm2java
 */
@Entity
@Table(name = "payment_option", catalog = "simplegymdb")
public class PaymentOption implements java.io.Serializable {

	private Integer id;
	private String name;
	private BigDecimal price;
	private String duration;
	private Set<Customer> customers = new HashSet<Customer>(0);

	public PaymentOption() {
	}

	public PaymentOption(String name, BigDecimal price, String duration) {
		this.name = name;
		this.price = price;
		this.duration = duration;
	}

	public PaymentOption(String name, BigDecimal price, String duration, Set<Customer> customers) {
		this.name = name;
		this.price = price;
		this.duration = duration;
		this.customers = customers;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price", nullable = false, precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "duration", nullable = false, length = 12)
	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentOption")
	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

}
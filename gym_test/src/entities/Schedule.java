package entities;
// Generated 2017-10-02 19:26:42 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Schedule generated by hbm2java
 */
@Entity
@Table(name = "schedule", catalog = "simplegymdb")
public class Schedule implements java.io.Serializable {

	private Date classDate;
	private Classes classes;

	public Schedule() {
	}

	public Schedule(Date classDate, Classes classes) {
		this.classDate = classDate;
		this.classes = classes;
	}

	@Id

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "class_date", unique = true, nullable = false, length = 19)
	public Date getClassDate() {
		return this.classDate;
	}

	public void setClassDate(Date classDate) {
		this.classDate = classDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id", nullable = false)
	public Classes getClasses() {
		return this.classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

}
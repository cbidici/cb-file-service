package com.cb.file.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * BaseEntity is a base class for all entities. <br/>
 * It provides basic attributes such as id, create_date for all entities.
 * 
 * @author cbidici
 * @since 0.0.1
 */
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid")
	@Column(updatable = false, nullable = false)
	private String id;

	private Date createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreate_date(Date createDate) {
		this.createDate = createDate;
	}

}

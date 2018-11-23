package com.cb.file.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * BaseEntity is a base class for all entities. <br/>
 * 
 * @author cbidici
 * @since 0.0.1
 */
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid2")
	@Column(updatable = false, nullable = false)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

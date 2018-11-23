package com.cb.file.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cb_file")
public class File extends BaseEntity {

	private String fileId;

	private String name;

	private String path;

	private String url;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

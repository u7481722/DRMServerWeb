package com.ubn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(
		name="EMP_GEN",
		table="GENERATOR_TABLE",
		pkColumnName="pkName",
		valueColumnName="pkValue",
		pkColumnValue="DCF",
		initialValue=1,
		allocationSize=1)
public class DCF extends BaseProperty {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String cek;
	
	private String contentId;
	
	private String roTemplate;
	
	private String contentType;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EMP_GEN")
	@Column(name = "ID")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "CEK", length = 5000)
	public String getCek() {
		return this.cek;
	}

	public void setCek(String cek) {
		this.cek = cek;
	}

	@Column(name = "CONTENT_ID", length = 50)
	public String getContentId() {
		return this.contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	@Column(name = "RO_TEMPLATE", length = 5000)
	public String getRoTemplate() {
		return this.roTemplate;
	}

	public void setRoTemplate(String roTemplate) {
		this.roTemplate = roTemplate;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}

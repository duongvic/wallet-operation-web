package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans;

import java.io.Serializable;
import java.util.Date;

public class CustomerRole implements Serializable{
	private Long id;
	private Long customerId;
	private Long roleId;
	private Boolean active;
	private String codeName;
	private String name;

	protected Long creator;
	protected Date createdTime;
	protected Long lastUpdater;
	protected Date lastUpdatedTime;
	protected String orgUnit;
	protected Date validFromDate;
	protected Date validToDate;

	public CustomerRole(Long id, Long customerId, Long roleId, Boolean active, Long creator,
			Date createdTime, Long lastUpdater, Date lastUpdatedTime, String orgUnit,
			Date validFromDate, Date validToDate) {
		this.id = id;
		this.customerId = customerId;
		this.roleId = roleId;
		this.active = active;
		this.creator = creator;
		this.createdTime = createdTime;
		this.lastUpdater = lastUpdater;
		this.lastUpdatedTime = lastUpdatedTime;
		this.orgUnit = orgUnit;
		this.validFromDate = validFromDate;
		this.validToDate = validToDate;
	}

	public CustomerRole() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Boolean getActive() {
		return active;
	}

	public Long getCreator() {
		return creator;
	}
	
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public Long getLastUpdater() {
		return lastUpdater;
	}
	
	public void setLastUpdater(Long lastUpdater) {
		this.lastUpdater = lastUpdater;
	}
	
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}

	public Date getValidFromDate() {
		return validFromDate;
	}

	public void setValidFromDate(Date validFromDate) {
		this.validFromDate = validFromDate;
	}

	public Date getValidToDate() {
		return validToDate;
	}

	public void setValidToDate(Date validToDate) {
		this.validToDate = validToDate;
	}
}

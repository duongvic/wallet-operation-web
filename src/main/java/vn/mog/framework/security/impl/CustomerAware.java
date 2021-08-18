package vn.mog.framework.security.impl;

import vn.mog.framework.security.api.ICustomerAware;

public class CustomerAware implements ICustomerAware {
    private Long id;
    private String cif;
    private String username;
    private String phone;
    private String email;
    private String fullname;
    private Integer type;
    private boolean verifiedEmail;
    private boolean verifiedPhone;

    public CustomerAware() {
      super();
    }

    @Override
    public Long getId() {
	return id;
    }

    @Override
    public void setId(Long id) {
	this.id = id;
    }

    @Override
    public String getCif() {
	return cif;
    }

    @Override
    public void setCif(String cif) {
	this.cif = cif;
    }

    @Override
    public String getName() {
	return cif;
    }

    @Override
    public String getUsername() {
	// TODO Auto-generated method stub
	return username;
    }

    @Override
    public void setUsername(String username) {
	// TODO Auto-generated method stub
	this.username = username;
    }

    @Override
    public String getFullname() {
	return fullname;
    }

    @Override
    public void setFullname(String fullname) {
	this.fullname = fullname;
    }

    @Override
    public String getPhone() {
	return phone;
    }

    @Override
    public void setPhone(String phone) {
	this.phone = phone;
    }

    @Override
    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public String getEmail() {
	return email;
    }

    @Override
    public Integer getType() {
	// TODO Auto-generated method stub
	return type;
    }

    @Override
    public void setType(Integer type) {
	// TODO Auto-generated method stub
	this.type = type;
    }

    @Override
    public boolean isVerifiedEmail() {
	return verifiedEmail;
    }

    @Override
    public boolean isVerifiedPhone() {
	return verifiedPhone;
    }

    @Override
    public void setVerifiedEmail(boolean verifiedEmail) {
	this.verifiedEmail = verifiedEmail;
    }

    @Override
    public void setVerifiedPhone(boolean verifiedPhone) {
	this.verifiedPhone = verifiedPhone;
    }
}

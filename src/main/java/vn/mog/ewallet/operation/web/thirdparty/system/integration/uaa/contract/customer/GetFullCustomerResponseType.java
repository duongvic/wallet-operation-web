package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import java.util.Collections;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Address;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.CustomerAttribute;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.CustomerBankAccount;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.CustomerContract;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Identity;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetFullCustomerResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  protected Customer customer;
  protected List<Address> addresses;
  protected List<CustomerBankAccount> bankAccounts;
  protected List<CustomerContract> contracts;
  protected List<Identity> identities;
  protected List<CustomerAttribute> attributes;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public List<CustomerBankAccount> getBankAccounts() {
    return bankAccounts;
  }

  public void setBankAccounts(List<CustomerBankAccount> bankAccounts) {
    this.bankAccounts = bankAccounts;
  }

  public List<CustomerContract> getContracts() {
    return contracts;
  }

  public void setContracts(List<CustomerContract> contracts) {
    this.contracts = contracts;
  }

  public List<Identity> getIdentities() {
    return identities == null ? Collections.emptyList() : identities;
  }

  public void setIdentities(List<Identity> identities) {
    this.identities = identities;
  }

  public List<CustomerAttribute> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<CustomerAttribute> attributes) {
    this.attributes = attributes;
  }
}

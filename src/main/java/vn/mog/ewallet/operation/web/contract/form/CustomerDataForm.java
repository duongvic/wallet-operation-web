package vn.mog.ewallet.operation.web.contract.form;

import java.io.Serializable;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Customer;


public class CustomerDataForm implements Serializable {

  private Long id;
  private String fullName;
  private String textSearch;
  private List<Integer> customerTypes;
  private List<Integer> blackLists;

  MultipartFile[] files;

  public CustomerDataForm() {
  }
  public boolean isExistFile() {
    return files != null && files.length > 0;
  }

  public MultipartFile[] getFiles() {
    return files;
  }

  public void setFiles(MultipartFile[] files) {
    this.files = files;
  }

  public CustomerDataForm(Customer item) {
    this.id = item.getId();
    this.fullName = item.getFullName();
  }

  public String getTextSearch() {
    return textSearch;
  }

  public void setTextSearch(String textSearch) {
    this.textSearch = textSearch;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public List<Integer> getCustomerTypes() {
    return customerTypes;
  }

  public void setCustomerTypes(List<Integer> customerTypes) {
    this.customerTypes = customerTypes;
  }

  public List<Integer> getBlackLists() {
    return blackLists;
  }

  public void setBlackLists(List<Integer> blackLists) {
    this.blackLists = blackLists;
  }
}

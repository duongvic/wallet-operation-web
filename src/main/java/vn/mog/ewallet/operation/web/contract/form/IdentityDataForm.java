package vn.mog.ewallet.operation.web.contract.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import vn.mog.ewallet.operation.web.contract.IdentityType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Attachment;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Identity;

public class IdentityDataForm implements Serializable {


  private Long id;
  private Long customerId;
  private Integer identityType;
  private String identityNumber;
  private Date issueDate;
  private Date expireDate;

  private String placeOfIssue;

  private Attachment frontFaceImage;
  private Attachment backFaceImage;
  private Attachment selfieImage;


  public void setIdentities(List<Identity> identities) {
    for (Identity item : identities) {
      if (IdentityType.CitizenshipCard.equals(item.getIdentityType()) ||
          IdentityType.Passport.equals(item.getIdentityType()) ||
          IdentityType.IdentityCard.equals(item.getIdentityType())) {
        //this.full_name = item.getFullname();
        this.identityNumber = item.getIdentity();
        this.identityType = item.getIdentityType();
        this.placeOfIssue = item.getIssuePlace();
        this.issueDate = item.getDateIssued();
        this.expireDate = item.getDateExpires();
        this.id = item.getId();
        this.customerId = item.getCustomerId();
      }
    }
  }

  public Long getId() {
    return id;
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

  public Integer getIdentityType() {
    return identityType;
  }

  public void setIdentityType(Integer identityType) {
    this.identityType = identityType;
  }

  public String getIdentityNumber() {
    return identityNumber;
  }

  public void setIdentityNumber(String identityNumber) {
    this.identityNumber = identityNumber;
  }

  public Date getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(Date issueDate) {
    this.issueDate = issueDate;
  }

  public String textIssueDate() {
    if (this.issueDate != null) {
      return DateFormatUtils.format(issueDate, "dd/MM/yyyy");
    } else {
      return StringUtils.EMPTY;
    }
  }

  public Date getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
  }

  public String textExpireDate() {
    if (this.expireDate != null) {
      return DateFormatUtils.format(expireDate, "dd/MM/yyyy");
    } else {
      return StringUtils.EMPTY;
    }
  }

  public String getPlaceOfIssue() {
    return placeOfIssue;
  }

  public void setPlaceOfIssue(String placeOfIssue) {
    this.placeOfIssue = placeOfIssue;
  }

  public Attachment getFrontFaceImage() {
    return frontFaceImage;
  }

  public String getSrcFrontFaceImage() {
    if (this.frontFaceImage == null) {
      return null;
    } else if (this.frontFaceImage.getContent() == null) {
      return null;
    } else {
      return this.getFrontFaceImage().getBase64Image();
    }
  }

  public void setFrontFaceImage(Attachment frontFaceImage) {
    this.frontFaceImage = frontFaceImage;
  }

  public Attachment getBackFaceImage() {
    return backFaceImage;
  }

  public String getSrcBackFaceImage() {
    if (this.backFaceImage == null) {
      return null;
    } else if (this.backFaceImage.getContent() == null) {
      return null;
    } else {
      return this.getBackFaceImage().getBase64Image();
    }
  }

  public void setBackFaceImage(Attachment backFaceImage) {
    this.backFaceImage = backFaceImage;
  }

  public Attachment getSelfieImage() {
    return selfieImage;
  }

  public String getSrcSelfieFaceImage() {
    if (this.selfieImage == null) {
      return null;
    } else if (this.selfieImage.getContent() == null) {
      return null;
    } else {
      return this.getSelfieImage().getBase64Image();
    }
  }

  public void setSelfieImage(Attachment selfieImage) {
    this.selfieImage = selfieImage;
  }
}

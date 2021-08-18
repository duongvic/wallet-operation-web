package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TransactionCancel {

  private Long id;
  private Date createdDate;
  private String service;
  private String merchant;
  private String requester;
  private Long amount;
  private Long fee;
  private Long totalAmount;
  private Long preBalance;
  private Long postBalance;
  private Integer status;
  private String approver;
  private Integer process;

  public TransactionCancel(Long id, Date createdDate, String service, String merchant, String requester, Long amount, Long fee, Long totalAmount,
      Long preBalance, Long postBalance, Integer status, String approver, Integer process) {
    this.id = id;
    this.createdDate = createdDate;
    this.service = service;
    this.merchant = merchant;
    this.requester = requester;
    this.amount = amount;
    this.fee = fee;
    this.totalAmount = totalAmount;
    this.preBalance = preBalance;
    this.postBalance = postBalance;
    this.status = status;
    this.approver = approver;
    this.process = process;
  }

  public static String getStatusCode(int code) {
    return Status.list().get(code);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getMerchant() {
    return merchant;
  }

  public void setMerchant(String merchant) {
    this.merchant = merchant;
  }

  public String getRequester() {
    return requester;
  }

  public void setRequester(String requester) {
    this.requester = requester;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getFee() {
    return fee;
  }

  public void setFee(Long fee) {
    this.fee = fee;
  }

  public Long getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Long totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Long getPreBalance() {
    return preBalance;
  }

  public void setPreBalance(Long preBalance) {
    this.preBalance = preBalance;
  }

  public Long getPostBalance() {
    return postBalance;
  }

  public void setPostBalance(Long postBalance) {
    this.postBalance = postBalance;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getApprover() {
    return approver;
  }

  public void setApprover(String approver) {
    this.approver = approver;
  }

  public Integer getProcess() {
    return process;
  }

  public void setProcess(Integer process) {
    this.process = process;
  }

  public static class Process {

    public static final int INIT = 0;
    public static final int REJECT = 0;
    public static final int APPROVE = 0;

    public static Object list() {
      HashMap<Integer, String> hash = new HashMap<>();
      hash.put(INIT, "Khởi tạo");
      hash.put(REJECT, "Từ chối");
      hash.put(APPROVE, "Đã duyệt");
      return hash;
    }
  }

  public static class Status {

    public static final int INIT = 0;
    public static final int FAIL = 1;
    public static final int SUCCESS = 2;

    public static Map<Integer, String> list() {
      Map<Integer, String> hash = new HashMap<>();
      hash.put(INIT, "Khởi tạo");
      hash.put(FAIL, "Thất bại");
      hash.put(SUCCESS, "Thành công");
      return hash;
    }

  }
}

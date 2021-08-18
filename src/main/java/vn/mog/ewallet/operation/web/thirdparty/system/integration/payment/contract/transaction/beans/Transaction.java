package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans;


import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderChannelType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.WalletSourceOfFundType;
import vn.mog.framework.contract.base.TraceableRequestType;

import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.SourceOfFundType.SOF_ID_TYPES;


public class Transaction implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long id;
  private String traceNo;
  private Long refTxnId;
  private String serviceType;
  // Service
  private Long serviceId;
  private String serviceCode;
  private String serviceName;
  private String serviceShortName;

  private String terminalId;
  private String sourceOfFundId;
  private String orderChannel;
  private String orderId;
  private String orderInfo;

  private String cif;
  private String username;
  private String phoneNumber; // Hệ thống sử dụng phoneNumber = tên đăng nhập

  private Long payerId;
  private String payerUsername;
  private String payerFullname;
  private String payerMsisdn;




  private Long payeeId;
  private String payeeUsername;
  private String payeeFullname;
  private String payeeMsisdn;

  private Long idOwner;
  private Integer idOwnerCustomerType;

  private String creatorUsername;
  private String approverUsername;

  private Long amount;
  private String currency;
  private Long fee;
  private Long commision;
  private Long discount;
  private Long cashback;
  private Long capitalValue;
  private Long realAmount;
  private Long preBalance;
  private Long postBalance;

  private Boolean test;
  private Boolean locked;
  private Boolean autoCapture;
  private Date expiration;

  private String text;
  private String providerCode;

  private TransactionEvent lastEvent;
  private Date lastEventTime;

  private Integer errorCode;
  private String errorMessage;
  private Integer transactionStatus;
  private Date creationDate;

  // TransResult (card)
  private List<Transaction.Card> serials;

  // Transaction Attribute (we will order this)
  private List<TransactionAttribute> attributes;

  private Long grossProfit;

  private String paymentChannelId;

  private String extraInfo;

  public Transaction() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTraceNo() {
    return traceNo;
  }

  public void setTraceNo(String traceNo) {
    this.traceNo = traceNo;
  }

  public Long getRefTxnId() {
    return refTxnId;
  }

  public void setRefTxnId(Long refTxnId) {
    this.refTxnId = refTxnId;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  public String getSourceOfFundId() {
    return sourceOfFundId;
  }

  public void setSourceOfFundId(String sourceOfFundId) {
    this.sourceOfFundId = sourceOfFundId;
  }

  public String getOrderChannel() {
    return orderChannel;
  }

  public void setOrderChannel(String orderChannel) {
    this.orderChannel = orderChannel;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getOrderInfo() {
    return orderInfo;
  }

  public void setOrderInfo(String orderInfo) {
    this.orderInfo = orderInfo;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getPayerId() {
    return payerId;
  }

  public void setPayerId(Long payerId) {
    this.payerId = payerId;
  }

  public Long getPayeeId() {
    return payeeId;
  }

  public void setPayeeId(Long payeeId) {
    this.payeeId = payeeId;
  }

  public String getPayerUsername() {
    return payerUsername;
  }

  public void setPayerUsername(String payerUsername) {
    this.payerUsername = payerUsername;
  }


  public String getPayerFullname() {
    return payerFullname;
  }

  public void setPayerFullname(String payerFullname) {
    this.payerFullname = payerFullname;
  }
  public String getPayerMsisdn() {
    return payerMsisdn;
  }

  public void setPayerMsisdn(String payerMsisdn) {
    this.payerMsisdn = payerMsisdn;
  }

  public String getPayeeUsername() {
    return payeeUsername;
  }

  public void setPayeeUsername(String payeeUsername) {
    this.payeeUsername = payeeUsername;
  }

  public String getPayeeFullname() {
    return payeeFullname;
  }

  public void setPayeeFullname(String payeeFullname) {
    this.payeeFullname = payeeFullname;
  }

  public String getPayeeMsisdn() {
    return payeeMsisdn;
  }

  public void setPayeeMsisdn(String payeeMsisdn) {
    this.payeeMsisdn = payeeMsisdn;
  }

  public Long getIdOwner() {
    return idOwner;
  }

  public void setIdOwner(Long idOwner) {
    this.idOwner = idOwner;
  }

  public Integer getIdOwnerCustomerType() {
    return idOwnerCustomerType;
  }

  public void setIdOwnerCustomerType(Integer idOwnerCustomerType) {
    this.idOwnerCustomerType = idOwnerCustomerType;
  }

  public String getCreatorUsername() {
    return creatorUsername;
  }

  public void setCreatorUsername(String creatorUsername) {
    this.creatorUsername = creatorUsername;
  }

  public String getApproverUsername() {
    return approverUsername;
  }

  public void setApproverUsername(String approverUsername) {
    this.approverUsername = approverUsername;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Long getFee() {
    return fee;
  }

  public void setFee(Long fee) {
    this.fee = fee;
  }

  public Long getCommision() {
    return commision;
  }

  public void setCommision(Long commision) {
    this.commision = commision;
  }

  public Long getDiscount() {
    return discount;
  }

  public void setDiscount(Long discount) {
    this.discount = discount;
  }

  public Long getCashback() {
    return cashback;
  }

  public void setCashback(Long cashback) {
    this.cashback = cashback;
  }

  public Long getCapitalValue() {
    return capitalValue;
  }

  public void setCapitalValue(Long capitalValue) {
    this.capitalValue = capitalValue;
  }

  public Long getRealAmount() {
    return realAmount;
  }

  public void setRealAmount(Long realAmount) {
    this.realAmount = realAmount;
  }

  public Date getExpiration() {
    return expiration;
  }

  public void setExpiration(Date expiration) {
    this.expiration = expiration;
  }

  public Boolean getTest() {
    return test;
  }

  public void setTest(Boolean test) {
    this.test = test;
  }

  public Boolean getLocked() {
    return locked;
  }

  public void setLocked(Boolean locked) {
    this.locked = locked;
  }

  public Boolean getAutoCapture() {
    return autoCapture;
  }

  public void setAutoCapture(Boolean autoCapture) {
    this.autoCapture = autoCapture;
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

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }

  public TransactionEvent getLastEvent() {
    return lastEvent;
  }

  public void setLastEvent(TransactionEvent lastEvent) {
    this.lastEvent = lastEvent;
  }

  public Date getLastEventTime() {
    return lastEventTime;
  }

  public void setLastEventTime(Date lastEventTime) {
    this.lastEventTime = lastEventTime;
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public Integer getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(Integer transactionStatus) {
    this.transactionStatus = transactionStatus;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public List<Transaction.Card> getSerials() {
    return serials;
  }

  public void setSerials(List<Transaction.Card> serials) {
    this.serials = serials;
  }
 
  public String getServiceShortName() {
    return serviceShortName;
  }

  public void setServiceShortName(String serviceShortName) {
    this.serviceShortName = serviceShortName;
  }
  

  public String getTransactionType() {
    if (ServiceType.FUND_IN.name().equals(serviceType)) {
      return "+";
    } else {
      return "-";
    }
  }

  public String getService() {
    return ServiceType.FULL_SERVICE_TYPES.get(serviceType);
  }

  public String getStatus() {
    return TxnStatus.TRANSACTION_STATUSES.get(transactionStatus);
  }

  public String getStringStatus() {
    switch (transactionStatus) {
      case TxnStatus.CANCELLED:
        return "CANCELLED";
      case TxnStatus.CLOSED:
        return "CLOSED";
      case TxnStatus.FAILED:
        return "FAILED";
      case TxnStatus.INITIAL:
        return "INITIAL";
      case TxnStatus.OPENED:
        return "OPENED";
      case TxnStatus.REVERSED:
        return "REVERSED";
      case TxnStatus.HOLD:
        return "HOLD";
      case TxnStatus.WAITTING:
        return "WAITTING";
      case TxnStatus.LOCKING:
        return "LOCKING";
      default:
        return StringUtils.EMPTY;
    }
  }

  public String getSourceOfFundName() {
    if (SOF_ID_TYPES.containsKey(this.sourceOfFundId)) {
      return SOF_ID_TYPES.get(this.sourceOfFundId);
    }
    return StringUtils.EMPTY;
  }

  public String textOrderChannel() {
    FundOrderChannelType fundOrderChannelType
        = FundOrderChannelType.getFundOrderChannel(this.orderChannel);

    if (fundOrderChannelType == null) {
      return this.orderChannel;
    }

    return fundOrderChannelType.displayText;
  }

  public String getWalletSourceOfFund() {
    WalletSourceOfFundType walletSourceOfFundType
        = WalletSourceOfFundType.getWalletSourceOfFund(this.orderChannel);

    if (walletSourceOfFundType == null) {
      return this.orderChannel;
    }

    return walletSourceOfFundType.getDisplayText();
  }

  public String getOwnerCustomerTypeName() {
    if (CustomerType.FULL_CUSTOMER_TYPES.containsKey(this.idOwnerCustomerType)) {
      return CustomerType.FULL_CUSTOMER_TYPES.get(this.idOwnerCustomerType);
    }

    return String.valueOf(this.idOwnerCustomerType);
  }

  public boolean isBuyCard() {
    return ServiceType.EPIN.name().equals(serviceType);
  }

  public List<TransactionAttribute> getAttributes() {
    if (attributes == null) {
      attributes = Collections.emptyList();
    }
    return attributes;
  }

  public void setAttributes(List<TransactionAttribute> attributes) {
    this.attributes = attributes;
  }

  public TransactionAttribute getTransactionAttributeByType(TransactionAttributeType transactionAttributeType) {
    for(TransactionAttribute attribute : attributes) {
      if(transactionAttributeType.name().equalsIgnoreCase(attribute.getTransactionAttributeType()))
        return attribute;
    }
    return null;
  }

  public Long getGrossProfit() {
    return grossProfit;
  }

  public void setGrossProfit(Long grossProfit) {
    this.grossProfit = grossProfit;
  }

  public String getPaymentChannelId() {
    return paymentChannelId;
  }

  public void setPaymentChannelId(String paymentChannelId) {
    this.paymentChannelId = paymentChannelId;
  }

  public String getExtraInfo() {
    return extraInfo;
  }

  public void setExtraInfo(String extraInfo) {
    this.extraInfo = extraInfo;
  }

  public static class Card implements Serializable {

    private String serial;
    private String pin;
    private Double price;
    private String cardType;
    private Date expiredDate;

    public Card() {
    }

    public Card(String serial, String pin, Double price, String cardType, Date expiredDate) {
      super();
      this.serial = serial;
      this.pin = pin;
      this.price = price;
      this.cardType = cardType;
      this.expiredDate = expiredDate;
    }

    public String getSerial() {
      return serial;
    }

    public void setSerial(String serial) {
      this.serial = serial;
    }

    public String getPin() {
      return pin;
    }

    public void setPin(String pin) {
      this.pin = pin;
    }

    public Double getPrice() {
      return price;
    }

    public void setPrice(Double price) {
      this.price = price;
    }

    public String getCardType() {
      return cardType;
    }

    public void setCardType(String cardType) {
      this.cardType = cardType;
    }

    public Date getExpiredDate() {
      return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
      this.expiredDate = expiredDate;
    }


  }
}

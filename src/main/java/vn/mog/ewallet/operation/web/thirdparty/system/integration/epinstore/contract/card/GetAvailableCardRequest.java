package vn.mog.ewallet.operation.web.thirdparty.system.integration.epinstore.contract.card;

import java.util.List;

@SuppressWarnings("serial")
public class GetAvailableCardRequest extends GetAvailableCardRequestType {

  private List<String> cardTypes;

  private String payerCif;

  public GetAvailableCardRequest() {
  }

  public GetAvailableCardRequest(List<String> cardTypes) {
    this.setCardTypes(cardTypes);
  }

  public List<String> getCardTypes() {
    return cardTypes;
  }

  public void setCardTypes(List<String> cardTypes) {
    this.cardTypes = cardTypes;
  }

  public String getPayerCif() {
    return payerCif;
  }

  public void setPayerCif(String payerCif) {
    this.payerCif = payerCif;
  }
}

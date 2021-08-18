package vn.mog.ewallet.operation.web.thirdparty.system.integration.epinstore.contract.card;

import java.util.Map;

@SuppressWarnings("serial")
public class GetAvailableCardResponse extends GetAvailableCardResponseType {

  private Map<String, Map<Integer, Long>> availableCard;

  public GetAvailableCardResponse() {
  }

  public Map<String, Map<Integer, Long>> getAvailableCard() {
    return availableCard;
  }

  public void setAvailableCard(Map<String, Map<Integer, Long>> availableCard) {
    this.availableCard = availableCard;
  }
}

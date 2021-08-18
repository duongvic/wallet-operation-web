package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.beans;

import org.apache.commons.lang3.StringUtils;

public enum QuickSearchType {
  SEARCH_ALL("","label.search.by"),
  SEARCH_BY_NAME("SEARCH_BY_NAME","setting.account.tbl.col.full.name"), 
  SEARCH_BY_MSISDN("SEARCH_BY_MSISDN","setting.account.tbl.col.phone"), 
  SEARCH_BY_CIF("SEARCH_BY_CIF","setting.account.tbl.col.cif");
  
  public String code;
  public String displayText;

  QuickSearchType(String code, String displayText) {
    this.code = code;
    this.displayText = displayText;
  }


  public String value() {
    return this.code;
  }

  public String displayText() {
    return this.displayText;
  }

  public String selectedQuickSearchType(String value) {
    if (String.valueOf(this.code).equals(value)) {
      return "selected";
    }
    return StringUtils.EMPTY;
  }

  public QuickSearchType getQuickSearchTypeName(String name) {
    if (StringUtils.isNotBlank(name)) {
      for (QuickSearchType quickSearchType : QuickSearchType.values()) {
        if (name.equals(quickSearchType.name())) {
          return quickSearchType;
        }
      }
    }

    return null;
  }
  
}

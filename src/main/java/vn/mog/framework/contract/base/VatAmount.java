package vn.mog.framework.contract.base;

import java.io.Serializable;
import java.math.BigDecimal;

public class VatAmount implements Serializable {

    private static final long serialVersionUID = 1L;

    protected BigDecimal value;

    protected String currency;

    public BigDecimal getValue() {
	return this.value;
    }

    public void setValue(BigDecimal value) {
	this.value = value;
    }

    public String getCurrency() {
	return this.currency;
    }

    public void setCurrency(String value) {
	this.currency = value;
    }
}

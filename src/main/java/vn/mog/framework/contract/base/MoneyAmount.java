package vn.mog.framework.contract.base;

import java.io.Serializable;

public class MoneyAmount implements Serializable {

    private static final long serialVersionUID = 1L;

    protected long value;

    protected String currency;

    public long getValue() {
	return this.value;
    }

    public void setValue(long value) {
	this.value = value;
    }

    public String getCurrency() {
	return this.currency;
    }

    public void setCurrency(String value) {
	this.currency = value;
    }
}

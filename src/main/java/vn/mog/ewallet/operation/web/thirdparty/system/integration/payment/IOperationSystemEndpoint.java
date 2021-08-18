package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment;

import java.util.List;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.KeyPairItem;


public interface IOperationSystemEndpoint {

  List<KeyPairItem> listPaymentChannel() throws FrontEndException;

}

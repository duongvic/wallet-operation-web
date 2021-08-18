package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment;

import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.*;

public interface ITransactionStatisticEndpoint {

        /* +-------------------+
           |      BY DATE      |
           +-------------------+ */

  GeneralResponse<Object> createTransactionStatisticByDate(CreateTransactionStatisticByDateRequest request) throws FrontEndException;

  GeneralResponse<Object> getTransactionStatisticByDate(GetTransactionStatisticByDateRequest request) throws FrontEndException;

  GeneralResponse<Object> summaryTransactionStatisticByDate(SummaryTransactionStatisticByDateRequest request) throws FrontEndException;

        /* +-------------------+
           |      BY HOUR      |
           +-------------------+ */

  GeneralResponse<Object> createTransactionStatisticByHour(CreateTransactionStatisticByHourRequest request) throws FrontEndException;

  GeneralResponse<Object> getTransactionStatisticByHour(GetTransactionStatisticByHourRequest request) throws FrontEndException;

  GeneralResponse<Object> summaryTransactionStatisticByHour(SummaryTransactionStatisticByHourRequest request) throws FrontEndException;
}

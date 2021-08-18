package vn.mog.ewallet.operation.web.thirdparty.system.integration.stock;

import vn.mog.framework.contract.base.KeyValue;

import java.util.List;

public interface IStockEndpoint {

  FindBankHistoryResponse findBankHistories(FindBankHistoryRequest request) throws Exception;

  BankBalanceStatisticResponse findBankBlanceStatics(BankBalanceStatisticRequest request) throws Exception;

  List<KeyValue> getBankCode () throws Exception;

  CreateBankHistoryResponse createBankHistory(CreateBankHistoryRequest request) throws Exception;

  FindBankAccountsResponse  findBankAccount (FindBankAccountsRequest request) throws Exception;

  GetBankHistoryDetailResponse getBankAccount(GetBankHistoryDetailRequest request) throws Exception;

  UpdateBankHistoryResponse updateBankAccount(UpdateBankHistoryRequest request) throws Exception;

  DeleteBankHistoryResponse deleteBankAccount(DeleteBankHistoryRequest request) throws Exception;
}

package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@ToString
//@Getter
//@Setter
//@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
//su dung lombok ko parse dc json, chua ro li do, check sau
public class VoucherRange {

  private Long amount;
  private String voucherCode;

  public VoucherRange() {
  }

  public VoucherRange(Long amount, String voucherCode) {
    this.amount = amount;
    this.voucherCode = voucherCode;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getVoucherCode() {
    return voucherCode;
  }

  public void setVoucherCode(String voucherCode) {
    this.voucherCode = voucherCode;
  }

  //  public static void main(String[] args) {
//    List<VoucherRange> voucherRanges = new ArrayList<>();
//    voucherRanges.add(new VoucherRange(0L, "VOUCHER_1"));
//    voucherRanges.add(new VoucherRange(1000000L, "VOUCHER_3"));
//    voucherRanges.add(new VoucherRange(4000000L, "VOUCHER_4"));
//    voucherRanges.add(new VoucherRange(500000L, "VOUCHER_2"));
//    System.out.println(voucherRanges);
//
//    voucherRanges = voucherRanges.stream().sorted(Comparator.comparingLong(VoucherRange::getAmount)).collect(
//        Collectors.toList());
//
//    Gson son = new Gson();
//    System.out.println(son.toJson(voucherRanges));
//
//  }

}
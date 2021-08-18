package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans;

public enum LocationType {
  Country(0, "Country"),
  Province(1, "Province"),
  District(2, "District"),
  Commune(3, "Commune"),
  All(4, "All");

  public String name;
  public int code;

  LocationType(int code, String name) {
    this.code = code;
    this.name = name;
  }
}

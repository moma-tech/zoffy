package top.moma.zoffy.report.logic.support;

public enum Providers {
  ONELINK_OUTFLOW("Onelink-outflow"),
  ONELINK_INFLOW("Onelink-inflow"),
  UBPS("UBPS"),
  OPS("OPS"),
  JAZZCASH("Jazzcash"),
  OFFLINE("Offline"),
  UBPS_OPS("ubps&ops"),
  ;

  private final String name;

  Providers(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}

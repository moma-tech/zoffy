package top.moma.zoffy.fund.constants;

/**
 * FundTransferOrderTransStatusEnum
 *
 * <p>交易/调拨状态;交易系统枚举，0:调拨处理中;1:调拨失败;2:调拨成功
 *
 * @version 1.0
 * @author Created by ivan at 15:47.
 */
public enum FundTransferOrderTransStatusEnum {
  PENDING(0, "调拨处理中"),
  FAILED(1, "调拨失败"),
  SUCCESSFUL(2, "调拨成功"),
  ;

  private final int transStatus;
  private final String transStatusDesc;

  FundTransferOrderTransStatusEnum(int transStatus, String transStatusDesc) {
    this.transStatus = transStatus;
    this.transStatusDesc = transStatusDesc;
  }

  public int transStatus() {
    return transStatus;
  }

  public String transStatusDesc() {
    return transStatusDesc;
  }
}

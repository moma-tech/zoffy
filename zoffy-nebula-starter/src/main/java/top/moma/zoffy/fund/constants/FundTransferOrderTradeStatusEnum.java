package top.moma.zoffy.fund.constants;

/**
 * FundTransferOrderTradeStatusEnum
 *
 * <p>调拨单状态;业务枚举（页面展现）
 *
 * @version 1.0
 * @author Created by ivan at 15:49.
 */
public enum FundTransferOrderTradeStatusEnum {
  CREATED(1, "订单创建"),
  APPROVAL_PENDING(2, "待审核"),
  APPROVAL_PASS(3, "审核通过"),
  APPROVAL_REJECTED(4, "审核驳回"),
  TRANSACTION_SUCCEED(5, "订单成功"),
  TRANSACTION_FAILED(6, "订单失败"),
  TRANSACTION_PENDING(7, "订单处理中"),
  ;

  private final int tradeStatus;
  private final String tradeStatusDesc;

  FundTransferOrderTradeStatusEnum(int tradeStatus, String tradeStatusDesc) {
    this.tradeStatus = tradeStatus;
    this.tradeStatusDesc = tradeStatusDesc;
  }

  public int tradeStatus() {
    return tradeStatus;
  }

  public String tradeStatusDesc() {
    return tradeStatusDesc;
  }
}

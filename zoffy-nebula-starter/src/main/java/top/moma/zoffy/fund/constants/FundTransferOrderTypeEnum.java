package top.moma.zoffy.fund.constants;

/**
 * FundTransferOrderTypeEnum
 *
 * <p>资金类型/调拨场景
 *
 * @version 1.0
 * @author Created by ivan at 15:47.
 */
public enum FundTransferOrderTypeEnum {
  O_PAYMENT(1, "0payment"),
  O_REPAYMENT(2, "0repayment"),
  OW_INVESTMENT(3, "0-investment"),
  OW_INVESTMENT_RETURN(4, "0-investment return"),
  OW_PAYMENT(5, "0-payment"),
  OW_REPAYMENT(6, "0-repayment"),
  COLLECT_FUND(7, "Collect Fund"),
  ;

  private final int fundType;
  private final String fundTypeDesc;

  FundTransferOrderTypeEnum(int fundType, String fundTypeDesc) {
    this.fundType = fundType;
    this.fundTypeDesc = fundTypeDesc;
  }

  public int fundType() {
    return fundType;
  }

  public String fundTypeDesc() {
    return fundTypeDesc;
  }
}

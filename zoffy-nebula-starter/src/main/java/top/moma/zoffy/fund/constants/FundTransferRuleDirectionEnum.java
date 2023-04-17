package top.moma.zoffy.fund.constants;

/**
 * FundTransferRuleDirectionEnum
 *
 * <p>调拨场景规则 - 出入金方向(S/R);出金Sender，入金Recipient
 *
 * @version 1.0
 * @author Created by ivan at 16:34.
 */
public enum FundTransferRuleDirectionEnum {
  SENDER("S", "出金"),
  RECIPIENT("R", "入金"),
  ;

  private final String fundDirection;
  private final String fundDirectionDesc;

  FundTransferRuleDirectionEnum(String fundDirection, String fundDirectionDesc) {
    this.fundDirection = fundDirection;
    this.fundDirectionDesc = fundDirectionDesc;
  }

  public String fundDirection() {
    return fundDirection;
  }

  public String fundDirectionDesc() {
    return fundDirectionDesc;
  }
}

package top.moma.zoffy.fund.constants;

/**
 * FundTransferOrderTransTypeEnum
 *
 * <p>交易类型;aa/ac/rm
 *
 * @version 1.0
 * @author Created by ivan at 15:47.
 */
public enum FundTransferOrderTransTypeEnum {
  AA("aa", "AA"),
  AC("ac", "AC"),
  RM("rm", "RM"),
  ;

  private final String transType;
  private final String transTypeDesc;

  FundTransferOrderTransTypeEnum(String transType, String transTypeDesc) {
    this.transType = transType;
    this.transTypeDesc = transTypeDesc;
  }

  public String transType() {
    return transType;
  }

  public String transTypeDesc() {
    return transTypeDesc;
  }
}

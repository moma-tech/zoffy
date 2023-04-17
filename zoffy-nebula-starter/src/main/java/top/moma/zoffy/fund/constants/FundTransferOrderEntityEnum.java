package top.moma.zoffy.fund.constants;

/**
 * FundTransferOrderEntityEnum
 *
 * <p>调拨主体;业务枚举（关联场景规则表）
 *
 * @version 1.0
 * @author Created by ivan at 15:48.
 */
public enum FundTransferOrderEntityEnum {
  BR("br", "BR"),
  OPAY("other", "OTHER"),
  ;

  private final String fundEntity;
  private final String fundEntityDesc;

  FundTransferOrderEntityEnum(String fundEntity, String fundEntityDesc) {
    this.fundEntity = fundEntity;
    this.fundEntityDesc = fundEntityDesc;
  }

  public String fundEntity() {
    return fundEntity;
  }

  public String fundEntityDesc() {
    return fundEntityDesc;
  }
}

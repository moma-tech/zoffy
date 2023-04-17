package top.moma.zoffy.fund.constants;

/**
 * FundTransferAccountTypeEnum
 *
 * <p>账户类型
 *
 * @version 1.0
 * @author Created by ivan at 15:47.
 */
public enum FundTransferAccountTypeEnum {
  OPAY("system", "System"),
  BANK("bank", "Bank"),
  ;

  private final String accountType;
  private final String accountTypeDesc;

  FundTransferAccountTypeEnum(String accountType, String accountTypeDesc) {
    this.accountType = accountType;
    this.accountTypeDesc = accountTypeDesc;
  }

  public String accountType() {
    return accountType;
  }

  public String accountTypeDesc() {
    return accountTypeDesc;
  }
}

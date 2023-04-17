package top.moma.zoffy.fund.constants;

/**
 * FundTransferOrderApprovalTypeEnum
 *
 * <p>审核类型;审核类型（关联审核状态），默认0，0:人工审核；1:自动通过
 *
 * @version 1.0
 * @author Created by ivan at 15:48.
 */
public enum FundTransferOrderApprovalTypeEnum {
  MANUAL(0, "人工审核"),
  AUTOMATIC(1, "自动通过"),
  ;

  private final int approvalType;
  private final String approvalTypeDesc;

  FundTransferOrderApprovalTypeEnum(int approvalType, String approvalTypeDesc) {
    this.approvalType = approvalType;
    this.approvalTypeDesc = approvalTypeDesc;
  }

  public int approvalType() {
    return approvalType;
  }

  public String approvalTypeDesc() {
    return approvalTypeDesc;
  }
}

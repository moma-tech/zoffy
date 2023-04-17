package top.moma.zoffy.fund.constants;

/**
 * FundTransferOrderApprovalStatusEnum
 *
 * <p>审核状态;业务枚举，默认0，0:待审批;1:审批通过;2:审批拒绝;
 *
 * @version 1.0
 * @author Created by ivan at 15:48.
 */
public enum FundTransferOrderApprovalStatusEnum {
  APPROVAL_PENDING(0, "待审核"),
  APPROVAL_PASS(1, "审核通过"),
  APPROVAL_REJECTED(2, "审核驳回"),
  ;

  private final int approvalStatus;
  private final String approvalStatusDesc;

  FundTransferOrderApprovalStatusEnum(int approvalStatus, String approvalStatusDesc) {
    this.approvalStatus = approvalStatus;
    this.approvalStatusDesc = approvalStatusDesc;
  }

  public int approvalStatus() {
    return approvalStatus;
  }

  public String approvalStatusDesc() {
    return approvalStatusDesc;
  }
}

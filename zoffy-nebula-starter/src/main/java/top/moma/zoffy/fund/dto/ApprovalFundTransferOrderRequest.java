package top.moma.zoffy.fund.dto;

import lombok.Data;

@Data
public class ApprovalFundTransferOrderRequest implements java.io.Serializable {
  /** 调拨单id;调拨单id，支持多个，逗号分隔 */
  private String fundOrderId;
  /** 审核状态，1:通过，2:拒绝 */
  private Integer approval;
  /** 拒绝原因 */
  private String denialReason;
  /** 审核人id */
  private Long approvalUser;
  /** 审核人名称 */
  private String approvalUserName;
}

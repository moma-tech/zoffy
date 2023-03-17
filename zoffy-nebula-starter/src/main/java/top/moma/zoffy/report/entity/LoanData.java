package top.moma.zoffy.report.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/** loan_data/6 @TableName loan_data */
@TableName(value = "pk_gl_ods_loan_data_month")
@Data
public class LoanData extends CalculateDto implements Serializable {
  /** 主键 */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 月份 */
  @TableField(value = "data_month")
  private String dataMonth;

  /** 第三方平台 */
  @TableField(value = "third_party_platform")
  private String thirdPartyPlatform;

  /** 放款本金 */
  @TableField(value = "principal")
  private String principal;

  /** 应收砍头息-服务费 */
  @TableField(value = "pre_service_fee")
  private String preServiceFee;

  /** 应收砍头息-gst */
  @TableField(value = "pre_gst")
  private String preGst;

  /** 应收服务费 */
  @TableField(value = "service_fee")
  private String serviceFee;

  /** 应收gst */
  @TableField(value = "gst")
  private String gst;

  /** 应收利息 */
  @TableField(value = "interest")
  private String interest;

  /** 应收罚息 */
  @TableField(value = "penalty")
  private String penalty;

  /** 实收本金 */
  @TableField(value = "principal_paid")
  private String principalPaid;

  /** 实收砍头息-服务费 */
  @TableField(value = "pre_service_fee_paid")
  private String preServiceFeePaid;

  /** 实收砍头息-gst */
  @TableField(value = "pre_gst_paid")
  private String preGstPaid;

  /** 实收服务费 */
  @TableField(value = "service_fee_paid")
  private String serviceFeePaid;

  /** 实收gst */
  @TableField(value = "gst_paid")
  private String gstPaid;

  /** 实收利息 */
  @TableField(value = "interest_paid")
  private String interestPaid;

  /** 实收罚息 */
  @TableField(value = "penalty_paid")
  private String penaltyPaid;

  /** 溢缴款 */
  @TableField(value = "overpaid_amount")
  private String overpaidAmount;

  /** 减免本金 */
  @TableField(value = "principal_reduced")
  private String principalReduced;

  /** 减免服务费 */
  @TableField(value = "service_fee_reduced")
  private String serviceFeeReduced;

  /** 减免gst */
  @TableField(value = "gst_reduced")
  private String gstReduced;

  /** 减免利息 */
  @TableField(value = "interest_reduced")
  private String interestReduced;

  /** 减免罚息 */
  @TableField(value = "penalty_reduced")
  private String penaltyReduced;

  /** 创建时间 */
  @TableField(value = "create_time")
  private LocalDateTime createTime;

  /** 更新时间 */
  @TableField(value = "update_time")
  private LocalDateTime updateTime;

  @TableField(exist = false)
  private static final long serialVersionUID = 1L;
}

package top.moma.zoffy.report.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/** cashinflow/7 @TableName cash_in_flow */
@TableName(value = "pk_gl_ods_cash_in_flow_month")
@Data
public class CashInFlow extends CalculateDto implements Serializable {
  /** 主键 */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 还款月份 */
  @TableField(value = "paid_month")
  private String paidMonth;

  /** 第三方平台 */
  @TableField(value = "third_party_platform")
  private String thirdPartyPlatform;

  /** 实收总金额 */
  @TableField(value = "total_amount_paid")
  private String totalAmountPaid;

  /** 实收金款 */
  @TableField(value = "amount_paid")
  private String amountPaid;

  /** 未放款还款余额 */
  @TableField(value = "unloan_amount_paid")
  private String unloanAmountPaid;

  /** 溢缴款 */
  @TableField(value = "overpaid_amount")
  private String overpaidAmount;

  /** 创建时间 */
  @TableField(value = "create_time")
  private LocalDateTime createTime;

  /** 更新时间 */
  @TableField(value = "update_time")
  private LocalDateTime updateTime;

  @TableField(exist = false)
  private static final long serialVersionUID = 1L;

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    CashInFlow other = (CashInFlow) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getPaidMonth() == null
            ? other.getPaidMonth() == null
            : this.getPaidMonth().equals(other.getPaidMonth()))
        && (this.getThirdPartyPlatform() == null
            ? other.getThirdPartyPlatform() == null
            : this.getThirdPartyPlatform().equals(other.getThirdPartyPlatform()))
        && (this.getTotalAmountPaid() == null
            ? other.getTotalAmountPaid() == null
            : this.getTotalAmountPaid().equals(other.getTotalAmountPaid()))
        && (this.getAmountPaid() == null
            ? other.getAmountPaid() == null
            : this.getAmountPaid().equals(other.getAmountPaid()))
        && (this.getUnloanAmountPaid() == null
            ? other.getUnloanAmountPaid() == null
            : this.getUnloanAmountPaid().equals(other.getUnloanAmountPaid()))
        && (this.getOverpaidAmount() == null
            ? other.getOverpaidAmount() == null
            : this.getOverpaidAmount().equals(other.getOverpaidAmount()))
        && (this.getCreateTime() == null
            ? other.getCreateTime() == null
            : this.getCreateTime().equals(other.getCreateTime()))
        && (this.getUpdateTime() == null
            ? other.getUpdateTime() == null
            : this.getUpdateTime().equals(other.getUpdateTime()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getPaidMonth() == null) ? 0 : getPaidMonth().hashCode());
    result =
        prime * result
            + ((getThirdPartyPlatform() == null) ? 0 : getThirdPartyPlatform().hashCode());
    result =
        prime * result + ((getTotalAmountPaid() == null) ? 0 : getTotalAmountPaid().hashCode());
    result = prime * result + ((getAmountPaid() == null) ? 0 : getAmountPaid().hashCode());
    result =
        prime * result + ((getUnloanAmountPaid() == null) ? 0 : getUnloanAmountPaid().hashCode());
    result = prime * result + ((getOverpaidAmount() == null) ? 0 : getOverpaidAmount().hashCode());
    result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
    result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", paidMonth=").append(paidMonth);
    sb.append(", thirdPartyPlatform=").append(thirdPartyPlatform);
    sb.append(", totalAmountPaid=").append(totalAmountPaid);
    sb.append(", amountPaid=").append(amountPaid);
    sb.append(", failedOrderPaid=").append(unloanAmountPaid);
    sb.append(", overpaidAmount=").append(overpaidAmount);
    sb.append(", createTime=").append(createTime);
    sb.append(", updateTime=").append(updateTime);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}

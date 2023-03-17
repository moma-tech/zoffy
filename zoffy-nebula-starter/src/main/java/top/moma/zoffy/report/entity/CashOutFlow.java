package top.moma.zoffy.report.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/** cashoutflow/8 @TableName cash_out_flow */
@TableName(value = "pk_gl_ods_cash_out_flow_month")
@Data
public class CashOutFlow extends CalculateDto implements Serializable {
  /** 主键 */
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  /** 还款月份 */
  @TableField(value = "loan_month")
  private String loanMonth;

  /** 第三方平台 */
  @TableField(value = "third_party_platform")
  private String thirdPartyPlatform;

  /** 放款本金 */
  @TableField(value = "principal")
  private String principal;

  /** 放款未生成账单金额 */
  @TableField(value = "unbilled_principal")
  private String unbilledPrincipal;

  /** 创建时间 */
  @TableField(value = "create_time")
  private LocalDateTime createTime;

  /** 更新时间 */
  @TableField(value = "update_time")
  private LocalDateTime updateTime;

  @TableField(exist = false)
  private static final long serialVersionUID = 1L;
}

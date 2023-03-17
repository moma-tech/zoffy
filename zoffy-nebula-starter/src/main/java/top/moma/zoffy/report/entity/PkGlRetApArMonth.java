package top.moma.zoffy.report.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * PkGlRetApArMonth
 *
 * <p>结果表-应收应付ApAr-按月;
 *
 * @version 1.0
 * @author Created by ivan at 18:22.
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("pk_gl_ret_ap_ar_month")
public class PkGlRetApArMonth extends CalculateDto implements Serializable {
  /** 数据id;唯一标识 */
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  /** 周期;统计周期，yyyy-MM */
  private String settlePeriod;
  /** 渠道名称;三方通道名称 */
  private String thirdPartyPlatform;
  /** 银行结算流入 */
  private String settleAmountInflow;
  /** 银行结算流出 */
  private String settleAmountOutflow;
  /** 其它流出（借） */
  private String otherDebit;
  /** 其它流入（贷） */
  private String otherCredit;
  /** 创建时间 */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;
  /** 更新时间 */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;
}

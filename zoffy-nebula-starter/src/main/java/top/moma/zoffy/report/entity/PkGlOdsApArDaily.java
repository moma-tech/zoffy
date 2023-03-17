package top.moma.zoffy.report.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * PkGlOdsApArDaily
 *
 * <p>基础表-应收应付ApAr-按天
 *
 * @version 1.0
 * @author Created by ivan at 18:23.
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@TableName("pk_gl_ods_ap_ar_daily")
public class PkGlOdsApArDaily extends CalculateDto implements java.io.Serializable {
  /** 数据id;唯一标识 */
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  /** 创建时间;创建时间，应用服务器时间 */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;
  /** 更新时间;更新时间，同创建时间 */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;
  /** 结算日期 */
  private String settleDate;
  /** 渠道名称 */
  private String provider;
  /** 银行结算流入 */
  private Long settleAmountInflow;
  /** 银行结算流出 */
  private Long settleAmountOutflow;
  /** 其它流出（借） */
  private Long otherDebit;
  /** 其它流入（贷） */
  private Long otherCredit;
}

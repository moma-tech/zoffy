package top.moma.zoffy.report.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * PkGlEntries
 *
 * <p>巴基斯坦凭证记录表
 *
 * @version 1.0
 * @author Created by ivan at 14:15.
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@TableName("pk_gl_entries")
public class PkGlEntries implements Serializable {
  /** 记录id;唯一标识 */
  @TableId(type = IdType.ASSIGN_ID)
  private Long entryId;
  /** 周期;计算的凭证周期 */
  private String entryPeriod;
  /** 所属凭证科目id;关联巴基斯坦凭证科目模版表 */
  private Long accountCodeId;
  /** 借方金额;借方金额计算结果 */
  private String dr;
  /** 贷方金额;贷方金额计算结果 */
  private String cr;
  /** 结余金额;借方金额 - 贷方金额，可为负 */
  private String balance;
  /** 排序号;展示用排序号 */
  private Integer sort;
  /** 数据有效性;0-无效，1-有效 */
  private Integer enable;
  /** 创建时间;创建时间，应用服务器时间 */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;
  /** 更新时间;更新时间，同创建时间 */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;
  /** 创建用户;创建用户，默认Scheduler */
  @TableField(fill = FieldFill.INSERT)
  private String createUser;
  /** 更新用户;更新用户，默认Scheduler */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private String updateUser;
}

package top.moma.zoffy.report.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * PkGlEntriesAccountCode
 *
 * <p>巴基斯坦凭证科目计算模版表;
 *
 * @version 1.0
 * @author Created by ivan at 14:15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pk_gl_entries_account_code")
public class PkGlEntriesAccountCode implements Serializable {
  /** 数据id;数据全局id */
  @TableId(type = IdType.ASSIGN_ID)
  private Long acId;
  /** 科目编码+排序;同版本下科目编码唯一，可用于排序 */
  private Integer code;
  /** 显示编码;展现用科目编码 */
  private String accountCode;
  /** 显示描述;展现用科目名称 */
  private String accountName;
  /** 显示分类;展现用科目分类 */
  private String category;
  /** 借方/贷方;借贷标识，0-借方，1-贷方 */
  private Integer drcr;
  /** 数据来源;数据源包括：data/cashin/cashout/apar/entry */
  private String source;
  /** 计算方式;公式包括：ACC_BY_PERIOD/ACC_BY_PLATFORM/ENTRY_MINUS/ENTRY_SUM/ENTRY_SUM_MINUS/MANUAL */
  private String calFunction;
  /** 计算参数;计算使用参数的数据源字段名称 */
  private String calParams;
  /** 计算优先级;计算顺序，自然数从小到大，99-基于其他科目计算 */
  private Integer calLevel;
  /** 版本;支持历史数据展示 */
  private String version;
  /** 数据有效性;0-无效，1-有效 */
  private Integer enable;
  /** 创建时间;创建时间，应用服务器时间 */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;
  /** 更新时间;更新时间，同创建时间 */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;
}

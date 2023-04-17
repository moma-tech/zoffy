package top.moma.zoffy.fund.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 调拨场景规则表
 * @author weiqiangguo
 * @date 2023-04-07
 */
@TableName
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundTransferRule implements Serializable {

  private static final long serialVersionUID = -8494380798906214955L;

  @TableId(type = IdType.AUTO)
  /** 规则id */
  private Long ruleId;

  /** 调拨主体;业务枚举（关联场景规则表） */
  private String fundEntity;

  /** 调拨场景;业务枚举 */
  private Integer fundType;

  /** 账户id;账户信息表id */
  private Long accountId;

  /** 出入金方向(s/r) */
  private String fundDirection;

  /** 转账类型(ac/aa/rm) */
  private String transactionType;

  /** 是否启用标识;默认1，0: 停用;1:启用 */
  private Integer activeStatus;
}

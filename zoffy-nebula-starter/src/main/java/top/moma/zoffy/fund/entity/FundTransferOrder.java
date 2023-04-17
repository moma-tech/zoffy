package top.moma.zoffy.fund.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guoweiqiang
 * @description 资金调拨单主表
 * @date 2023-04-07
 */
@TableName
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundTransferOrder implements Serializable {

  private static final long serialVersionUID = -5606791294553578026L;

  @TableId(type = IdType.AUTO)
  /** 调拨单id;唯一主键id */
  private Long orderId;

  /** 调拨单据号;预留，存在同一场景多条付款资金调拨流水 */
  private String orderSeq;

  /** 调拨主体;业务枚举（关联场景规则表） */
  private String fundEntity;

  /** 资金类型/调拨场景;业务枚举（关联场景规则表） */
  private Integer fundType;

  /** 资金用途;业务自行定义 */
  private String fundDescription;

  /** 调拨单状态;业务枚举（页面展现） */
  private Integer tradeStatus;

  /** 交易类型;aa/ac/rm */
  private String transactionType;

  /** 交易id(业务方) */
  private String transactionId;

  /** 交易流水号(交易方) */
  private String transactionOrder;

  /** 交易金额;金额，无小数位，默认0 */
  private Long transactionAmount;

  /** 交易币种;默认ngn */
  private String transactionCurrency;

  /** 交易国家;默认ng */
  private String transactionCountry;

  /** 出金账户名称 */
  private String senderAccountName;

  /** 出金账户账号 */
  private String senderAccountCode;

  /** 出金账户银行名称 */
  private String senderAccountBankName;

  /** 出金账户银行编码;交易系统银行编码 */
  private String senderAccountBankCode;

  /** 入金账户名称 */
  private String recipientAccountName;

  /** 入金账户账号 */
  private String recipientAccountCode;

  /** 入金账户银行名称 */
  private String recipientAccountBankName;

  /** 入金账户银行编码;交易系统银行编码 */
  private String recipientAccountBankCode;

  /** 交易/调拨状态;交易系统枚举，0:调拨处理中;1:调拨失败;2:调拨成功 */
  private Integer transactionStatus;

  /** 失败原因 */
  private String transactionFailureReason;

  /** 调拨单创建时间 */
  private LocalDateTime createTime;

  /** 调拨单更新时间 */
  private LocalDateTime updateTime;

  /** 调拨单完成时间 */
  private LocalDateTime completeTime;

  /** 创建人;登陆操作员id */
  private Long createUser;

  /** 创建人名称;登陆操作员名称 */
  private String createUserName;

  /** 审核类型;审核类型（关联审核状态），默认0，0:人工审核；1:自动通过 */
  private Integer approvalType;

  /** 审核人;登陆操作员id */
  private Long approvalUser;

  /** 审核人名称;登陆操作员名称 */
  private String approvalUserName;

  /** 审核状态;业务枚举，默认1，0:待审批;1:审批通过;2:审批拒绝; */
  private Integer approvalStatus;

  /** 审核完成时间 */
  private LocalDateTime approvalCompleteTime;

  /** 审核拒绝原因 */
  private String approvalDenialReason;

  /** 备注;预留 */
  private String remarks;
}

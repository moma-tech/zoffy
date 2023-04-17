package top.moma.zoffy.fund.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @description 账户信息表
 * @author weiqiangguo
 * @date 2023-04-07
 */
@Data
@TableName
public class FundTransferAccountInfo implements Serializable {

  private static final long serialVersionUID = -6037962579984758530L;

  @TableId(type = IdType.AUTO)
  /** 账户id */
  private Long accountId;

  /** 账户类型（system/bank） */
  private String accountType;

  /** 账户名称 */
  private String accountName;

  /** 账户账号 */
  private String accountCode;

  /** 账户银行名称 */
  private String accountBankName;

  /** 账户银行代码;交易系统银行编码 */
  private String accountBankCode;

  /** 账户状态，1:有效，0:有效 */
  private Integer accountStatus;
}

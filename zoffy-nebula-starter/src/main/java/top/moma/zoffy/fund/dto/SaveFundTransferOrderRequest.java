package top.moma.zoffy.fund.dto;

import lombok.Data;

@Data
public class SaveFundTransferOrderRequest implements java.io.Serializable {
  /** 调拨主体 */
  private String fundEntity;
  /** 调拨场景 */
  private Integer fundType;
  /** " 调拨金额;金额，无小数位，默认0" */
  private String amount;
  /** 发送方账户id */
  private String senderAccountId;
  /** 接收放账户id */
  private String recipientAccountId;
  /** 调拨资金用途 */
  private String fundDescription;
  /** 调拨单id，空时创建，非空时更新 */
  private String fundOrderId;
  /** 创建人id */
  private Long createUser;
  /** 创建人名称 */
  private String createUserName;
}

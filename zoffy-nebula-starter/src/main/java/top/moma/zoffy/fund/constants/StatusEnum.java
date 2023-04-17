package top.moma.zoffy.fund.constants;

import lombok.Getter;

/**
 * @author weiqiangguo
 */
@Getter
public enum StatusEnum {
  VALID(1, "有效"),
  INVALID(0, "无效");

  private int code;
  private String desc;

  StatusEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}

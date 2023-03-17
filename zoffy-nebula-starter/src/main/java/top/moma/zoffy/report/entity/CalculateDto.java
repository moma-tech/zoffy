package top.moma.zoffy.report.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CalculateDto {
  /** 求和属性 */
  @TableField(exist = false)
  @JsonIgnore
  private String sumAll;

  public String getSumAll() {
    return sumAll;
  }

  public void setSumAll(String sumAll) {
    this.sumAll = sumAll;
  }
}

package top.moma.zoffy.report.logic.constants;

import java.math.BigDecimal;

/**
 * CalFunctionInterface
 *
 * <p>计算接口定义
 *
 * @version 1.0
 * @author Created by ivan at 15:32.
 */
public interface CalFunctionInterface {
  /**
   * 计算 calculate
   *
   * @param source source 数据源
   * @param period period 计算周期
   * @param params params 计算参数
   * @return java.math.BigDecimal
   * @author Created by ivan
   * @since 2023/3/10 15:32
   */
  BigDecimal calculate(String source, String period, String... params);
}

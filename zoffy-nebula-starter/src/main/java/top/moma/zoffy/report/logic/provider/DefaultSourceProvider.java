package top.moma.zoffy.report.logic.provider;

import java.math.BigDecimal;

/**
 * DefaultSourceProvider
 *
 * <p>未知数据源
 *
 * @version 1.0
 * @author Created by ivan at 17:19.
 */
public class DefaultSourceProvider implements SourceProvider {

  @Override
  public BigDecimal accumulateByPeriod(String period, String... params) {
    return BigDecimal.ZERO;
  }

  @Override
  public BigDecimal accumulateByPlatform(String period, String... params) {
    return BigDecimal.ZERO;
  }
}

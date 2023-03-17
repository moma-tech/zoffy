package top.moma.zoffy.report.logic.provider;

import top.moma.zoffy.helper.ContextHelper;
import top.moma.zoffy.report.logic.constants.AccountCodeConstants;
import top.moma.zoffy.report.logic.constants.EntryBizConstants;

import java.math.BigDecimal;

/**
 * SourceProvider
 *
 * <p>数据源计算代理
 *
 * @version 1.0
 * @author Created by ivan at 14:27.
 */
public interface SourceProvider {

  /**
   * 代理路由 getProvider
   *
   * @param source 数据源
   * @return com.opay.fund.gl.report.provider.logic.impl.pk.entry.provider.SourceProvider
   * @author Created by ivan
   * @since 2023/3/10 17:25
   */
  static SourceProvider getProvider(String source) {
    SourceProvider sourceProvider;
    switch (source) {
      case AccountCodeConstants.SOURCE_TYPE_LOAN_DATA:
        sourceProvider = ContextHelper.getBeanByName(EntryBizConstants.SOURCE_PROVIDER_LOAN_DATA);
        break;
      case AccountCodeConstants.SOURCE_TYPE_CASH_IN:
        sourceProvider = ContextHelper.getBeanByName(EntryBizConstants.SOURCE_PROVIDER_CASH_IN);
        break;
      case AccountCodeConstants.SOURCE_TYPE_CASH_OUT:
        sourceProvider = ContextHelper.getBeanByName(EntryBizConstants.SOURCE_PROVIDER_CASH_OUT);
        break;
      case AccountCodeConstants.SOURCE_TYPE_AP_AR:
        sourceProvider = ContextHelper.getBeanByName(EntryBizConstants.SOURCE_PROVIDER_AP_AR);
        break;
      case AccountCodeConstants.SOURCE_TYPE_ENTRY:
        sourceProvider = ContextHelper.getBeanByName(EntryBizConstants.SOURCE_PROVIDER_ENTRY);
        break;
      case AccountCodeConstants.SOURCE_TYPE_MANUAL:
        // 不自动计算
      default:
        sourceProvider = new DefaultSourceProvider();
    }
    return sourceProvider;
  }

  /**
   * 按周期累加全渠道数据 accumulateByPeriod
   *
   * @param period 结束周期
   * @param params 累加参数
   * @return java.math.BigDecimal 累加结果
   * @author Created by ivan
   * @since 2023/3/10 15:25
   */
  BigDecimal accumulateByPeriod(String period, String... params);

  /**
   * 按周期累加指定渠道数据 accumulateByPeriod
   *
   * @param period 结束周期
   * @param params 累加参数，第一个参数为指定渠道
   * @return java.math.BigDecimal 累加结果
   * @author Created by ivan
   * @since 2023/3/10 15:25
   */
  BigDecimal accumulateByPlatform(String period, String... params);

  /**
   * 周期内求和 sum
   *
   * @param period 计算周期
   * @param params 求和科目代码
   * @return java.math.BigDecimal 计算结果
   * @author Created by ivan
   * @since 2023/3/10 15:26
   */
  default BigDecimal sum(String period, String... params) {
    return BigDecimal.ZERO;
  }

  /**
   * 周期内减法 minus
   *
   * @param period 计算周期
   * @param params 减法科目代码，顺序减
   * @return java.math.BigDecimal 计算结果
   * @author Created by ivan
   * @since 2023/3/10 15:27
   */
  default BigDecimal minus(String period, String... params) {
    return BigDecimal.ZERO;
  }

  /**
   * 周期内先加后减 sumMinus
   *
   * @param period 计算周期
   * @param params 参与计算科目代码，第一位为求和参数数量
   * @return java.math.BigDecimal 计算结果
   * @author Created by ivan
   * @since 2023/3/10 15:28
   */
  default BigDecimal sumMinus(String period, String... params) {
    return BigDecimal.ZERO;
  }

  /**
   * 手动科目计算 manual
   *
   * @param period 计算周期
   * @param params 计算参数
   * @return java.math.BigDecimal 计算结果
   * @author Created by ivan
   * @since 2023/3/10 15:29
   */
  default BigDecimal manual(String period, String... params) {
    return BigDecimal.ZERO;
  }
}

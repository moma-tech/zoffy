package top.moma.zoffy.report.logic.provider;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import top.moma.zoffy.report.entity.CashOutFlow;
import top.moma.zoffy.report.logic.constants.EntryBizConstants;
import top.moma.zoffy.report.logic.constants.EntryColConstants;
import top.moma.zoffy.report.logic.support.CalculationHelper;
import top.moma.zoffy.report.service.CashOutFlowService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
/**
 * CashOutSourceProvider
 *
 * <p>Cash Out Flow 数据计算
 *
 * @version 1.0
 * @author Created by ivan at 17:35.
 */
@Component(EntryBizConstants.SOURCE_PROVIDER_CASH_OUT)
public class CashOutSourceProvider implements SourceProvider {

  final CashOutFlowService cashOutFlowService;

  public CashOutSourceProvider(CashOutFlowService cashOutFlowService) {
    this.cashOutFlowService = cashOutFlowService;
  }

  @Override
  public BigDecimal accumulateByPeriod(String period, String... params) {
    BigDecimal result = BigDecimal.ZERO;
    if (Objects.nonNull(params) && params.length > 1) {
      CashOutFlow cashOutFlow =
          cashOutFlowService.getOne(
              CalculationHelper.sumQueryWrapper(
                  null,
                  CalculationHelper.periodFormatter(
                      EntryColConstants.PERIOD_FORMAT_CASH_OUT, period),
                  Arrays.asList(params),
                  EntryColConstants.PLATFORM_COL_CASH_OUT,
                  EntryColConstants.PERIOD_COL_CASH_OUT));
      if (Objects.nonNull(cashOutFlow)) {
        result = new BigDecimal(cashOutFlow.getSumAll());
      }
    }
    return result;
  }

  @Override
  public BigDecimal accumulateByPlatform(String period, String... params) {
    BigDecimal result = BigDecimal.ZERO;
    if (Objects.nonNull(params) && params.length > 1) {
      String platform = params[0];
      @SuppressWarnings("unchecked")
      List<String> cols =
          (List<String>) CollectionUtils.arrayToList(params).subList(1, params.length);
      CashOutFlow cashOutFlow =
          cashOutFlowService.getOne(
              CalculationHelper.sumQueryWrapper(
                  platform,
                  CalculationHelper.periodFormatter(
                      EntryColConstants.PERIOD_FORMAT_CASH_OUT, period),
                  cols,
                  EntryColConstants.PLATFORM_COL_CASH_OUT,
                  EntryColConstants.PERIOD_COL_CASH_OUT));
      if (Objects.nonNull(cashOutFlow)) {
        result = new BigDecimal(cashOutFlow.getSumAll());
      }
    } else {
      result = accumulateByPeriod(period, params);
    }
    return result;
  }
}

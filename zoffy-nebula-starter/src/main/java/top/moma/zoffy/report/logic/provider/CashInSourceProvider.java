package top.moma.zoffy.report.logic.provider;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import top.moma.zoffy.report.entity.CashInFlow;
import top.moma.zoffy.report.logic.constants.EntryBizConstants;
import top.moma.zoffy.report.logic.constants.EntryColConstants;
import top.moma.zoffy.report.logic.support.CalculationHelper;
import top.moma.zoffy.report.service.CashInFlowService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * CashInSourceProvider
 *
 * <p>Cash In Flow 数据计算
 *
 * @version 1.0
 * @author Created by ivan at 17:33.
 */
@Component(EntryBizConstants.SOURCE_PROVIDER_CASH_IN)
public class CashInSourceProvider implements SourceProvider {

  final CashInFlowService cashInFlowService;

  public CashInSourceProvider(CashInFlowService cashInFlowService) {
    this.cashInFlowService = cashInFlowService;
  }

  @Override
  public BigDecimal accumulateByPeriod(String period, String... params) {
    BigDecimal result = BigDecimal.ZERO;
    if (Objects.nonNull(params) && params.length > 0) {
      CashInFlow cashInFlow =
          cashInFlowService.getOne(
              CalculationHelper.sumQueryWrapper(
                  null,
                  CalculationHelper.periodFormatter(
                      EntryColConstants.PERIOD_FORMAT_CASH_IN, period),
                  Arrays.asList(params),
                  EntryColConstants.PLATFORM_COL_CASH_IN,
                  EntryColConstants.PERIOD_COL_CASH_IN));
      if (Objects.nonNull(cashInFlow)) {
        result = new BigDecimal(cashInFlow.getSumAll());
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
      CashInFlow loanData =
          cashInFlowService.getOne(
              CalculationHelper.sumQueryWrapper(
                  platform,
                  CalculationHelper.periodFormatter(
                      EntryColConstants.PERIOD_FORMAT_CASH_IN, period),
                  cols,
                  EntryColConstants.PLATFORM_COL_CASH_IN,
                  EntryColConstants.PERIOD_COL_CASH_IN));
      if (Objects.nonNull(loanData)) {
        result = new BigDecimal(loanData.getSumAll());
      }
    } else {
      result = accumulateByPeriod(period, params);
    }
    return result;
  }
}

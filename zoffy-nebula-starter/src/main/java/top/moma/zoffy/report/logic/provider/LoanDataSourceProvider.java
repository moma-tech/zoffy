package top.moma.zoffy.report.logic.provider;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import top.moma.zoffy.report.entity.LoanData;
import top.moma.zoffy.report.logic.constants.EntryBizConstants;
import top.moma.zoffy.report.logic.constants.EntryColConstants;
import top.moma.zoffy.report.logic.support.CalculationHelper;
import top.moma.zoffy.report.service.LoanDataService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * LoanDataSourceProvider
 *
 * <p>Loan Data数据计算
 *
 * @version 1.0
 * @author Created by ivan at 16:38.
 */
@Component(EntryBizConstants.SOURCE_PROVIDER_LOAN_DATA)
public class LoanDataSourceProvider implements SourceProvider {

  final LoanDataService loanDataService;

  public LoanDataSourceProvider(LoanDataService loanDataService) {
    this.loanDataService = loanDataService;
  }

  @Override
  public BigDecimal accumulateByPeriod(String period, String... params) {
    BigDecimal result = BigDecimal.ZERO;
    if (Objects.nonNull(params) && params.length > 0) {
      LoanData loanData =
          loanDataService.getOne(
              CalculationHelper.sumQueryWrapper(
                  null,
                  CalculationHelper.periodFormatter(
                      EntryColConstants.PERIOD_FORMAT_LOAN_DATA, period),
                  Arrays.asList(params),
                  EntryColConstants.PLATFORM_COL_LOAN_DATA,
                  EntryColConstants.PERIOD_COL_LOAN_DATA));
      if (Objects.nonNull(loanData)) {
        result = new BigDecimal(loanData.getSumAll());
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
      LoanData loanData =
          loanDataService.getOne(
              CalculationHelper.sumQueryWrapper(
                  platform,
                  CalculationHelper.periodFormatter(
                      EntryColConstants.PERIOD_FORMAT_LOAN_DATA, period),
                  cols,
                  EntryColConstants.PLATFORM_COL_LOAN_DATA,
                  EntryColConstants.PERIOD_COL_LOAN_DATA));
      if (Objects.nonNull(loanData)) {
        result = new BigDecimal(loanData.getSumAll());
      }
    } else {
      result = accumulateByPeriod(period, params);
    }
    return result;
  }
}

package top.moma.zoffy.report.logic.provider;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import top.moma.zoffy.report.entity.PkGlRetApArMonth;
import top.moma.zoffy.report.logic.constants.EntryBizConstants;
import top.moma.zoffy.report.logic.constants.EntryColConstants;
import top.moma.zoffy.report.logic.support.CalculationHelper;
import top.moma.zoffy.report.service.PkGlRetApArMonthService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * ApArSourceProvider
 *
 * <p>Ap Ar月表数据计算
 *
 * @version 1.0
 * @author Created by ivan at 18:37.
 */
@Component(EntryBizConstants.SOURCE_PROVIDER_AP_AR)
public class ApArSourceProvider implements SourceProvider {

  final PkGlRetApArMonthService apArMonthService;

  public ApArSourceProvider(PkGlRetApArMonthService apArMonthService) {
    this.apArMonthService = apArMonthService;
  }

  @Override
  public BigDecimal accumulateByPeriod(String period, String... params) {
    BigDecimal result = BigDecimal.ZERO;
    if (Objects.nonNull(params) && params.length > 1) {
      PkGlRetApArMonth loanData =
          apArMonthService.getOne(
              CalculationHelper.sumQueryWrapper(
                  null,
                  CalculationHelper.periodFormatter(EntryColConstants.PERIOD_FORMAT_AP_AR, period),
                  Arrays.asList(params),
                  EntryColConstants.PLATFORM_COL_AP_AR,
                  EntryColConstants.PERIOD_COL_AP_AR));
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
      PkGlRetApArMonth loanData =
          apArMonthService.getOne(
              CalculationHelper.sumQueryWrapper(
                  platform,
                  CalculationHelper.periodFormatter(EntryColConstants.PERIOD_FORMAT_AP_AR, period),
                  cols,
                  EntryColConstants.PLATFORM_COL_AP_AR,
                  EntryColConstants.PERIOD_COL_AP_AR));
      if (Objects.nonNull(loanData)) {
        result = new BigDecimal(loanData.getSumAll());
      }
    } else {
      result = accumulateByPeriod(period, params);
    }
    return result;
  }
}

package top.moma.zoffy.report.logic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.moma.zoffy.report.entity.PkGlOdsApArDaily;
import top.moma.zoffy.report.entity.PkGlRetApArMonth;
import top.moma.zoffy.report.logic.support.Providers;
import top.moma.zoffy.report.service.PkGlOdsApArDailyService;
import top.moma.zoffy.report.service.PkGlRetApArMonthService;

import java.util.List;
import java.util.Locale;

@Service
public class ApArLogicImpl {
  @Autowired PkGlOdsApArDailyService dailyService;
  @Autowired PkGlRetApArMonthService monthService;

  public void calculateMonth(String provider, String settlePeriod) {
    PkGlRetApArMonth pkGlRetApArMonth = new PkGlRetApArMonth();
    List<PkGlOdsApArDaily> monthCalculation;
    LambdaQueryWrapper<PkGlOdsApArDaily> monthQueryWrapper =
        new LambdaQueryWrapper<PkGlOdsApArDaily>()
            .between(PkGlOdsApArDaily::getSettleDate, settlePeriod + "-01", settlePeriod + "-31");
    pkGlRetApArMonth.setSettlePeriod(settlePeriod);
    if (provider.equals(Providers.OPS.getName()) || provider.equals(Providers.UBPS.getName())) {
      monthQueryWrapper.and(
          i ->
              i.eq(PkGlOdsApArDaily::getProvider, Providers.OPS.getName().toLowerCase(Locale.ROOT))
                  .or()
                  .eq(
                      PkGlOdsApArDaily::getProvider,
                      Providers.UBPS.getName().toLowerCase(Locale.ROOT)));
      pkGlRetApArMonth.setThirdPartyPlatform(Providers.UBPS_OPS.getName().toLowerCase(Locale.ROOT));
    } else {
      monthQueryWrapper.eq(PkGlOdsApArDaily::getProvider, provider.toLowerCase(Locale.ROOT));
      pkGlRetApArMonth.setThirdPartyPlatform(provider.toLowerCase(Locale.ROOT));
    }
    monthCalculation = dailyService.list(monthQueryWrapper);
    PkGlOdsApArDaily daily =
        monthCalculation.stream()
            .reduce(
                PkGlOdsApArDaily.builder()
                    .settleAmountOutflow(0L)
                    .settleAmountInflow(0L)
                    .otherDebit(0L)
                    .otherCredit(0L)
                    .build(),
                this::combineDaily);
    pkGlRetApArMonth.setSettleAmountInflow(daily.getSettleAmountInflow() + "");
    pkGlRetApArMonth.setSettleAmountOutflow(daily.getSettleAmountOutflow() + "");
    pkGlRetApArMonth.setOtherDebit(daily.getOtherDebit() + "");
    pkGlRetApArMonth.setOtherCredit(daily.getOtherCredit() + "");
    monthService.saveOrUpdate(
        pkGlRetApArMonth,
        new LambdaUpdateWrapper<PkGlRetApArMonth>()
            .eq(PkGlRetApArMonth::getSettlePeriod, settlePeriod)
            .eq(PkGlRetApArMonth::getThirdPartyPlatform, provider));
  }
  /**
   * 加计算 combineDaily
   *
   * @param a 第一条
   * @param b 第二条
   * @return com.opay.fund.gl.report.provider.entity.pk.apar.PkGlOdsApArDaily
   * @author Created by ivan
   * @since 2023/3/13 15:58
   */
  private PkGlOdsApArDaily combineDaily(PkGlOdsApArDaily a, PkGlOdsApArDaily b) {
    a.setSettleAmountOutflow(a.getSettleAmountOutflow() + b.getSettleAmountOutflow());
    a.setSettleAmountInflow(a.getSettleAmountInflow() + b.getSettleAmountInflow());
    a.setOtherDebit(a.getOtherDebit() + b.getOtherDebit());
    a.setOtherCredit(a.getOtherCredit() + b.getOtherCredit());
    return a;
  }
}

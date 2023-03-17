package top.moma.zoffy.report.logic.constants;

import top.moma.zoffy.report.logic.provider.SourceProvider;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * CalFunctionEnumFactory
 *
 * <p>计算工厂
 *
 * @version 1.0
 * @author Created by ivan at 15:37.
 */
public enum CalFunctionEnumFactory implements CalFunctionInterface {
  ACC_BY_PERIOD {
    @Override
    public BigDecimal calculate(String source, String period, String... args) {
      if (Objects.isNull(args) || args.length < 1) {
        return BigDecimal.ZERO;
      }
      return SourceProvider.getProvider(source).accumulateByPeriod(period, args);
    }
  },
  ACC_BY_PLATFORM {
    @Override
    public BigDecimal calculate(String source, String period, String... args) {
      if (Objects.isNull(args) || args.length < 2) {
        return BigDecimal.ZERO;
      }
      return SourceProvider.getProvider(source).accumulateByPlatform(period, args);
    }
  },
  SUM {
    @Override
    public BigDecimal calculate(String source, String period, String... args) {
      if (Objects.isNull(args) || args.length < 1) {
        return BigDecimal.ZERO;
      }
      return SourceProvider.getProvider(source).sum(period, args);
    }
  },
  MINUS {
    @Override
    public BigDecimal calculate(String source, String period, String... args) {
      if (Objects.isNull(args) || args.length < 1) {
        return BigDecimal.ZERO;
      }
      return SourceProvider.getProvider(source).minus(period, args);
    }
  },
  SUM_MINUS {
    @Override
    public BigDecimal calculate(String source, String period, String... args) {
      if (Objects.isNull(args) || args.length < 1) {
        return BigDecimal.ZERO;
      }
      return SourceProvider.getProvider(source).sumMinus(period, args);
    }
  },
  MANUAL {
    @Override
    public BigDecimal calculate(String source, String period, String... args) {
      if (Objects.isNull(args) || args.length < 1) {
        return BigDecimal.ZERO;
      }
      return SourceProvider.getProvider(source).manual(period, args);
    }
  },
}

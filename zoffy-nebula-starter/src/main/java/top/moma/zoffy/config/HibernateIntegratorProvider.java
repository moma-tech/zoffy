package top.moma.zoffy.config;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;

/**
 * HibernateIntegratorProvider
 *
 * @author Created by ivan at 15:39.
 * @version 1.0
 */
public class HibernateIntegratorProvider implements IntegratorProvider {

  /**
   * getIntegrators
   *
   * <p>用于简化jpa sql中对象映射
   *
   * @return java.util.List<org.hibernate.integrator.spi.Integrator>
   * @author Created by ivan
   * @since 2023/3/22 17:30
   */
  @Override
  public List<Integrator> getIntegrators() {
    return new ArrayList<>();
  }
}

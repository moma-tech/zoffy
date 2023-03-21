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

  @Override
  public List<Integrator> getIntegrators() {
    return new ArrayList<>();
  }
}

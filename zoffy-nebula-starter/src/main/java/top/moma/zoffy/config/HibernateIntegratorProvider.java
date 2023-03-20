package top.moma.zoffy.config;

import io.hypersistence.utils.hibernate.type.util.ClassImportIntegrator;
import java.util.List;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import top.moma.zoffy.chinook.track.entity.Track;

/**
 * HibernateIntegratorProvider
 *
 * @version 1.0
 * @author Created by ivan at 15:39.
 */
public class HibernateIntegratorProvider implements IntegratorProvider {
  @Override
  public List<Integrator> getIntegrators() {
    ClassImportIntegrator classImportIntegrator =
        new ClassImportIntegrator(List.of(Track.TrackRecord.class));
    return List.of(classImportIntegrator);
  }
}

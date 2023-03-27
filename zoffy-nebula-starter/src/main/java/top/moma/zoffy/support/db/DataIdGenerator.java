package top.moma.zoffy.support.db;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import top.moma.zoffy.support.SerialNumberHelper;

/**
 * DataIdGenerator
 *
 * <p>数据库主键自定义生成器
 *
 * @version 1.0
 * @author Created by ivan at 16:36.
 */
public class DataIdGenerator implements IdentifierGenerator {

  @Override
  public Object generate(
      SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
      throws HibernateException {
    return SerialNumberHelper.getId();
  }
}

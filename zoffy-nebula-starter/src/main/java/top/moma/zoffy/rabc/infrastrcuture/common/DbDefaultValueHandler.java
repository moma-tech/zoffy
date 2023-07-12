package top.moma.zoffy.rabc.infrastrcuture.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import top.moma.zoffy.rabc.infrastrcuture.helper.tenant.TenantHolder;

@Component
public class DbDefaultValueHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    strictInsertFill(metaObject, BasePo.COL_CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
    strictInsertFill(metaObject, BasePo.COL_UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    strictInsertFill(metaObject, BasePo.COL_TENANT_ID, Long.class, TenantHolder.getCurrentTenant());
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    fillStrategy(metaObject, BasePo.COL_UPDATE_TIME, LocalDateTime.now());
  }
}

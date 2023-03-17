package top.moma.zoffy.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class MybatisFieldFillingHandler implements MetaObjectHandler {
  public static final String CREATE_TIME = "createTime";
  public static final String UPDATE_TIME = "updateTime";
  public static final String CREATE_USER = "createUser";
  public static final String UPDATE_USER = "updateUser";

  @Override
  public void insertFill(MetaObject metaObject) {
    if (Objects.isNull(getFieldValByName(CREATE_TIME, metaObject))) {
      setFieldValByName(CREATE_TIME, LocalDateTime.now(), metaObject);
    }
    if (Objects.isNull(getFieldValByName(UPDATE_TIME, metaObject))) {
      setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
    }
    if (Objects.isNull(getFieldValByName(CREATE_USER, metaObject))) {
      setFieldValByName(CREATE_USER, "Scheduler", metaObject);
    }
    if (Objects.isNull(getFieldValByName(UPDATE_TIME, metaObject))) {
      setFieldValByName(UPDATE_USER, "Scheduler", metaObject);
    }
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    if (Objects.isNull(getFieldValByName(UPDATE_TIME, metaObject))) {
      setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
    }
    if (Objects.isNull(getFieldValByName(UPDATE_TIME, metaObject))) {
      setFieldValByName(UPDATE_USER, "Unknown", metaObject);
    }
  }
}

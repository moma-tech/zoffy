package top.moma.zoffy.beam.auth.user.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import top.moma.m78.framework.common.model.entity.SuperModel;

/**
 * User Basic Identification Info
 *
 * @author Ivan
 * @since 2019-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_user")
public class AuthUser extends SuperModel {

  private static final long serialVersionUID = 1L;

  /** user system id */
  private String id;

  /** user login name */
  private String username;

  /** user login password */
  private String password;

  /** user type. eg. person, company, partner */
  private Boolean userType;

  /** user status. eg. normal, warn, limit, close */
  private Boolean status;

  /** create time. */
  private LocalDate createTime;

  /** update time last */
  private LocalDate updateTime;

  /** create/update user id */
  private String modifier;

  public static final String ID = "id";

  public static final String USERNAME = "username";

  public static final String PASSWORD = "password";

  public static final String USER_TYPE = "user_type";

  public static final String STATUS = "status";

  public static final String CREATE_TIME = "create_time";

  public static final String UPDATE_TIME = "update_time";

  public static final String MODIFIER = "modifier";
}

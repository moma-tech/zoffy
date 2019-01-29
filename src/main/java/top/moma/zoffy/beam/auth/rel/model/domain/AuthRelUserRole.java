package top.moma.zoffy.beam.auth.rel.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import top.moma.m78.framework.common.model.entity.SuperModel;

/**
 * @author Ivan
 * @since 2019-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("auth_rel_user_role")
public class AuthRelUserRole extends SuperModel {

  private static final long serialVersionUID = 1L;

  private String authUserId;

  private String authRoleId;

  private String id;

  private Boolean status;

  private LocalDate createTime;

  private LocalDate updateTime;

  private String modifier;

  public static final String AUTH_USER_ID = "auth_user_id";

  public static final String AUTH_ROLE_ID = "auth_role_id";

  public static final String ID = "id";

  public static final String STATUS = "status";

  public static final String CREATE_TIME = "create_time";

  public static final String UPDATE_TIME = "update_time";

  public static final String MODIFIER = "modifier";
}

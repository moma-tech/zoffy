package top.moma.zoffy.rbac.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import top.moma.zoffy.common.base.BaseEntity;

/**
 * ZoffyUser
 *
 * <p>用户表;
 *
 * @version 1.0
 * @author Created by ivan at 2023-3-27.
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "zoffy_user",
    indexes = {
      @Index(name = "email", columnList = "user_email", unique = true),
      @Index(name = "phone", columnList = "user_phone", unique = true),
    })
public class ZoffyUser extends BaseEntity {
  /** 用户ID */
  @Id
  @GeneratedValue
  @Column(name = "user_id")
  private Long userId;
  /** 用户名 */
  @Column(name = "user_name")
  private String userName;
  /** 用户密码 */
  @Column(name = "user_password")
  private String userPassword;
  /** 用户邮箱 */
  @Column(name = "user_email")
  private String userEmail;
  /** 用户手机号 */
  @Column(name = "user_phone")
  private String userPhone;

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    ZoffyUser that = (ZoffyUser) o;
    return Objects.equals(userId, that.userId);
  }

  private static final String USER_ID = "user_id";
  private static final String USER_NAME = "user_name";
  private static final String USER_PASSWORD = "user_password";
  private static final String USER_EMAIL = "user_email";
  private static final String USER_PHONE = "user_phone";
  private static final String DELETE_MARK = "delete_mark";
  private static final String REMARKS = "remarks";
  private static final String TENANT_ID = "tenant_id";
  private static final String REVISION = "revision";
  private static final String CREATE_USER = "create_user";
  private static final String CREATE_TIME = "create_time";
  private static final String UPDATE_USER = "update_user";
  private static final String UPDATE_TIME = "update_time";
}

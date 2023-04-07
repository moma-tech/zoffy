package top.moma.zoffy.rbac.storage.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import top.moma.zoffy.common.base.BaseEntity;
import top.moma.zoffy.rbac.storage.role.entity.ZoffyRole;

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
  @GeneratedValue(generator = "cus_id")
  @GenericGenerator(name = "cus_id", strategy = "top.moma.zoffy.support.db.DataIdGenerator")
  @Column(name = "user_id", nullable = false)
  private Long userId;
  /** 用户名 */
  @Column(name = "user_name", nullable = false)
  private String userName;
  /** 用户密码 */
  @Column(name = "user_password", nullable = false)
  private String userPassword;
  /** 用户邮箱 */
  @Column(name = "user_email", nullable = false)
  private String userEmail;
  /** 用户手机号 */
  @Column(name = "user_phone", nullable = false)
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

  public static final String USER_ID = "user_id";
  public static final String USER_NAME = "user_name";
  public static final String USER_PASSWORD = "user_password";
  public static final String USER_EMAIL = "user_email";
  public static final String USER_PHONE = "user_phone";
  public static final String DELETE_MARK = "delete_mark";
  public static final String REMARKS = "remarks";
  public static final String TENANT_ID = "tenant_id";
  public static final String REVISION = "revision";
  public static final String CREATE_USER = "create_user";
  public static final String CREATE_TIME = "create_time";
  public static final String UPDATE_USER = "update_user";
  public static final String UPDATE_TIME = "update_time";

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "zoffy_user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<ZoffyRole> roleSet;
}

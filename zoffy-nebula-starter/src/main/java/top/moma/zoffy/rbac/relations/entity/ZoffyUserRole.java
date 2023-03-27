package top.moma.zoffy.rbac.relations.entity;

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
 * ZoffyUserRole
 *
 * <p>用户角色关系;
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
    name = "zoffy_user_role",
    indexes = {
      @Index(name = "user_role", columnList = "user_id,role_id", unique = true),
    })
public class ZoffyUserRole extends BaseEntity {
  /** 表内主键 */
  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;
  /** 用户表主键 */
  @Column(name = "user_id")
  private Long userId;
  /** 角色表主键 */
  @Column(name = "role_id")
  private Long roleId;

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
    ZoffyUserRole that = (ZoffyUserRole) o;
    return Objects.equals(id, that.id);
  }

  private static final String UR_ID = "id";
  private static final String USER_ID = "user_id";
  private static final String ROLE_ID = "role_id";
  private static final String DELETE_MARK = "delete_mark";
  private static final String REMARKS = "remarks";
  private static final String TENANT_ID = "tenant_id";
  private static final String REVISION = "revision";
  private static final String CREATE_USER = "create_user";
  private static final String CREATE_TIME = "create_time";
  private static final String UPDATE_USER = "update_user";
  private static final String UPDATE_TIME = "update_time";
}

package top.moma.zoffy.rbac.role.entity;

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
 * ZoffyRole
 *
 * <p>角色表;
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
    name = "zoffy_role",
    indexes = {
      @Index(name = "role_code", columnList = "role_code", unique = true),
    })
public class ZoffyRole extends BaseEntity {
  /** 角色ID */
  @Id
  @GeneratedValue
  @Column(name = "role_id")
  private Long roleId;
  /** 角色名称 */
  @Column(name = "role_name")
  private String roleName;
  /** 角色编码 */
  @Column(name = "role_code")
  private String roleCode;
  /** 父级角色ID;如果是顶级角色，父节点为0 */
  @Column(name = "parent_role_id")
  private Long parentRoleId;

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
    ZoffyRole that = (ZoffyRole) o;
    return Objects.equals(roleId, that.roleId);
  }

  private static final String ROLE_ID = "role_id";
  private static final String ROLE_NAME = "role_name";
  private static final String ROLE_CODE = "role_code";
  private static final String PARENT_ROLE_ID = "parent_role_id";
  private static final String DELETE_MARK = "delete_mark";
  private static final String REMARKS = "remarks";
  private static final String TENANT_ID = "tenant_id";
  private static final String REVISION = "revision";
  private static final String CREATE_USER = "create_user";
  private static final String CREATE_TIME = "create_time";
  private static final String UPDATE_USER = "update_user";
  private static final String UPDATE_TIME = "update_time";
}

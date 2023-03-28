package top.moma.zoffy.rbac.role.entity;

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
import top.moma.zoffy.rbac.resource.entity.ZoffyResource;
import top.moma.zoffy.rbac.user.entity.ZoffyUser;

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
@SuperBuilder
@Accessors(chain = true)
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
  @GeneratedValue(generator = "cus_id")
  @GenericGenerator(name = "cus_id", strategy = "top.moma.zoffy.support.db.DataIdGenerator")
  @Column(name = "role_id", nullable = false)
  private Long roleId;
  /** 角色名称 */
  @Column(name = "role_name", nullable = false)
  private String roleName;
  /** 角色编码 */
  @Column(name = "role_code", nullable = false)
  private String roleCode;
  /** 父级角色ID;如果是顶级角色，父节点为0 */
  @Column(name = "parent_role_id", nullable = false, columnDefinition = "bigint default 0")
  private Long parentRoleId;
  /** 角色树路径 */
  @Column(name = "role_path")
  private String rolePath;

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

  public static final String ROLE_ID = "role_id";
  public static final String ROLE_NAME = "role_name";
  public static final String ROLE_CODE = "role_code";
  public static final String PARENT_ROLE_ID = "parent_role_id";
  public static final String ROLE_PATH = "role_path";
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
      name = "zoffy_role_resource",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "resource_id"))
  private Set<ZoffyResource> resourceSet;

  @ManyToMany(mappedBy = "roleSet")
  private Set<ZoffyUser> userSet;
}

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
 * ZoffyRoleResource
 *
 * <p>角色资源关系;
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
    name = "zoffy_role_resource",
    indexes = {
      @Index(name = "role_resource", columnList = "role_id,resource_id", unique = true),
    })
public class ZoffyRoleResource extends BaseEntity {
  /** 表内主键 */
  @Id
  @GeneratedValue
  @Column(name = "id")
  private String id;
  /** 角色主键 */
  @Column(name = "role_id")
  private String roleId;
  /** 资源主键 */
  @Column(name = "resource_id")
  private String resourceId;

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
    ZoffyRoleResource that = (ZoffyRoleResource) o;
    return Objects.equals(id, that.id);
  }

  private static final String RR_ID = "id";
  private static final String ROLE_ID = "role_id";
  private static final String RESOURCE_ID = "resource_id";
  private static final String DELETE_MARK = "delete_mark";
  private static final String REMARKS = "remarks";
  private static final String TENANT_ID = "tenant_id";
  private static final String REVISION = "revision";
  private static final String CREATE_USER = "create_user";
  private static final String CREATE_TIME = "create_time";
  private static final String UPDATE_USER = "update_user";
  private static final String UPDATE_TIME = "update_time";
}

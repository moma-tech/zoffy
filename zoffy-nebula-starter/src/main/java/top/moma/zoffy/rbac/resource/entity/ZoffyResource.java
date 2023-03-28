package top.moma.zoffy.rbac.resource.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
import top.moma.zoffy.rbac.role.entity.ZoffyRole;

/**
 * ZoffyResource
 *
 * <p>资源权限表;
 *
 * @version 1.0
 * @author Created by ivan at 2023-3-27.
 */
@SuperBuilder
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "zoffy_resource",
    indexes = {
      @Index(name = "resource_uri", columnList = "resource_uri", unique = true),
    })
public class ZoffyResource extends BaseEntity {
  /** 资源id */
  @Id
  @GeneratedValue(generator = "cus_id")
  @GenericGenerator(name = "cus_id", strategy = "top.moma.zoffy.support.db.DataIdGenerator")
  @Column(name = "resource_id", nullable = false)
  private Long resourceId;
  /** 资源名称 */
  @Column(name = "resource_name", nullable = false)
  private String resourceName;
  /** 父级资源id;如果是顶级资源，父节点为0 */
  @Column(name = "parent_resource_id", nullable = false, columnDefinition = "bigint default 0")
  private Long parentResourceId;
  /** 资源树路径;所有父级资源主键，以英文逗号隔开如：一级，二级等 */
  @Column(name = "resource_path")
  private String resourcePath;
  /** 资源类型 */
  @Column(name = "resource_type", nullable = false, columnDefinition = "varchar default 0")
  private String resourceType;
  /** 资源URI */
  @Column(name = "resource_uri", nullable = false)
  private String resourceUri;
  /** 资源鉴权类型 */
  @Column(name = "resource_auth_type", nullable = false, columnDefinition = "varchar default 0")
  private String resourceAuthType;
  /** 资源标识 */
  @Column(name = "resource_code")
  private String resourceCode;
  /** 资源层级;资源树层级 */
  @Column(name = "resource_level", columnDefinition = "integer default 1")
  private Integer resourceLevel;
  /** 资源排序 */
  @Column(name = "sort_order", columnDefinition = "integer default 1")
  private Integer sortOrder;

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
    ZoffyResource that = (ZoffyResource) o;
    return Objects.equals(resourceId, that.resourceId);
  }

  public static final String RESOURCE_ID = "resource_id";
  public static final String RESOURCE_NAME = "resource_name";
  public static final String PARENT_RESOURCE_ID = "parent_resource_id";
  public static final String RESOURCE_PATH = "resource_path";
  public static final String RESOURCE_TYPE = "resource_type";
  public static final String RESOURCE_URI = "resource_uri";
  public static final String RESOURCE_AUTH_TYPE = "resource_auth_type";
  public static final String RESOURCE_CODE = "resource_code";
  public static final String RESOURCE_LEVEL = "resource_level";
  public static final String SORT_ORDER = "sort_order";
  public static final String DELETE_MARK = "delete_mark";
  public static final String REMARKS = "remarks";
  public static final String TENANT_ID = "tenant_id";
  public static final String REVISION = "revision";
  public static final String CREATE_USER = "create_user";
  public static final String CREATE_TIME = "create_time";
  public static final String UPDATE_USER = "update_user";
  public static final String UPDATE_TIME = "update_time";

  @ManyToMany(mappedBy = "resourceSet")
  private Set<ZoffyRole> roleSet;
}

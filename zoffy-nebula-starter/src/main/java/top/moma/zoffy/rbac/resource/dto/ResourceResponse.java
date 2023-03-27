package top.moma.zoffy.rbac.resource.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceResponse {
  private Long resourceId;
  /** 资源名称 */
  private String resourceName;
  /** 父级资源id;如果是顶级资源，父节点为0 */
  private Long parentResourceId;
  /** 资源树路径;所有父级资源主键，以英文逗号隔开如：一级，二级等 */
  private String resourcePath;
  /** 资源类型 */
  private String resourceType;
  /** 资源URI */
  private String resourceUri;
  /** 资源鉴权类型 */
  private String resourceAuthType;
  /** 资源标识 */
  private String resourceCode;
  /** 资源层级;资源树层级 */
  private Integer resourceLevel;
  /** 资源排序 */
  private Integer sortOrder;
}

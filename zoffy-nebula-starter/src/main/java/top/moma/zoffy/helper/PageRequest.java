package top.moma.zoffy.helper;

import java.io.Serializable;
import lombok.Data;

/**
 * 分页请求
 *
 * @author weiqiangguo
 */
@Data
public class PageRequest implements Serializable {

  private static final long serialVersionUID = -7543151275407977175L;
  private Long page;
  private Long pageSize;
}

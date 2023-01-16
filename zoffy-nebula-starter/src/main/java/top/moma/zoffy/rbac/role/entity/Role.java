package top.moma.zoffy.rbac.role.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements java.io.Serializable {
  @TableId private Integer roleId;

  private String roleName;
}

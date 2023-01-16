package top.moma.zoffy.rbac.user_role.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole implements java.io.Serializable {
  @TableId private Integer urId;
  private Integer userId;
  private Integer roleId;
}

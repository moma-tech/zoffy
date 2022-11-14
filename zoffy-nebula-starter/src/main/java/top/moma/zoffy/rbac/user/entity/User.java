package top.moma.zoffy.rbac.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements java.io.Serializable {
  @TableId(type = IdType.AUTO)
  private Integer userId;

  private String email;

  private String phoneNo;

  private String userName;
}

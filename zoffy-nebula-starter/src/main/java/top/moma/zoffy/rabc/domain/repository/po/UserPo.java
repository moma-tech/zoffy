package top.moma.zoffy.rabc.domain.repository.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import top.moma.zoffy.rabc.infrastrcuture.common.BasePo;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("zoffy_user")
public class UserPo extends BasePo implements java.io.Serializable {
  /** 用户ID */
  @TableId private Long userId;
  /** 用户名 */
  private String userName;
  /** 用户密码 */
  private String userPassword;
  /** 用户邮箱 */
  private String userEmail;
  /** 用户手机号 */
  private String userPhone;
}

package top.moma.zoffy.rabc.infrastrcuture.common;

import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import top.moma.zoffy.rabc.framework.constants.GeneralConstants;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BasePo implements java.io.Serializable {
  /** 租户id */
  private Long tenantId;

  /** 乐观锁 */
  @Version private Long revision;

  /** 创建时间 */
  private LocalDateTime createTime;

  /** 修改时间 */
  private LocalDateTime updateTime;

  /** 创建用户 */
  private Long createUser;

  /** 修改用户 */
  private Long updateUser;

  /** 数据是否逻辑删除 */
  private Integer deleteMark = GeneralConstants.DB_DATA_AVAILABLE;

  /** 数据备注 */
  private String remarks;

  public static final String COL_TENANT_ID = "tenant_id";
  public static final String COL_REVISION = "revision";
  public static final String COL_CREATE_TIME = "create_time";
  public static final String COL_UPDATE_TIME = "update_time";
  public static final String COL_CREATE_USER = "create_user";
  public static final String COL_UPDATE_USER = "update_user";
  public static final String COL_DELETE_MARK = "delete_mark";
  public static final String COL_REMARKS = "remarks";
}

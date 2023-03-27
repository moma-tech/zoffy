package top.moma.zoffy.common.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * BaseEntity
 *
 * <p>JPA基础对象
 *
 * <p>用于通用字段及默认值配置
 *
 * @version 1.0
 * @author Created by ivan at 16:26.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements java.io.Serializable {

  /** 创建时间 */
  @Column(name = "create_time")
  @CreatedDate
  private LocalDateTime createTime;

  /** 修改时间 */
  @Column(name = "update_time")
  @LastModifiedDate
  private LocalDateTime updateTime;

  /** 创建用户 */
  @Column(name = "create_user")
  private Long createUser;

  /** 修改用户 */
  @Column(name = "update_name")
  private Long updateUser;

  /** 数据是否逻辑删除 */
  @Column(name = "delete_mark")
  private int deleteMark = 0;

  /** 数据备注 */
  @Column(name = "remarks")
  private String remarks;
}

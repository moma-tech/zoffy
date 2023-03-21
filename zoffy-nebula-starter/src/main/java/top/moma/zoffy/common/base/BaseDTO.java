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

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDTO implements java.io.Serializable {

  @Column(name = "create_time")
  @CreatedDate
  private LocalDateTime createTime;

  @Column(name = "update_time")
  @LastModifiedDate
  private LocalDateTime updateTime;

  @Column(name = "create_user")
  private Long createUser;

  @Column(name = "update_name")
  private Long updateUser;

  @Column(name = "deleted")
  private int deleted = 0;

  @Column(name = "remarks")
  private String remarks;
}

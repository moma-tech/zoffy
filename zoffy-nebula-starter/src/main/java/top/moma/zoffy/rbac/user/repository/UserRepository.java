package top.moma.zoffy.rbac.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.moma.zoffy.rbac.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  /**
   * findByEmail
   *
   * @param email email
   * @return top.moma.zoffy.rbac.user.entity.UserEntity
   * @author Created by ivan
   * @since 2023/3/22 17:56
   */
  UserEntity findByEmail(String email);
}

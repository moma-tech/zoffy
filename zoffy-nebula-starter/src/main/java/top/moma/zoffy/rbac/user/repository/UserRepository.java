package top.moma.zoffy.rbac.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.moma.zoffy.rbac.user.entity.ZoffyUser;

public interface UserRepository extends JpaRepository<ZoffyUser, Long> {

  /**
   * description findByUserEmail
   *
   * @param email email
   * @return top.moma.zoffy.rbac.user.entity.ZoffyUser
   * @author Created by ivan
   * @since 2023/3/27 14:20
   */
  ZoffyUser findByUserEmail(String email);
}

package top.moma.zoffy.rbac.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.moma.zoffy.rbac.user.entity.ZoffyUser;

public interface UserRepository
    extends JpaRepository<ZoffyUser, Long>, JpaSpecificationExecutor<ZoffyUser> {

  /**
   * description findByUserEmail
   *
   * @param email email
   * @return top.moma.zoffy.rbac.user.entity.ZoffyUser
   * @author Created by ivan
   * @since 2023/3/27 14:20
   */
  ZoffyUser findByUserEmailAndDeleteMark(String email, int deleteMark);

  ZoffyUser findByUserPhoneAndDeleteMark(String phone, int deleteMark);

  ZoffyUser findByUserNameAndDeleteMark(String userName, int deleteMark);

  ZoffyUser findByUserEmailAndDeleteMarkOrUserPhoneAndDeleteMark(
      String email, int deleteMark, String phone, int deleteMark2);
}

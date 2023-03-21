package top.moma.zoffy.rbac.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.moma.zoffy.rbac.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByEmail(String email);
}

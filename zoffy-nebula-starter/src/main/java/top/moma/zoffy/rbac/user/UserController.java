package top.moma.zoffy.rbac.user;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping("/list")
  @PreAuthorize("hasRole('ADMIN')")
  public List<User> listUser() {
    List<User> users = new ArrayList<>();
    users.add(createUser(1L));
    users.add(createUser(2L));
    return users;
  }

  private User createUser(Long userId) {
    return User.builder().id(userId).userName("abc").password("111111").build();
  }
}

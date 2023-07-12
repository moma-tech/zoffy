package top.moma.zoffy.rabc.presentation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.zoffy.rabc.application.UserApplication;
import top.moma.zoffy.rabc.domain.covertor.UserConvertor;
import top.moma.zoffy.rabc.infrastrcuture.helper.tenant.TenantHolder;
import top.moma.zoffy.rabc.presentation.response.UserResponse;

@RestController
public class UserController {

  @Autowired UserApplication userApplication;

  @GetMapping("/user")
  List<UserResponse> listUser() {
    TenantHolder.setCurrentTenant(1L);
    return userApplication.listUser().stream()
        .map(UserConvertor.INSTANCE::entityToResponse)
        .collect(Collectors.toList());
  }
}

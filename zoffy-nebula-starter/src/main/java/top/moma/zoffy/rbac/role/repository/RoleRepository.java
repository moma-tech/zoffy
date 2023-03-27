package top.moma.zoffy.rbac.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.moma.zoffy.rbac.role.entity.ZoffyRole;

/**
 * RoleRepository
 *
 * @version 1.0
 * @author Created by ivan at 16:31.
 */
public interface RoleRepository
    extends JpaRepository<ZoffyRole, Long>, JpaSpecificationExecutor<ZoffyRole> {}

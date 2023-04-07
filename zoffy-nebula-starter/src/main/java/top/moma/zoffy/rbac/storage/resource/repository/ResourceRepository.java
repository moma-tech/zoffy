package top.moma.zoffy.rbac.storage.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.moma.zoffy.rbac.storage.resource.entity.ZoffyResource;

public interface ResourceRepository
    extends JpaRepository<ZoffyResource, Long>, JpaSpecificationExecutor<ZoffyResource> {}
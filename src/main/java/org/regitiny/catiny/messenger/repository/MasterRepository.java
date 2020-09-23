package org.regitiny.catiny.messenger.repository;

import org.regitiny.catiny.messenger.domain.Master;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Master entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MasterRepository extends JpaRepository<Master, Long>, JpaSpecificationExecutor<Master> {
}

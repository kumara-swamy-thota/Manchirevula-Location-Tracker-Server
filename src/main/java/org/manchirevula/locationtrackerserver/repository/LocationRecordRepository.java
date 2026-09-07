package org.manchirevula.locationtrackerserver.repository;

import org.manchirevula.locationtrackerserver.model.LocationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRecordRepository extends JpaRepository<LocationRecord, Long> {
    Optional<LocationRecord> findTopByOrderByTimestampDesc();
}
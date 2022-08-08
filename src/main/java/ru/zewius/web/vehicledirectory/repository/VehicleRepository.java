package ru.zewius.web.vehicledirectory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.zewius.web.vehicledirectory.entity.VehicleEntity;

public interface VehicleRepository extends JpaRepository<VehicleEntity, String> {
    @Query(value = "SELECT pg_size_pretty(pg_database_size(current_database()))", nativeQuery = true)
    String getDatabaseSize();
}

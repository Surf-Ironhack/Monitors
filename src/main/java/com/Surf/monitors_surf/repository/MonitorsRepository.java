package com.Surf.monitors_surf.repository;

import com.Surf.monitors_surf.models.Monitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorsRepository extends JpaRepository<Monitors, Long>{

}

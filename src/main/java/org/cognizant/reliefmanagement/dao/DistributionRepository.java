package org.cognizant.reliefmanagement.dao;

import org.cognizant.reliefmanagement.entity.Distribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionRepository extends JpaRepository<Distribution, Integer> {

    boolean existsByItemId(int itemId);
}
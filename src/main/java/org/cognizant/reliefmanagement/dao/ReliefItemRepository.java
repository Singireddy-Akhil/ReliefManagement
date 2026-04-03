package org.cognizant.reliefmanagement.dao;
import org.cognizant.reliefmanagement.entity.ReliefItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReliefItemRepository extends JpaRepository<ReliefItem , Integer> {

    ReliefItem findByName(String name);
}

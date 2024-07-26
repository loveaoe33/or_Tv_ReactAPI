package jpaConnection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import patientObject.patientClass;


@Repository
public interface orTv_JPAInterface extends JpaRepository<patientClass,Long> {
    // Implement interface methods here
}

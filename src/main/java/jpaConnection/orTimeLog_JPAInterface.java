package jpaConnection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patientObject.orListClassLog;

@Repository
public interface orTimeLog_JPAInterface extends JpaRepository<orListClassLog, Long> {

}

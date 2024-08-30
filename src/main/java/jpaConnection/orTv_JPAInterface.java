package jpaConnection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import patientObject.orListClass;
import patientObject.patientClass;


@Repository
public interface orTv_JPAInterface extends JpaRepository<patientClass,Long> {
    // Implement interface methods here
	

}

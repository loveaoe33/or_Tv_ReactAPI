package jpaConnection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import patientObject.orListClass;
import patientObject.patientClass;


@Repository
public interface orTv_JPAInterface extends JpaRepository<patientClass,Long> {
    // Implement interface methods here

	
	
	
	@Modifying // 使用int可以知道更新多少資料  
	@Transactional // 確認全成功不然全失敗
    @Query("UPDATE patientClass o SET o.Patient_Number = ?2,o.Patient_Name=?4 ,o.Patient_Gender=?3,o.Patient_Status=?5 WHERE o.id = ?1 ")
	int  updatePatientDetail(Long id,String Patient_Number,String Gender,String Patient_Name,String Status);
}

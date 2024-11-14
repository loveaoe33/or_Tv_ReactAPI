package jpaConnection;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import patientObject.orListClass;
import patientObject.orListClassLog;
import patientObject.patientClass;

@Repository
public interface orTime_JPAInterface extends JpaRepository<orListClass, Long> {
//	@Modifying 
//	@Query("SELECT o FROM orListClass o WHERE o.List_Status = 'Process' AND o.Inert_Time = CURRENT_DATE")
//	Optional<List<orListClass>> selectProcessList();
//
//	@Modifying
//	@Query("SELECT o FROM orListClass o WHERE o.List_Status = 'Finish' AND o.Inert_Time = CURRENT_DATE")
//	Optional<List<orListClass>> selectFinishList();

	@Modifying
	@Query("SELECT o FROM orListClass o WHERE MONTH(o.Insert_Time) = MONTH(CURRENT_DATE) AND YEAR(o.Insert_Time) = YEAR(CURRENT_DATE)")
    List<orListClass> SelectDataList(); // 直接返回 List
	
	@Modifying // 使用int可以知道更新多少資料  
	@Transactional // 確認全成功不然全失敗
    @Query("UPDATE orListClass o SET o.Patient_Json_String = ?2,o.Step_Number=?3 WHERE o.List_Number = ?1   AND o.List_Status = 'Process'")
	int UpdateTemp(String List_Number,String PatientJson,int StepNumber);
	
	
	@Modifying 
	@Transactional 
    @Query("UPDATE orListClass o SET o.Patient_Json_String = ?2,o.Step_Number=?3,o.List_Status='Finish',o.Finish_Time=?4 WHERE o.List_Number = ?1")
	int UpdateFinish(String List_Number,String PatientJson,int StepNumber,LocalDateTime Finish_time);
	
	@Modifying 
	@Transactional 
    @Query("UPDATE orListClass o SET o.Patient_Json_String = ?2, o.Finish_Time = ?3, o.List_Status = ?4 WHERE o.id = ?1 AND o.List_Status = 'Process'")
	int UpdateOrList(Long id,String PatientJson,Date Finish_Time, String List_Status);
   

	@Modifying 
	@Transactional 
    @Query("UPDATE orListClass o SET o.Patient_Json_String = ?2, o.Edit_Log_Number=?3 WHERE o.List_Number = ?1")
    int UpdateEditLog(String List_Number,String Patient_JsonString,String Edit_Log_Number);
	

	@Modifying 
	@Transactional 
    @Query("DELETE orListClass o WHERE o.List_Number = ?1")
	int DeleteForm(String List_Number);
	
//	@Modifying 
//	@Transactional 
//	@Query("UPDATE orListClass p SET p.OrRoom = ?2,p.PatientJson = ?3,p.StepNumber = ?4,p.FirstTag = ?5,p.EditLog_Number = ?6,p.Inert_Time = ?7,p.List_Status = ?8,p.Finish_Time = ?9 WHERE p.id = ?1")
//	int editOrList(Long id, String OrRoom, String PatientJson, int StepNumber, String FirstTag, String EditLog_Number, Date Inert_Time, String List_Status, Date Finish_Time);

}







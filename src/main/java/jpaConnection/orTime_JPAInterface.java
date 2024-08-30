package jpaConnection;

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
	@Modifying 
	@Query("SELECT *from ortime o where o.List_Status==Process AND DATE(o.Inert_Time)=CURDATE()")
	Optional<List<orListClass>> selectProcessList();

	@Modifying
	@Query("SELECT *from ortime o where o.List_Status==Finish AND DATE(o.Inert_Time)=CURDATE()")
	Optional<List<orListClass>> selectFinishList();

	@Modifying // 使用int可以知道更新多少資料
	@Transactional // 確認全成功不然全失敗
	@Query("UPDATE Ortime o SET o.Call_PatientJson = ?1, o.Arrival_AreaJson = ?2, o.AnaeStart_DatesJson = ?3, "
			+ "o.AnaeEnd_DatesJson = ?4, o.OperPreStart_DateJson = ?5, o.OperPreEnd_TimesJson = ?6, "
			+ "o.OperStart_TimesJson = ?7, o.OperEnd_DatesJson = ?8 , o.Finish_Time=?9"
			+ "WHERE o.ListNumber = ?10 AND o.List_Status = 'Process'")
	int updateOrList(String callPatientJson, String arrivalAreaJson, String anaeStartDatesJson, String anaeEndDatesJson,
			String operPreStartDateJson, String operPreEndTimesJson, String operStartTimesJson, String operEndDatesJson,
			String Finish_Time, int listNumber);


	
	@Modifying 
	@Transactional 
	@Query("UPDATE Patient p SET " +
	        "p.orRoom = ?2, " +
	        "p.callPatientJson = ?3, " +
	        "p.arrivalAreaJson = ?4, " +
	        "p.anaeStartDatesJson = ?5, " +
	        "p.anaeEndDatesJson = ?6, " +
	        "p.operPreStartDateJson = ?7, " +
	        "p.operPreEndTimesJson = ?8, " +
	        "p.operStartTimesJson = ?9, " +
	        "p.operEndDatesJson = ?10, " +
	        "p.stepNumber = ?11, " +
	        "p.firstTag = ?12, " +
	        "p.editLogNumber = ?13, " +
	        "p.inertTime = ?14, " +
	        "p.listStatus = ?15, " +
	        "p.finishTime = ?16 " +
	        "WHERE p.patientNumber = ?1")
	int editOrList(String patientNumber,
	        String orRoom,
	        String callPatientJson,
	        String arrivalAreaJson,
	        String anaeStartDatesJson,
	        String anaeEndDatesJson,
	        String operPreStartDateJson,
	        String operPreEndTimesJson,
	        String operStartTimesJson,
	        String operEndDatesJson,
	        int listNumber,
	        int stepNumber,
	        String firstTag,
	        int editLogNumber,
	        String inertTime,
	        String listStatus,
	        String finishTime);
}







package jpaConnection;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import jpaConnection.orTv_JPAInterface;
import patientObject.marqueeClass;
import patientObject.orListClass;
import patientObject.orListClassLog;
import patientObject.patientClass;

@Service
public class orTv_JPAController {

	private final orTv_JPAInterface orTv_jpaInterface;
	private final orTime_JPAInterface orTime_jPAInterface;
	private final orTimeLog_JPAInterface orTimeLog_jPAInterface;
	private final orTv_Marquee_JPAInterface orTv_Marquee_jPAInterface;
	private final HashMap<String, String> tampPatient = new HashMap<String, String>();
	private final org.slf4j.Logger TampLogger = LoggerFactory.getLogger(orTv_JPAController.class);
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");

	@Autowired
	public orTv_JPAController(orTv_JPAInterface orTv_jpaInterface, orTime_JPAInterface orTime_jPAInterface,
			orTimeLog_JPAInterface orTimeLog_jPAInterface,orTv_Marquee_JPAInterface orTv_Marquee_jPAInterface) {
		this.orTv_jpaInterface = orTv_jpaInterface;
		this.orTime_jPAInterface = orTime_jPAInterface;
		this.orTimeLog_jPAInterface = orTimeLog_jPAInterface;
		this.orTv_Marquee_jPAInterface=orTv_Marquee_jPAInterface;
	}

	
	public List<marqueeClass> selectMarquee() { //帶出跑馬燈
		List<marqueeClass>data=orTv_Marquee_jPAInterface.findAll();
		return data;
		
	}
	
	public String insertMarquee(marqueeClass marquee) { //新增跑馬燈
		orTv_Marquee_jPAInterface.save(marquee);
		return null;
		
		
	}
	public String deleteMarquee(long l) { //刪除跑馬燈
		orTv_Marquee_jPAInterface.deleteAllById(null);
		return null;
		
	}
	
	public String insertPatient(patientClass patient) {// 新增開刀內部病人
		orTv_jpaInterface.save(patient);
		return "Sucess";

	}

	public String getLocalTime() {
		LocalDateTime time = LocalDateTime.now();
		String DateString = time.format(formatter);
		return DateString;

	}

	public String deletePatient(Long id) {// 刪除開刀內部病人
		orTv_jpaInterface.deleteById(id);
		return "Sucess";

	}

	public List<patientClass> getPatient() { // 取得開刀內部所有病人
		String test = "";
		List<patientClass> data = orTv_jpaInterface.findAll();
		return data;

	}

	public String insertOrtime(orListClass orlistClass) { // 新增開刀紀錄
		try {

			orListClass data = orTime_jPAInterface.save(orlistClass); // 成功儲存會回傳單筆物件
			return "Sucess";
		} catch (DataAccessException e) {
			return "fail";
		}

	}

	public String updateOrtime() {// 更新開刀紀錄

		return "Sucess";
	}

	public String editOrtime(orListClass orlistClass,orListClassLog orlistClassLog) {// 編輯開刀紀錄
		try {
			int Rows = orTime_jPAInterface.UpdateEditLog(orlistClass.getList_Number(), orlistClass.getPatient_Json_String(),orlistClass.getEdit_Log_Number());
			System.out.println("到這"+Rows);

			if (Rows < 1) {
				return "fail";

			} else {

				orTimeLog_jPAInterface.save(orlistClassLog);
			}
			return "Sucess";
		} catch (DataAccessException e) {
			System.out.println("editOrtime錯誤" + e.toString());
			return "fail";
		}

	}

	public String tempFinish(orListClass orlistClass) { // 完成開刀暫存
		try {
			System.out.println(orlistClass);

			if (orlistClass.getStep_Number() == 8) {
				int Rows = orTime_jPAInterface.UpdateFinish(orlistClass.getList_Number(),
						orlistClass.getPatient_Json_String(), orlistClass.getStep_Number(),
						LocalDateTime.parse(getLocalTime(), formatter));
				return (Rows < 1) ? "fail" : "Sucess";

			}
			return "fail";
		} catch (DataAccessException e) {
			System.out.println("tempFinish錯誤" + e.toString());
			return "fail";
		}

	}

	public String tempPatient(orListClass orlistClass) { // 寫入開刀暫存OK
		try {

			if (orlistClass.getStep_Number() == 0) {
				orListClass data = orTime_jPAInterface.save(orlistClass); // 成功儲存會回傳單筆物件
				return "Sucess";

			} else {
				int Rows = orTime_jPAInterface.UpdateTemp(orlistClass.getList_Number(),
						orlistClass.getPatient_Json_String(), orlistClass.getStep_Number()); // 成功儲存會回傳單筆物件

				return (Rows < 1) ? "fail" : "Sucess";
			}
//			tampPatient.put(orlistClass.getList_Number(), orlistClass.getPatient_Json_String());
//			TampLogger.info("OrTimeTamp紀錄:"+tampPatient.toString());
//			TampLogger.warn("orTimeLog");
		} catch (DataAccessException e) {
			System.out.println("tempPatient錯誤" + e.toString());
			return "fail";
		}

	}
	
	
	public String deleteTemp(orListClass orlistClass) {
		try {
			int Rows = orTime_jPAInterface.DeleteForm(orlistClass.List_Number);
			return (Rows < 1) ? "fail" : "Sucess";
	} catch (DataAccessException e) {
		System.out.println("deleteTemp錯誤" + e.toString());
		return "fail";
	}		
}
		
	

	public String getOrtList(String ListNumber) { // 撈出暫存明細OK
		return tampPatient.get(ListNumber);
	}

	public List<orListClass> loadOrtList() { // 撈出單號紀錄 OK
		return orTime_jPAInterface.SelectDataList();

	}

	public List<orListClass> getTemp() { // 暫時資料 OK
		return orTime_jPAInterface.SelectDataList();
	}

}

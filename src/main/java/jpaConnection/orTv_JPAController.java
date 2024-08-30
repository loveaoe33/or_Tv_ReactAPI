package jpaConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import jpaConnection.orTv_JPAInterface;
import patientObject.orListClass;
import patientObject.orListClassLog;
import patientObject.patientClass;

@Service
public class orTv_JPAController {

	private final orTv_JPAInterface orTv_jpaInterface;
	private final orTime_JPAInterface orTime_jPAInterface;
	private final orTimeLog_JPAInterface orTimeLog_jPAInterface;

	@Autowired
	public orTv_JPAController(orTv_JPAInterface orTv_jpaInterface, orTime_JPAInterface orTime_jPAInterface,orTimeLog_JPAInterface orTimeLog_jPAInterface) {
		this.orTv_jpaInterface = orTv_jpaInterface;
		this.orTime_jPAInterface = orTime_jPAInterface;
        this.orTimeLog_jPAInterface=orTimeLog_jPAInterface;
	}

	public String insertPatient(patientClass patient) {// 新增開刀內部病人
		orTv_jpaInterface.save(patient);
		return "Sucess";

	}

	public String deletePatient(Long id) {// 刪除開刀內部病人
		orTv_jpaInterface.deleteById(id);
		return "Sucess";

	}

	public String getPatient() { // 取得開刀內部所有病人
		String test = "";
		List<patientClass> data = orTv_jpaInterface.findAll();
		for (patientClass datas : data) {
			test = test + datas.getPatient_Name();

		}
		return test;

	}

	public String insertOrtime(orListClass orlistClass) { // 新增開刀紀錄
		try {
			
			orListClass  data=orTime_jPAInterface.save(orlistClass); //成功儲存會回傳單筆物件
			return "Sucess";
		}catch(DataAccessException  e) {
			return "fail";
		}

	}

	public String updateOrtime() {// 更新開刀紀錄

		return "Sucess";
	}

	public String editOrtime() {// 編輯開刀紀錄

		orTimeLog_jPAInterface.save(null);	
		return "Sucess";
	}

	public Optional<List<orListClass>> loadOrtProcess() { // 撈出本日處理中記錄  OK
		return orTime_jPAInterface.selectProcessList();

	}

	public Optional<List<orListClass>> loadOrtFinish() { // 撈出本日已完成紀錄  OK
		return orTime_jPAInterface.selectFinishList();
	}
	
	public Optional<orListClass> getOrtListDate(Long id) { // 撈出本日已完成紀錄  OK
		return orTime_jPAInterface.findById(id);
	}

}

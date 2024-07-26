package jpaConnection;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import jpaConnection.orTv_JPAInterface;

import patientObject.patientClass;

@Service
public class orTv_JPAController {
	
	private final orTv_JPAInterface orTv_jpaInterface;
	
	@Autowired
    public orTv_JPAController(orTv_JPAInterface orTv_jpaInterface) {
    	this.orTv_jpaInterface=orTv_jpaInterface;
    	
    }
	
	public String insertPatient(patientClass patient) {
		orTv_jpaInterface.save(patient);
		return "Sucess";
	
	}
	
	public String deletePatient(Long id) {
		orTv_jpaInterface.deleteById(id);
		return "Sucess";

	}
	
	public String getPatient() {  //取得內部所有病人
		String test="";
		List<patientClass> data=orTv_jpaInterface.findAll();
		for(patientClass datas:data ) {
			test=test+datas.getPatient_Name();
			
		}
		return test;

	}
	
}

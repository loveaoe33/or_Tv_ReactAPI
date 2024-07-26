package orClient_Tv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jpaConnection.orTv_JPAController;
import patientObject.patientClass;


@ComponentScan(basePackages = {"jpaConnection"})
@RestController
public class orClient_Tv_Controller implements ErrorController {
    
	private final orTv_JPAController orTv_jpaController;
	
	
	@Autowired
	public orClient_Tv_Controller(orTv_JPAController orTv_jpaController) {
		this.orTv_jpaController=orTv_jpaController;
	}
	
	
	
	    
	
	@CrossOrigin
	@GetMapping("orClient_Tv_Controller/insertPatient") //新增病人 
	public String insertPatient() {
		patientClass data=patientClass.builder().Patient_Number("08001").Patient_Name("測試").Patient_Gender("女").Patient_Statu("手術中").build();
		return orTv_jpaController.insertPatient(data);
	}
	
	@GetMapping("/orClient_Tv_Controller/deletePatient") //刪除病人
	public String deletePatient() {
		return orTv_jpaController.deletePatient((long) 5);
	}
	
	@GetMapping("/orClient_Tv_Controller/printPatient") //撈出病人
	public String printPatient() {
		return orTv_jpaController.getPatient();
	}
	
	
	
	@GetMapping("/") 
	public String test2() {
		return "123";
	}
	
	
	
}

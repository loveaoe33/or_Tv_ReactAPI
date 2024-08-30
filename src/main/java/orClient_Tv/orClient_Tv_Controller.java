package orClient_Tv;

import java.util.List;
import java.util.Optional;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jpaConnection.orTv_JPAController;
import patientObject.orListClass;
import patientObject.orListClassLog;
import patientObject.patientClass;


@ComponentScan(basePackages = {"jpaConnection"})
@RestController
public class orClient_Tv_Controller<Json> implements ErrorController {
    
	private final orTv_JPAController orTv_jpaController;
    private ObjectMapper orMapper=new ObjectMapper();
    Gson orGson=new Gson();

	
	
	@Autowired
	public orClient_Tv_Controller(orTv_JPAController orTv_jpaController) {
		this.orTv_jpaController=orTv_jpaController;
	}
	
	
	
	    
	
	@CrossOrigin
	@GetMapping("orClient_Tv_Controller/insertPatient") //新增病人 
	public String insertPatient() {
		
		patientClass x=new patientClass();
		patientClass data=patientClass.builder().Patient_Number("08001").Patient_Name("測試").Patient_Gender("女").Patient_Status("手術中").build();
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
	
	
	@GetMapping("/orTime_Controller/loadProcessList") //撈出手術紀錄正處理單號 OK
	public <T> T loadProcessList() {
		Optional<List<orListClass>> data=orTv_jpaController.loadOrtProcess();
		return (data.isPresent())?(T) data:(T) "none";
	}
	
	@GetMapping("/orTime_Controller/loadFinishList") //撈出手術紀錄正處理單號 OK
	public <T> T  loadFinishList() {
		Optional<List<orListClass>> data=orTv_jpaController.loadOrtFinish();
		return (data.isPresent())?(T) data:(T) "none";
	}
	
	@GetMapping("/orTime_Controller/getListData") //撈出單號明細 OK
	public <T> T  getListData(@RequestParam String content) {
		JsonObject dataChnge=orGson.fromJson(content, JsonObject.class);
		Long List_Id=Long.parseLong(dataChnge.get("List_Id").getAsString());
		Optional<orListClass> data=orTv_jpaController.getOrtListDate(List_Id);
		return (data.isPresent())?(T) data:(T) "none";
	}
	
	
	
	@PostMapping("/orTime_Controller/postTampData") //新增手術紀錄暫存
	public String postTampDate() {
		return orTv_jpaController.getPatient();
	}
	
	@PostMapping("/orTime_Controller/postOrData") //新增手術紀錄存檔 OK
	public String postOrData(@RequestParam String Conten) throws JsonMappingException, JsonProcessingException {
		
	 
	    orListClass orListclass=orMapper.readValue(Conten, orListClass.class);
		return orTv_jpaController.insertOrtime(orListclass);
	}
	
	
	@PostMapping("/orTime_Controller/editOrData") //編輯已完成手術紀錄  ok
	public String editOrData() {
		return orTv_jpaController.editOrtime();
	}

	
	
	@GetMapping("/") 
	public String test2() {
		return "123";
	}
	
	
	
}

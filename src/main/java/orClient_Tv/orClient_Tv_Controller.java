package orClient_Tv;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jpaConnection.orTv_JPAController;
import patientObject.marqueeClass;
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
	    orMapper.registerModule(new JavaTimeModule());

	}
	
	
	
	    
	
	@CrossOrigin
	@PostMapping("orClient_Tv_Controller/insertPatient") //新增病人 
	public String insertPatient(@RequestBody String content) {
		
		try {
			patientClass data = orMapper.readValue(content, patientClass.class);

			return orTv_jpaController.insertPatient(data);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            System.out.print("insertPatient錯誤"+e.toString());
			return "fail";
		}
	}
	@CrossOrigin
	@PostMapping("/orClient_Tv_Controller/deletePatient") //刪除病人
	public String deletePatient(@RequestBody String content) {
		try {
		JsonNode nodes = orMapper.readTree(content);
		return orTv_jpaController.deletePatient(nodes.get("Patient_Id").asLong());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            System.out.print("deletePatient錯誤"+e.toString());
			return "fail";
		}

		
	
	}
	@CrossOrigin
	@GetMapping("/orClient_Tv_Controller/printPatient") //撈出病人	
	public List<patientClass> printPatient() {
		return orTv_jpaController.getPatient();
	}
	
	@CrossOrigin
	@GetMapping("/orClient_Tv_Controller/printPatientDetail") //撈出病人明細
	public Optional<patientClass> printPatientDetail(@RequestParam String content) throws UnsupportedEncodingException {
		
		try {
		content = URLDecoder.decode(content, "UTF-8");
		JsonNode nodes = orMapper.readTree(content);

		return orTv_jpaController.getPatientDetail(nodes.get("Patient_Id").asLong());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            System.out.print("printPatientDetail錯誤"+e.toString());
			return null;
		}
		
	}
	
	
	@CrossOrigin
	@PostMapping("/orClient_Tv_Controller/updatePatientDetail") //更新病人明細
	public String  updatePatientDetail(@RequestBody String content) {
		
		try {
		patientClass data = orMapper.readValue(content,patientClass.class);
		return orTv_jpaController.updatePatientDetail(data);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            System.out.print("updatePatientDetail錯誤"+e.toString());
			return null;
		}
		
	}

	
	
	
	
	@CrossOrigin
	@PostMapping("orClient_Tv_Controller/insertMarquee") //新增跑馬燈
	public String insertMarquee(@RequestBody String content) {
		
		
		try {
			marqueeClass data = orMapper.readValue(content, marqueeClass.class);
			return orTv_jpaController.insertMarquee(data);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            System.out.print("insertMarquee錯誤"+e.toString());
			return "fail";
		}


	}
	@CrossOrigin
	@PostMapping("/orClient_Tv_Controller/deleteMarquee") //刪除跑馬燈
	public String deleteMarquee(@RequestBody String content) {
		try {
            System.out.print("insertMarquee錯誤"+content);

			JsonNode nodes = orMapper.readTree(content);
			return orTv_jpaController.deleteMarquee(nodes.get("Marquee_Id").asLong());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            System.out.print("deleteMarquee錯誤"+e.toString());

			return "fail";
		}
	}
	@CrossOrigin
	@GetMapping("/orClient_Tv_Controller/printMarquee") //撈出跑馬燈
	public List<marqueeClass> printMarquee() {
		return orTv_jpaController.selectMarquee();
	}
	
	
	
	
	
	
	
	
	
	@CrossOrigin
	@GetMapping("/orTime_Controller/loadOrTimeList") //撈出手術紀錄正處理單號 OK
	public <T> T loadOrTimeProcessList(@RequestParam String content) {
		return (T) orTv_jpaController.loadOrtList();
	}

	@CrossOrigin
	@GetMapping("/orTime_Controller/loadOrTimeListData") //撈出單號明細 OK
	public <T> T  loadOrTimeListData(@RequestParam String content) {
		try {
			JsonNode ListNumber=orMapper.readTree(content);
			String data=orTv_jpaController.getOrtList(ListNumber.get("List_Number").asText());
			return (T) data;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (T) "fail";
		}

	}
	
	
	@CrossOrigin
	@PostMapping("/orTime_Controller/postTempData") //新增手術紀錄暫存
	public String postTampDate(@RequestBody String content)  {
		try {
			System.out.println(content);
			orListClass orList = orMapper.readValue(content, orListClass.class);
			System.out.println(orList.getPatient_Json_String());

			return orTv_jpaController.tempPatient(orList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			System.out.println("Json錯誤"+e);
			e.printStackTrace();
			return "fail";
		}
	}
	
	
	
	@CrossOrigin
	@PostMapping("/orTime_Controller/postSaveTempData") //更新手術紀錄存檔 OK
	public String postOrData(@RequestBody String Conten) throws JsonMappingException, JsonProcessingException {
	    orListClass orListclass=orMapper.readValue(Conten, orListClass.class);
		return orTv_jpaController.tempFinish(orListclass);
	}
	
	@CrossOrigin
	@PostMapping("/orTime_Controller/deleteOrTimeFormData") //刪除手術紀錄
	public String deleteOrTimeFormData(@RequestBody String Conten) throws JsonMappingException, JsonProcessingException {
	    orListClass orListclass=orMapper.readValue(Conten, orListClass.class);
		return orTv_jpaController.deleteTemp(orListclass);
	}
	
	
	
	
	@CrossOrigin
	@PostMapping("/orTime_Controller/editOrData") //編輯已完成手術紀錄  ok
	public String editOrData(@RequestBody String Conten) throws JsonMappingException, JsonProcessingException {
		
	    JsonNode node =orMapper.readTree(Conten);
		System.out.println("文字"+node.get("Edit_Column").asText());

	    orListClass orListclass=orListClass.builder().List_Number(node.get("List_Number").asText()).Patient_Json_String(node.get("PatiendJson").asText()).Edit_Log_Number(node.get("Edit_Log_Number").asText()).build();
	    orListClassLog orListClasslog=orListClassLog.builder().Edit_Log_Number(node.get("Edit_Log_Number").asText()).Edit_Column(node.get("Edit_Column").asText()).Edit_Emp(node.get("Edit_Emp").asText()).Edit_Time(node.get("Edit_Time").asText()).build();
		return orTv_jpaController.editOrtime(orListclass, orListClasslog);
	}

	
	@CrossOrigin
	@GetMapping("/") 
	public String test2() {
		return "123";
	}
	
	
	
}

package patientObject;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "ortime")
public class orListClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Column(name = "Patient_Number")
	public String Patient_Number;
	@Column(name = "Or_Room")
	public String Or_Room;
	@Column(name = "Patient_Json_String")
	public String Patient_Json_String;
	@Column(name = "List_Number")
	public String List_Number;
	@Column(name = "Step_Number")
	public int Step_Number;
	@Column(name = "First_Tag")
	public String First_Tag;
	//Log
	@Column(name = "Edit_Log_Number")
	public String Edit_Log_Number;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	@Column(name = "Insert_Time")
	public LocalDateTime Insert_Time;
	@Column(name = "List_Status")
	public String List_Status;
	@Column(name = "Finish_Time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	public LocalDateTime Finish_Time;

}

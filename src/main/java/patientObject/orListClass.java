package patientObject;

import java.util.Date;

import org.springframework.stereotype.Component;

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
@Table(name = "ortime")
public class orListClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Patient_Number;
	private String Or_Room;
	private String Call_PatientJson;
	private String Arrival_AreaJson;
	private String AnaeStart_DatesJson;
	private String AnaeEnd_DatesJson;
	private String OperPreStart_DateJson;
	private String OperPreEnd_TimesJson;
	private String OperStart_TimesJson;
	private String OperEnd_DatesJson;
	private String List_Number;
	private int Step_Number;
	private String First_Tag;
	private String Edit_Log_Number;
	private Date Inert_Time;
	private String List_Status;
	private String Finish_Time;

}

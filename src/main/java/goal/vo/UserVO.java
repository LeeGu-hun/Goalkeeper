package goal.vo;

import java.io.File;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserVO {
	private int uno;
	private String u_id;
	private String u_name;
	private String u_password;

	private String u_mail;
	private String u_number;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date u_birthdate;
}

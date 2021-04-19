package goal.vo;

import java.io.File;
import java.util.Date;

import lombok.Data;

@Data
public class userVO {
	private int uno;
	private String u_id;
	private String u_name;
	private String u_password;
	private File u_profile;
	private String u_mail;
	private String u_number;
	private char u_gender;
	private Date u_birthdate;
}

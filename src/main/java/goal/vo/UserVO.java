package goal.vo;

import java.io.File;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
@Alias("userVO")
public class UserVO {
	private int uno;
	private String userId;
	private String userName;
	private String userPw;

	private String userMail;
	private String userPhone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date userBirthdate;
}

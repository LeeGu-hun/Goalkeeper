package goal.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class FriendVO {
	private int uno, fno;
	private String f_name;
	private String f_numbrr;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date f_birthdate;
}

package goal.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("friendVO")
public class FriendVO {
	private int uno, fno;
	private String f_name;
	private String f_number;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date f_birthdate;
}

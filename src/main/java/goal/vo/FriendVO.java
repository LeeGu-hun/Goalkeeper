package goal.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("friendVO")
public class FriendVO {
	private int fno, uno, friendNo;
	private String friendName;
	private String friendNumber;
	
	@DateTimeFormat(pattern = "yy/MM/dd")
	private Date friendBirthdate;
}

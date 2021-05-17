package goal.vo;

import java.io.File;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
@Alias("userVO")
public class ChatVO {
	private int uno, friendNo;
	private String userId;
}

package goal.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("myGoalVO")
public class MyGoalVO {
	private int pgno, uno;
	private String privateGoal;
	private String goal;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date deadline;
}

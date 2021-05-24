package goal.vo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("groupGoal")
public class GroupGoalVO {
	private int dno, gno, goal_allcnt;
	private String goal_name, goal_type;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date goal_enddate;
	private int groupMaxData;
}

package goal.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("groupUserGoal")
public class GroupUserGoalVO {
	private String goal_name, userId;
	private int goal_allcnt, data_cnt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date data_regdate;
}

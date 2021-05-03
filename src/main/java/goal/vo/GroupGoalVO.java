package goal.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GroupGoalVO {
	int dno, gno, goal_allcnt;
	String goal_name, goal_type;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date goal_enddate;
}

package goal.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class InputMyDataVO {
	private int pgno;
	private String myData;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date today;
}

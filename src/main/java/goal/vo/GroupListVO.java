package goal.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GroupListVO {
	private int[] gno, uno;
	private String[] g_name, g_intro, g_open, g_cate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date[] g_date, g_goaldate;
	public String isCheck() {
		for(int i=0; i<gno.length; i++) {
			if(g_cate[i].equals("공부")) {
				return "study";
			} else if(g_cate[i].equals("운동")) {
				return "exercise";
			} else if(g_cate[i].equals("야외활동")) {
				return "picnic";
			} else if(g_cate[i].equals("음악")) {
				return "music";
			} else if(g_cate[i].equals("기타")) {
				return "etc";
			}
		}
		return "error";
	}
}

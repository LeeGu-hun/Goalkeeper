package goal.vo;

import lombok.Data;

@Data
public class BoardFileVO {
	   	private String uuid;
	    private int bno;
	    private String fileName;     //저장할 파일
	    private String fileUrl;
}

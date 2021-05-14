package goal.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("userFileVO")
public class UserFileVO {
	private int uno;
	private String userFileName, userFileId, userFilePath;
}

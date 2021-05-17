package goal.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("groupBgiVO")
public class GroupBgiVO {
	private int gno;
	private String fileName, uuid, filePath;
}

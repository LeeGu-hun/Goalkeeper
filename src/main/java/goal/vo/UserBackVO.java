package goal.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("userBackVO")
public class UserBackVO {
	private int uno;
	private String backName, backId, backPath;
}

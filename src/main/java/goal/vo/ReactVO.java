package goal.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("reactVO")
public class ReactVO {
	private String react_name;
	private int bno;
	private String userId;
}

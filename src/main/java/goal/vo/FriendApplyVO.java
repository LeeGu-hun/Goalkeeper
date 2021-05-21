package goal.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("friendApplyVO")
public class FriendApplyVO {
	private int applyUno, receiveUno;
	private String applyId, receiveId;
	private String applyFileCheck;
	private String applyBackCheck;
	private String receiveFileCheck;
	private String receiveBackCheck;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date applyBirthdate, receiveBirthdate, applyDate;
}

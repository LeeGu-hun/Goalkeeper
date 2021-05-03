package goal.vo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("boardVO")
public class BoardVO {
   private int bno;
   private int uno;
   private String b_content;
   private String b_title;
   private String b_cate;
   private String b_group;
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date b_date;
  
}
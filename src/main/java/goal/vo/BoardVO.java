package goal.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
   private int bno;
   private int uno;
   private String b_content;
   private String b_title;
   private String b_cate;
   private String b_group;
   private Date b_date;
}
package goal.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import goal.service.BoardFileService;
import goal.service.BoardService;
import goal.service.CommonService;
import goal.service.GroupService;
import goal.service.ReplyService;
import goal.service.UserService;
import goal.vo.BoardFileVO;
import goal.vo.BoardVO;
import goal.vo.UserVO;

@Service
public class BoardUpload {

	@Autowired
	BoardFileService boardFileService;
	@Autowired
	BoardService boardService;
	
	public void BoardUpload(BoardVO board,List<MultipartFile> files) throws IllegalStateException, IOException {
		
		BoardFileVO boardFileVO = new BoardFileVO();
	
		for(MultipartFile file : files) {
        	String fileUrl = "C:\\uploadfile";
        	String fileName = file.getOriginalFilename(); 
            String uuid = RandomStringUtils.randomAlphanumeric(32);
            String filesave = fileUrl + "/" + uuid + "_" + fileName;
            
            File dest = new File(filesave);
            file.transferTo(dest);
            
            boardFileVO.setUuid(uuid);
            boardFileVO.setBno(board.getBno());
            boardFileVO.setFileName(fileName);
            boardFileVO.setFileUrl(fileUrl);
            boardFileService.fileInsert(boardFileVO);
        }
	}
	
}

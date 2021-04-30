package goal.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import goal.vo.BoardFileVO;

public class BoardUpload {
	public List<BoardFileVO> requestMultiUpload(MultipartHttpServletRequest mtfRequest) {
		List<BoardFileVO> boardFile = null;
		String path ="C:\\board-image";
        File uploadPath = new File(path); 
        if(uploadPath.exists()==false) uploadPath.mkdir();
        
        for(BoardFileVO file : boardFile) {
        	MultipartFile mf = mtfRequest.getFile("file");
        	String originFileName = mf.getOriginalFilename();
            originFileName = originFileName.substring(originFileName.lastIndexOf("\\")+1);
            file.setI_filename(originFileName);
            UUID uuid = UUID.randomUUID();
            originFileName = uuid.toString() + "_" + originFileName;
            
            try {
    			mf.transferTo(new File(uploadPath, originFileName));
    		} catch (IllegalStateException | IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
            file.setUuid(uuid.toString());
            file.setI_path(path);
        }
        return boardFile;
    }
	
}

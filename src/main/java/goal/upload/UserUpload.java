package goal.upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import goal.vo.UserFileVO;

public class UserUpload {
	public UserFileVO profileUpload(MultipartHttpServletRequest request) {		
		UserFileVO userFile = new UserFileVO();
	    MultipartFile mf = request.getFile("file");
	    String path ="C:\\userprofile";
	    File uploadPath = new File(path); 
	    if(uploadPath.exists()==false) uploadPath.mkdir();
	
	    String originFileName = mf.getOriginalFilename();
	    originFileName = originFileName.substring(originFileName.lastIndexOf("\\")+1);
	    userFile.setUserFileName(originFileName);
	    UUID uuid = UUID.randomUUID();
	    originFileName = uuid.toString() + "_" + originFileName;
	    
	    try {
			mf.transferTo(new File(uploadPath, originFileName));
		} catch (IllegalStateException | IOException e1) {
			e1.printStackTrace();
		}
	    userFile.setUserFileId(uuid.toString());
	    userFile.setUserFilePath(path);
	
	    return userFile;
	}
}

package goal.upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import goal.vo.UserFileVO;

public class UserUpload {
	public UserFileVO profileUpload(UserFileVO vo, @RequestPart("files") MultipartFile files) {
		String fileUrl = "C:/profile";
		File uploadPath = new File(fileUrl);
		
		if (uploadPath.exists() == false) {
        	uploadPath.mkdirs();
        }
		
		String fileName = files.getOriginalFilename(); 
		fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
		vo.setUserFileName(fileName);
		UUID uuid = UUID.randomUUID();
        fileName = uuid.toString() + "_" + fileName;
        try {
			files.transferTo(new File(fileUrl, fileName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        vo.setUserFileId(uuid.toString());
        vo.setUserFilePath(fileUrl);
        
        return vo;
	}
}
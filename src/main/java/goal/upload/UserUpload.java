package goal.upload;

import java.io.File;
import java.io.IOException;

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
        String uuid = RandomStringUtils.randomAlphanumeric(32)+"."+"jpg";
        String filePath = fileUrl + "/" + uuid;
        
        File dest = new File(filePath);
        try {
			files.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        vo.setUserFileId(uuid);
        vo.setUserFileName(fileName);
        vo.setUserFilePath(filePath);
        
        return vo;
	}
}
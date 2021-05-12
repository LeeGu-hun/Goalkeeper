package goal.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import goal.vo.GroupFileVO;

public class GroupUpload { 
	public GroupFileVO requestSingleUpload(MultipartHttpServletRequest mtfRequest) {
		GroupFileVO groupFile = new GroupFileVO();
        MultipartFile mf = mtfRequest.getFile("file");
        String path ="C:\\group-image";
        File uploadPath = new File(path); 
        if(uploadPath.exists()==false) uploadPath.mkdir();

        String originFileName = mf.getOriginalFilename();
        originFileName = originFileName.substring(originFileName.lastIndexOf("\\")+1);
        groupFile.setG_filename(originFileName);
        UUID uuid = UUID.randomUUID();
        originFileName = uuid.toString() + "_" + originFileName;
        
        try {
			mf.transferTo(new File(uploadPath, originFileName));
		} catch (IllegalStateException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        groupFile.setG_fid(uuid.toString());
        groupFile.setG_filepath(path);

        return groupFile;
    }
	public void requestMultiUpload(List<MultipartFile> files) {
    }
	public void requestMultdddiUpload(MultipartHttpServletRequest mtfRequest) {
        
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
        String src = mtfRequest.getParameter("src");
        System.out.println("src value : " + src);

        String path = "C:\\group-image";

        for (MultipartFile mf : fileList) {
        	String originFileName = mf.getOriginalFilename();
        	originFileName = originFileName.substring(originFileName.lastIndexOf("\\")+1);

            String safeFile = path + System.currentTimeMillis() + originFileName;
            try {
                mf.transferTo(new File(safeFile));
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	}
}

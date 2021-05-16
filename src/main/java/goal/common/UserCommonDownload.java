package goal.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import goal.util.MediaUtils;

public class UserCommonDownload {
	public ResponseEntity<byte[]> getImageEntity(ResponseEntity<byte[]> entity, MediaUtils mediaUtils, InputStream in, String vo_name, String vo_uuid, String vo_path) throws IOException{
		try {
			String fileName = vo_name;
			String uuid = vo_uuid;
			String uploadPath = vo_path;
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
            MediaType mType = mediaUtils.getMediaType(formatName);
            HttpHeaders headers = new HttpHeaders();
            in = new FileInputStream(uploadPath);
            headers.setContentType(mType);
            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
         } catch (IOException e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
         } finally {
            in.close();
         }
	     return entity;
	}
}

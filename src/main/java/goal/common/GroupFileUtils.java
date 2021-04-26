package goal.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import goal.vo.GroupFileVO;

@Component
public class GroupFileUtils {

	public List<GroupFileVO> parseFileInfo(int gno, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		//비어있을 경우 이 문장에서 끝
		if (ObjectUtils.isEmpty(multipartHttpServletRequest)) {return null;}

		//비어있지 않을 경우 아래부터 시작
		
		//복수의 파일을 담기 위한 컬렉션 선언 (이유 :: t_file 테이블에 추가하기 위함.)
		List<GroupFileVO> fileList = new ArrayList<>();
		
		//파일 업로드 경로 생성
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/" + current.format(format);
		File file = new File(path);
		if (file.exists() == false) file.mkdirs();

		//아래 부터는 복수개의 파일을 하나씩 끄집어 내기 위한 코드
		
		//복수개의 list에 접근하기 위한 iterator선언
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		//newFileName(시분초), originalFileExtension(원래 확장자), contentType(파일의 형식)
		String newFileName, originalFileExtension, contentType;
		
		//업로드된 객체에서 파일 목록을 가져옴. 
		while (iterator.hasNext()) {
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			//파일 목록에서 실제 파일에 개별 접근
			for (MultipartFile multipartFile : list) {
				//실제 파일이 비어 있지 않을 경우
				if (multipartFile.isEmpty() == false) {
					//파일의 타입을 알기 위함.
					contentType = multipartFile.getContentType();
					
					if (ObjectUtils.isEmpty(contentType)) {
						break; //타입이 비어 있을 경우 for반복문 빠져나감.
					} else {
						//이미지 파일일경우만 업로드 됨 jpg, png, gif
						if (contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						} else if (contentType.contains("image/png")) {
							originalFileExtension = ".png";
						} else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						} else {
							break;
						}
					}

					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					GroupFileVO boardFile = new GroupFileVO();
					boardFile.setGno(gno);
					boardFile.setG_filename(multipartFile.getOriginalFilename());
					boardFile.setG_filepath(path + "/" + newFileName);
					fileList.add(boardFile);

					//실제 파일을 위한 객체 선언
					file = new File(path + "/" + newFileName);
					//file 을 실제 경로로 이동시킴
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
}

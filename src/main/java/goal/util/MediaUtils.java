package goal.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {
	private static Map<String, MediaType> mediaTypeMap;
	
	static {
		mediaTypeMap = new HashMap<String, MediaType>();
		mediaTypeMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaTypeMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	public MediaType getMediaType(String fileName) {
		String formatName = getFormatName(fileName);
		return mediaTypeMap.get(formatName);
	}

	private static String getFormatName(String fileName) {
		return fileName.toUpperCase();
	}
}

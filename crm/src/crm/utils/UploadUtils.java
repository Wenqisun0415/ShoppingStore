package crm.utils;

import java.util.UUID;

public class UploadUtils {

	/**
	 * 解决目录下文件名重复问题
	 * @param fileName
	 * @return
	 */
	public static String getUUIDFileName(String fileName) {
		int idx = fileName.lastIndexOf(".");
		String extension = fileName.substring(idx);
		return UUID.randomUUID().toString().replace("-", "")+extension;
	}
	
	/**
	 * 目录分离方法
	 */
	public static String getPath(String uuidFileName) {
		int code1 = uuidFileName.hashCode();
		int dir1 = code1 & 0xf;
		int code2 = code1 >>> 4;
		int dir2 = code2 & 0xf;
		return "/" + dir1 + "/" + dir2;
	}
}

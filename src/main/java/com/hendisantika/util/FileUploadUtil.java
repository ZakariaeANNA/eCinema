package com.hendisantika.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUploadUtil {

	private static final String UPLOAD_DIR = "src\\main\\resources\\static\\photos";
	public static void saveFile(String uploadDir, String string, MultipartFile file) {
		String fileLocation = new File(UPLOAD_DIR+"\\"+uploadDir).getAbsolutePath()+"\\"+string;
		try {
			FileOutputStream output = new FileOutputStream(fileLocation);
			output.write(file.getBytes());
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
}

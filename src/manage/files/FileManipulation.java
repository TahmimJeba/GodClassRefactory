package manage.files;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileManipulation {
	public static final String STOP_WORD_FOLDER_NAME = "Stop Word Directory";
	public static final String STOP_WORD_FILE_NAME = "Stop Words.txt";
	public static final String SOURCE_FOLDER_NAME = "Source Folder";
	
	public FileManipulation(){
		stopWordFileCreation = new StopWordFileCreation();
	}
	
	private StopWordFileCreation stopWordFileCreation;
	
	File sourceFolder = new File(SOURCE_FOLDER_NAME);
	File stopWords = new File(STOP_WORD_FOLDER_NAME);
	
	
	public void createSourceDirectory(){
		try {
			if(!sourceFolder.exists()){
				sourceFolder.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createStopWordDirectory(){
		try {
			if(!stopWords.exists()){
				stopWords.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void manageStopWords(){
		try {
			deleteFilesInStopWordDirectory();
			deleteFilesInSourceDirectory();
			stopWordFileCreation.createFilesInStopWordDirectory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFilesInStopWordDirectory(){
		try {
			FileUtils.cleanDirectory(new File(STOP_WORD_FOLDER_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFilesInSourceDirectory(){
		try {
			FileUtils.cleanDirectory(new File(SOURCE_FOLDER_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

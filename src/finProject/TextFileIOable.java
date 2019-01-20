package finProject;

public interface TextFileIOable {
public void createNewFile(String fileName);
public void writeToNewFile(String fileName, String text);
public void appendToFile(String fileName, String text);
public boolean deleteFile(String fileName);
public boolean copyFile(String fileName);
public boolean copyFile(String fileName, String fileNameOtherFile);
public String readFile(String fileName);
public boolean findAndReplaceFileContent(String fileName, String origText, String
replacementText);
//public String readDelimitedFile(String fileName);
public String readDelimetedFile(String fileName, String delimeter);
}

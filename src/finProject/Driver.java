package finProject;


public class Driver {
public static void main(String[] args) {
TextFileHandler fileHandler = new TextFileHandler();
String theFileName = "myFiles/sampleFile.txt";
// String file2Name = "myFiles/sampleFile_copy.txt";
// fileHandler.copyFile(theFileName, file2Name);
// fileHandler.deleteFile(file2Name);
// fileHandler.createNewFile(theFileName);
// fileHandler.writeToNewFile(theFileName, "How is your day?????");
// fileHandler.appendToFile(theFileName, "yikes!");
// String theContents = fileHandler.readFile(theFileName);
// String theContents = fileHandler.readFileFromFileChooser();
// System.out.println("The content is:\n"+theContents);
fileHandler.recursivelyPrintAllDirAndFileNames("/Users/esofianos1/Documents/ECLIPSE_Files/workspace/Lehman");
}
}

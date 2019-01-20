package finProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;

//import finProject.TextFileIOable;
public class TextFileHandler implements TextFileIOable{
/**
* Create a New EMPTY file
*/
public void createNewFile(String fileName) {
PrintWriter outStream = null;
try{
outStream = new PrintWriter(fileName);
}catch(FileNotFoundException e){
System.out.println("Could not create the file "+fileName);
e.printStackTrace();
}
finally{
if(outStream != null){
outStream.close();
}
System.out.println("All done check your file system for "+ fileName);
}
}
@Override
public void writeToNewFile(String fileName, String text) {
PrintWriter outStream = null;
try{
outStream = new PrintWriter(fileName);
outStream.println(text); //write the text to the file
}catch(FileNotFoundException e){
System.out.println("Could not create the file "+fileName);
e.printStackTrace();
}
finally{
if(outStream != null){
outStream.close();
}
System.out.println("All done check your file system for "+ fileName);
}
}
@Override
public void appendToFile(String fileName, String text) {
PrintWriter outStream = null;
try{
// outStream = new PrintWriter(fileName);//replace this line
outStream = new PrintWriter(new FileOutputStream(fileName, true));//true means append to end of file
outStream.println(text); //write the text to the file
outStream.flush();
}catch(FileNotFoundException e){
System.out.println("Could not create the file "+fileName);
e.printStackTrace();
}
finally{
if(outStream != null){
outStream.close();
}
System.out.println("All done check your file system for "+ fileName);
}
}
@Override
public boolean deleteFile(String fileName) {
File file = new File(fileName);
if(file.exists()){
if(file.delete()){
System.out.println(" The file "+fileName+ " was deleted " );
return true;
}
}
else{
System.out.println(" The file "+fileName+ " was NOT deleted or does not exist");
}
return false;
}
/**
* @param String file Name including path to file
* @return String representing file content
*/
@Override
public String readFile(String fileName) {
Scanner inStream = null;
String fileContents = "";
try {
File theFileObject = new File(fileName) ;
inStream = new Scanner(theFileObject);
while( inStream.hasNextLine() ){ //use the Scanner Object's hasNextLine method to get Lines as long as the file has
fileContents += inStream.nextLine() +"\n";//get a line from the file append to fileContent String
}
} catch (FileNotFoundException e) {
e.printStackTrace();
}
finally{
if(inStream !=null){
inStream.close();
}
}
return fileContents;
}
@Override
public boolean copyFile(String fileNameOrig) {
// TODO Auto-generated method stub
return false;
}
@Override
public boolean copyFile(String fileNameOrig, String fileNameCopy) {
Scanner inStream = null;
PrintWriter outStream = null;
try{
inStream = new Scanner(new File( fileNameOrig ));
outStream = new PrintWriter(new File(fileNameCopy));
// outStream = new PrintWriter(new FileOutputStream( new File(fileNameCopy) , true));
while(inStream.hasNextLine()){
String lineIn = inStream.nextLine();
// outStream.println( lineIn);
char c1 = lineIn.charAt(0);
String lineOut = Character.toUpperCase(c1) + lineIn.substring(1);
outStream.println( lineOut);
}
if(inStream != null){
inStream.close();
}
if(outStream !=null){
outStream.close();
}
return true;//success in copying
}
catch(FileNotFoundException e){
System.out.println("YIKES! problem copying");
e.printStackTrace();
}
finally{
if(inStream != null){
inStream.close();
}
if(outStream !=null){
outStream.close();
}
System.out.println("all done go check your file system");
}
return false;//failure in copying
}
@Override
public String readDelimetedFile(String fileName, String delimeter) {
Scanner inStream = null;
String token = "";
String fileContent = "";
try{
File theFile = new File(fileName);
if((theFile.exists()) && theFile.canRead()){
inStream = new Scanner(theFile);
// inStream.useDelimiter(delimeter);
// while(inStream.hasNext()){
while(inStream.hasNextLine()){
// token = inStream.next();
// System.out.println(token);//just so we can see it on the console as it runs
// fileContent += token+"\n";//put each token on separate line
String lineIn = inStream.nextLine();
String [] tokens = lineIn.split(delimeter);
for(int i=0; i<tokens.length; i++){
fileContent += tokens[i] +"\n";
}
}
}
}catch(FileNotFoundException e){
e.printStackTrace();
}
finally{
if(inStream != null){
inStream.close();
}
System.out.println("ALL DONE WITH readDelimetedFile ");
}
//cover on Tuesday 12/4
return fileContent;
}
@Override
public boolean findAndReplaceFileContent(String fileName, String textOrig,
String textReplacement) {
return false;
}
public String readFileFromFileChooser(){
JFileChooser jfc = new JFileChooser();
int yesNo = jfc.showDialog(null, null);
if(yesNo == JFileChooser.APPROVE_OPTION){
File file = jfc.getSelectedFile();
if(file.isFile()){
return readFile( file.getAbsolutePath() );
}
}
return "";
}
public void recursivelyPrintAllDirAndFileNames(String fileName){
File fileObj = new File(fileName);
if(fileObj.exists()){
if(fileObj.isFile()){
System.out.println("File: "+ fileName);//base case
}
else{
System.out.println("Dir: " + fileName);
File[] files = fileObj.listFiles();
for(int i=0; i<files.length; i++){
recursivelyPrintAllDirAndFileNames(files[i].getAbsolutePath());
}
}
}
}

}

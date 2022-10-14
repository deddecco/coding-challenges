package personal.y22.m08;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileCopier {

     public static String[] opCodes = new String[7];

     public static void setOpCodes() {
          opCodes[0] = "-t";
          opCodes[1] = "-u";
          opCodes[2] = "-l";
          opCodes[3] = "-n";
          opCodes[4] = "-c";
          opCodes[5] = "-s";
          opCodes[6] = "";
     }


     public static void main(String[] args) throws IOException {

          setOpCodes();

          if (args[0].equals("copy")) {
               if (args.length == 3) {
                    makeCopy(args);
               } else if (operationsToBeDoneIncludeThisOne("-n", args)) {
                    makeCopyWithLineNums(args);
               } else if (operationsToBeDoneIncludeThisOne("-l", args)) {
                    makeCopyAllLowerCase(args);
               } else if (operationsToBeDoneIncludeThisOne("-u", args)) {
                    makeCopyAllUpperCase(args);
               } else if (operationsToBeDoneIncludeThisOne("-c", args)) {
                    makeFirstLetterCapital(args);
               } else if (operationsToBeDoneIncludeThisOne("-s", args)) {
                    makeCopyReplaceSpace(args);
               } else if (operationsToBeDoneIncludeThisOne("-t", args)) {
                    makeCopyTruncate64(args);
               }
          } else {
               warnUserAboutBadOperation();
          }
     }

     // usage message
     private static void warnUserAboutBadOperation() {
          System.out.println("The first argument must be the word \"copy\"");
          System.out.println("In addition, any permutation of the following is a valid command:");
          System.out.println("No modifiers: copies the file exactly");
          System.out.println("-c: capitalizes the first character of the line");
          System.out.println("-l: all characters become lowercase");
          System.out.println("-u: all characters become uppercase");
          System.out.println("-n: adds line numbers");
          System.out.println("-s: replaces spaces in the file with \"_\"");
          System.out.println("-t: truncates each line to 64 characters");
          System.exit(-1);
     }

     // strict copy
     // copy f1 f2
     private static void copy(BufferedReader reader, FileWriter writer) throws IOException {
          String currentLine;
          while ((currentLine = reader.readLine()) != null) {
               writer.write(currentLine);
               writer.write("\n");
          }
     }

     private static void makeCopy(String[] args) throws IOException {
          // if args has 3 elements, they will be [copy instruction, source file, destination file]

          File source = getSourceFile(args);
          File destination = getDestFile(args);
          BufferedReader reader = new BufferedReader(new FileReader(source));
          FileWriter writer = new FileWriter(destination);
          copy(reader, writer);
          writer.close();
     }

     // line numbers
     // copy -n f1 f2
     private static void copyWithLineNums(BufferedReader reader, FileWriter writer) throws IOException {
          String currentLine;
          int i = 1;
          while ((currentLine = reader.readLine()) != null) {
               writer.write("Line " + i + ": " + currentLine);
               writer.write("\n");
               i++;
          }
     }

     private static void makeCopyWithLineNums(String[] args) throws IOException {

          File source = getSourceFile(args);
          File destination = getDestFile(args);
          BufferedReader reader = new BufferedReader(new FileReader(source));
          FileWriter writer = new FileWriter(destination);
          copyWithLineNums(reader, writer);
          writer.close();
     }

     // truncate each line to <=64 characters
     // copy -t f1 f2
     private static void truncateTo64(BufferedReader reader, FileWriter writer) throws IOException {
          String currentLine;

          while ((currentLine = reader.readLine()) != null) {
               writer.write(currentLine.substring(0, Math.min(currentLine.length(), 64)));
               writer.write("\n");
          }
     }

     public static void makeCopyTruncate64(String[] args) throws IOException {

          File source = getSourceFile(args);
          File destination = getDestFile(args);
          BufferedReader reader = new BufferedReader(new FileReader(source));
          FileWriter writer = new FileWriter(destination);
          truncateTo64(reader, writer);
          writer.close();
     }


     // spaces become underscores everywhere
     // copy -s f1 f2
     public static void replaceSpaceUnderscore(BufferedReader reader, FileWriter writer) throws IOException {
          String currentLine;

          while ((currentLine = reader.readLine()) != null) {
               StringBuilder adjustedLine = new StringBuilder();
               for (int i = 0; i < currentLine.length(); i++) {
                    if (currentLine.charAt(i) != ' ') {
                         adjustedLine.append(currentLine.charAt(i));
                    } else {
                         adjustedLine.append("_");
                    }
               }
               writer.write(adjustedLine.toString());
               writer.write("\n");
          }
     }

     public static void makeCopyReplaceSpace(String[] args) throws IOException {
          File source = getSourceFile(args);
          BufferedReader reader = new BufferedReader(new FileReader(source));
          File destination = getDestFile(args);
          FileWriter writer = new FileWriter(destination);
          replaceSpaceUnderscore(reader, writer);
          writer.close();
     }

     // all letters become uppercase
     // copy -u f1 f2
     private static void copyWithAllUpperCase(BufferedReader reader, FileWriter writer) throws IOException {
          String currentLine;
          while ((currentLine = reader.readLine()) != null) {
               writer.write(currentLine.toUpperCase());
               writer.write("\n");
          }
     }

     private static void makeCopyAllUpperCase(String[] args) throws IOException {
          File source = getSourceFile(args);
          BufferedReader reader = new BufferedReader(new FileReader(source));
          File destination = getDestFile(args);
          FileWriter writer = new FileWriter(destination);
          copyWithAllUpperCase(reader, writer);
          writer.close();
     }

     // all letters become lowercase
     // copy -l f1 f2
     private static void copyWithAllLowerCase(BufferedReader reader, FileWriter writer) throws IOException {
          String currentLine;
          while ((currentLine = reader.readLine()) != null) {
               writer.write(currentLine.toLowerCase());
               writer.write("\n");
          }
     }

     private static void makeCopyAllLowerCase(String[] args) throws IOException {
          File source = getSourceFile(args);
          BufferedReader reader = new BufferedReader(new FileReader(source));
          File destination = getDestFile(args);
          FileWriter writer = new FileWriter(destination);
          copyWithAllLowerCase(reader, writer);
          writer.close();
     }

     private static final Pattern FIRST_LETTER = Pattern.compile("([a-zA-Z])");

     // capitalize only the first letter of the line
     // copy -c f1 f2
     private static void capitalizeFirstLetterOfLine(BufferedReader reader, FileWriter writer) throws IOException {
          String currLine;
          while ((currLine = reader.readLine()) != null) {
               Matcher matcher = FIRST_LETTER.matcher(currLine);
               String modLine = matcher.replaceFirst(matchResult -> matchResult.group(1).toUpperCase());
               writer.write(modLine);
               writer.write("\n");
          }
     }

     private static void makeFirstLetterCapital(String[] args) throws IOException {
          if (new File(args[2]).equals(getSourceFile(args))) {
               File source = getSourceFile(args);
               BufferedReader reader = new BufferedReader(new FileReader(source));
               File destination = getDestFile(args);
               FileWriter writer = new FileWriter(destination);
               capitalizeFirstLetterOfLine(reader, writer);
               writer.close();
          }
     }

     // get the source file
     private static File getSourceFile(String[] args) {
          if (new File(args[args.length - 2]).exists()) {
               return new File(args[args.length - 2]);
          } else {
               System.out.println("The file " + args[args.length - 2] + " does not exist");
               warnUserAboutBadOperation();
               return null;
          }
     }

     // get the destination file
     private static File getDestFile(String[] args) {
          return new File(args[args.length - 1]);
     }

     public static boolean operationsToBeDoneIncludeThisOne(String opCode, String[] args) {
          for (int i = 1; i < args.length - 2; i++) {
               if (opCode.equals(args[i])) {
                    return true;
               }
          }
          return false;
     }
}
package personal.y22.m09;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class GenerateHTML {

     static class BeginTag {
          public void printBeginTag() {
               System.out.println("""
                         <!DOCTYPE html>
                         <html lang="en">""");
          }
     }

     static class BoldTag {
          public void makeTextBold(String text) {
               System.out.println(getBoldBegin() + text + getBoldEnd());
          }

          public String getBoldBegin() {
               return "<b>";
          }

          public String getBoldEnd() {
               return "</b>";
          }
     }

     static class ItalicTag {
          public void makeTextItalic(String text) {
               System.out.println(getItalicBegin() + text + getItalicEnd());
          }

          public String getItalicBegin() {
               return "<i>";
          }

          public String getItalicEnd() {
               return "</i>";
          }
     }

     static class StrongTag {
          public void makeTextStrong(String text) {
               System.out.println(getStrongBegin() + text + getStrongEnd());
          }

          public String getStrongBegin() {
               return "<strong>";
          }

          public String getStrongEnd() {
               return "</strong>";
          }
     }

     static class BodyTag {
          public void printBodyBeginTag() {
               System.out.println("<body>");
          }

          public void printBodyEndTag() {
               System.out.println("</body>");
          }

     }

     static class MainTag {
          public void printMainBeginTag() {
               System.out.println("<main>");
          }

          public void printMainEndTag() {
               System.out.println("</main>");
          }
     }

     static class UnorderedList {
          ArrayList<String> UL = new ArrayList<>();
          String ULOpenTag = "<ul>";
          String ULCloseTag = "</ul>";

          public void createUL() {
               UL.add(ULOpenTag);
               UL.add(ULCloseTag);
          }

          public void printUL() {
               for (String ulElem : UL) {
                    System.out.println(ulElem);
               }
          }

          public void addToUL(String ULItem) {
               String openTag = "<li>";
               String closeTag = "</li>";
               String element = openTag + "\n" + ULItem + "\n" + closeTag;
               int indexToAdd = UL.indexOf(ULOpenTag) + 1;

               UL.add(indexToAdd, element);
          }
     }

     static class OrderedList {
          ArrayList<String> OL = new ArrayList<>();
          String OLOpenTag = "<ol>";
          String OLCloseTag = "</ol>";

          public void createUL() {
               OL.add(OLOpenTag);
               OL.add(OLCloseTag);
          }

          public void printOL() {
               for (String ulElem : OL) {
                    System.out.println(ulElem);
               }
          }

          public void addToUL(String OLItem) {
               String openTag = "<li>";
               String closeTag = "</li>";
               String element = openTag + "\n" + OLItem + "\n" + closeTag;
               int indexToAdd = OL.indexOf(OLCloseTag) - 1;

               OL.add(indexToAdd, element);
          }
     }

     static class Figure {
          public void printImgTag(String src, String alt, String title, int width, int height) {
               System.out.println("<img src = " + src + " alt= " + alt + " title=" + title + "width= " + width + "height = " + height + ">");
          }

          public void printBeginFigureTag() {
               System.out.println("<figure>");
          }

          public void printEndFigureTag() {
               System.out.println("</figure");
          }

          public void printCaptionInfo(String caption) {
               printBeginFigCaption();
               System.out.println(caption);
               printEndFigCaption();
          }

          public void printBeginFigCaption() {
               System.out.println("<figure>");
          }

          public void printEndFigCaption() {
               System.out.println("</figure>");
          }

          public void printAllInfo(String src, String alt, String title, int width, int height, String caption) {
               printBeginFigureTag();
               printImgTag(src, alt, title, width, height);
               printBeginFigCaption();
               printCaptionInfo(caption);
               printEndFigCaption();
               printEndFigureTag();
          }
     }

     static class AnchorTag {
          public String printBeginAnchor() {
               String beginAnchor = "<a href =";
               System.out.print(beginAnchor);
               return beginAnchor;
          }

          public String printEndAnchor() {
               String endAnchor = "</a> ";
               System.out.print(endAnchor);
               return endAnchor;
          }

          public void printAnchorPhone(int phoneNum, String formattedPhone) {
               System.out.println(printBeginAnchor() + "tel:" + phoneNum + ">" + formattedPhone + printEndAnchor());
          }

          public void printAnchorSection(String sectionName, String formattedSection) {
               System.out.println(printBeginAnchor() + "#" + sectionName + "> " + formattedSection + printEndAnchor());
          }

          public void printAnchorEmail(String emailAddress, String emailCoverMessage) {
               System.out.println(printBeginAnchor() + "mailto:" + emailAddress + "> " + emailCoverMessage + printEndAnchor());
          }
     }

     static class ParagraphTag {

          String beginParaTag = "<p>";
          String endParaTag = "</p>";

          public void printPara(String text) {
               String paragraph = getBeginPara() + text + getEndPara();
               System.out.println(paragraph);
          }

          public String getBeginPara() {
               return beginParaTag;
          }

          public String getEndPara() {
               return endParaTag;
          }
     }

     static class Heading {
          public void printHeadingText(int headingLevel, String headingText) {
               if (headingLevel != 0) {
                    System.out.println("<h" + headingLevel + ">" + headingText + "</h" + headingLevel + ">");
               } else {
                    System.out.println("<strong>" + headingText + "</strong>");
               }
          }

          public void printHeadingBeginTag(int headingLevel) {
               System.out.println("<h" + headingLevel + ">");
          }

          public void printHeadingEndTag(int headingLevel) {
               System.out.println("</h" + headingLevel + ">");
          }
     }

     static class Break {
          public void printBreak() {
               System.out.println("<br>");
          }
     }

     static class HorizontalRule {
          public void printHorizontalRule() {
               System.out.println("<hr>");
          }
     }

     static class Table {
          public void printTableBeginTag() {
               System.out.println("<table>");
          }

          public void printTableEndTag() {
               System.out.println("</table>");
          }

          public String printTableRowBeginTag() {
               return "<tr>";
          }

          public String printTableRowEndTag() {
               return "</tr>";
          }

          public void printTableRow(String word, int frequency) {
               System.out.println(printTableDataBeginTag() + word + printTableDataEndTag() + printTableDataBeginTag() + frequency + printTableDataEndTag());
          }

          public String printTableDataBeginTag() {
               return "<td>";
          }

          public String printTableDataEndTag() {
               return "</td>";
          }

          public String getTableHeaderBeginTag() {
               return "<th>";
          }

          public String getTableHeaderEndTag() {
               return "</th>";
          }

          public void printTableDataRow(String dataElem) {
               System.out.println(printTableDataBeginTag() + dataElem + printTableDataEndTag());
          }

          public void printTableDataRow(String dataElem, String dataFormatTag) {
               System.out.print(printTableDataBeginTag() + "<" + dataFormatTag + ">" + dataElem + "</" + dataFormatTag + ">" + printTableDataEndTag());
          }

          public void printTableHeader(String headingText) {
               System.out.println(getTableHeaderBeginTag() + headingText + getTableHeaderEndTag());
          }
     }

     public static void main(String[] args) throws IOException {

          String filePath = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\BibleFrequencyAnalysis.txt";
          BufferedReader reader = new BufferedReader(new FileReader(filePath));
          String[] tableHeader = reader.readLine().split("\t");
          Table table = new Table();

          String currLine;
          int i = 0;
          int numberOfWords = 150;

          String[][] rows = new String[numberOfWords][2];

          while (((currLine = reader.readLine()) != null) && i < numberOfWords) {

               String[] dataRow = currLine.split("\t");
               rows[i] = dataRow;
               i++;
          }

          Comparator<String[]> comparator = Comparator.comparingInt(o -> Integer.parseInt(o[1]));

          Arrays.sort(rows, comparator);
          Collections.reverse(Arrays.asList(rows));
          SupremeCourtOrdering sco = new SupremeCourtOrdering();
          String[][] reordered = sco.reorder(rows);
          Heading heading = new Heading();
          int gapSize = reordered.length / 12;

          for (int idx = 0; idx < reordered.length; idx++) {

               if (idx == reordered.length / 2 - 1) {
                    heading.printHeadingText(0, reordered[idx][0]);
               }
               generateHeadingsPlusGap(reordered, heading, gapSize, idx);
               generateHeadingsMinusGap(reordered, heading, gapSize, idx);

               if ((idx >= reordered.length / 2 + 5 * gapSize) && (idx < reordered.length - 1)) {
                    heading.printHeadingText(6, reordered[idx][0]);
               }
               if (idx > 0 && idx < gapSize) {
                    heading.printHeadingText(6, reordered[idx][0]);
               }
          }
     }

     private static void generateHeadingsMinusGap(String[][] reordered, Heading hdg, int gapSize, int index) {
          int hdgUpperBound;
          int hdgLowerBound;

          for (int j = 0; j < 5; j++) {
               hdgUpperBound = reordered.length / 2 - 1 - (gapSize * j);
               hdgLowerBound = reordered.length / 2 - 1 - (gapSize * (j + 1));

               if ((index < hdgUpperBound) && (index > hdgLowerBound)) {
                    hdg.printHeadingText(j + 1, reordered[index][0]);
               }
          }
     }

     private static void generateHeadingsPlusGap(String[][] reordered, Heading hdg, int gapSize, int index) {
          int hdgUpperBound;
          int hdgLowerBound;

          for (int j = 0; j < 5; j++) {
               hdgLowerBound = reordered.length / 2 + (gapSize * j);
               hdgUpperBound = reordered.length / 2 - 1 + (gapSize * (j + 1));

               if ((index > hdgLowerBound) && (index < hdgUpperBound)) {
                    hdg.printHeadingText(j + 1, reordered[index][0]);
               }
          }
     }
}
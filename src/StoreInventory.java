package personal.y22.m09;

import java.util.List;

public class StoreInventory {
     public static void main(String[] args) throws Exception {
          String config1 = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\products.txt";
          String config2 = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\departments.txt";
          String config3 = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m09\\ProductDepartmentMatching.txt";

          Table products = new Table();
          products.loadTable("PRODUCT", config1);
          products.sortRecords("NAME");

          Table departments = new Table();
          departments.loadTable("DEPARTMENT", config2);

          RelationshipTable matching = new RelationshipTable();
          matching.loadRelTable("MATCHING", config3, products, departments);

          generateReports(departments, matching);

          products.saveToDisk();
          departments.saveToDisk();
          matching.saveToDisk();
     }

     private static void generateReports(Table departments, RelationshipTable matching) {
          List<String[]> matches = departments.findAll();

          for (String[] record : matches) {
               String val = departments.getVal(record, "NAME");
               matches = matching.findAllMatches("DEPARTMENT_NAME", val);
               System.out.println("Everything in the " + val + " department: ");
               matching.printHeader();

               int maxHeadingLength = matching.relTableHeadings[0].length();
               for (int i = 0; i < matching.relTableHeadings.length; i++) {
                    maxHeadingLength = Math.max(maxHeadingLength, matching.relTableHeadings[i].length());
               }
               for (String[] match : matches) {
                    int longestLength = match[0].length();
                    for (String s : match) {
                         longestLength = Math.max(longestLength, s.length());
                    }
                    for (int i = 0; i < match.length; i++) {
                         String s = match[i];
                         System.out.printf("[%" + matching.relTableHeadings[i].length() + "." + s.length() + "s]", s);
                    }
                    System.out.println();
               }

               System.out.println();
               System.out.println();
               System.out.println();
          }
     }
}
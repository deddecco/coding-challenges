package personal.y22.m09;

import java.io.IOException;

public class StoreInventory {
    public static void main(String[] args) throws IOException {
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

        products.saveToDisk();
        matching.saveToDisk();
        departments.saveToDisk();
    }
}
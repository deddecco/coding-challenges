package personal.y22.m11;

import java.util.ArrayList;
import java.util.List;

public class NewPermuter {

     public List<List<String>> permute(List<String> setup, List<String> list) {

          // eventually will return this list
          List<List<String>> finalList = new ArrayList<>();
          // start this out as exact copy of setup
          List<String> newSetup = new ArrayList<>(setup);


          // if the list of missing numbers is empty,
          // then the setup list is complete
          // add it to the list to be returned
          if (list.isEmpty()) {
               finalList.add(setup);
          }
          // if the list of missing numbers is of size 1,
          // then make a copy of the setup list,
          // find the null value in the copy,
          // and put the missing number in that position
          else if (list.size() == 1) {
               int replaceThisIndex = setup.indexOf(null);
               newSetup.set(replaceThisIndex, list.get(0));
               finalList.add(newSetup);
          }
          // if there are any other number of missing values
          else {
               // for every element in the list of missing values,
               for (int i = 0; i < list.size(); i++) {
                    String element = list.get(i);
                    List<String> newList = new ArrayList<>();
                    // make a copy of that list without a given element
                    for (String s : list) {
                         if (!s.equals(element)) {
                              newList.add(s);
                         }
                    }
                    // find the first null
                    int substituteHere = setup.indexOf(null);

                    // put the given element in that position
                    newSetup.set(substituteHere, element);
                    // permute the copy of the setup list and the list of missing numbers
                    List<List<String>> temp = permute(newSetup, newList);
                    // add all the resulting permutations to the final list
                    finalList.addAll(temp);
               }
          }

          return finalList;
     }

     public static void main(String[] args) {
          List<String> setup = new ArrayList<>();


          List<String> list = new ArrayList<>();


          NewPermuter np = new NewPermuter();
          List<List<String>> options = np.permute(setup, list);
          System.out.println(options);
          System.out.println(options.size());
     }
}

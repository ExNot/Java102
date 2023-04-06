/*
package CollectionMapInterface;

import java.util.*;

public class Fixture {
    public static void main(String[] args) {

        Random rand = new Random();
        int randNum = 0;

        Scanner scan = new Scanner(System.in);


        ArrayList<String> teamsArr = new ArrayList<>();

        ArrayList<Integer> randArr = new ArrayList<>();

        System.out.println("Please enter number of teams");
        int teamNum = scan.nextInt();


        for (int i = 2; i<= teamNum+1 ; i++){
            System.out.println("Please enter the team " + (i-1) + ". team: ");
            teamsArr.add(scan.next());
        }


        if (teamNum %2 == 1)
        {
            teamsArr.add("Bay");
            teamNum++;
        }


        Collections.shuffle(teamsArr);


        TreeSet<String> tempList = new TreeSet<>();
        tempList.addAll(teamsArr);



        for (int i = 0; i< teamNum-1; i++){

            for (int j =1; j){

            }

        }


















        */
/*        for (int j = 0; j< teamNum-2; j++){

            for (int i = j; i< teamNum-1; i +=1){

                System.out.println(teamsArr.get(i) + " vs " + teamsArr.get(i+1));

            }

        }


        System.out.println(teamsArr);*//*



        */
/*do {
            randNum = rand.nextInt(teamNum);
            randArr.add(randNum);
            int rand1 = 0;

            if (randArr.size() %2 == 0){
                rand1 = randNum;
                continue;
            }

            else {

                System.out.println(teamsArr.get(rand1) + " vs " + teamsArr.get(randNum));

            }



        }

        while (randArr.size() <= teamNum || !randArr.contains(randNum));*//*






    }
}
*/

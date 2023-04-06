package CollectionMapInterface;

import java.util.*;

public class asd {
    public static void main(String[] args) {

        System.out.print("Enter team count: ");
        Scanner scanner = new Scanner(System.in);
        int teamCount = scanner.nextInt();
        LinkedList<String> teams = new LinkedList<>();
        for (int i = 0; i < teamCount; i++) {
            System.out.print("Enter team name: ");
            teams.add(scanner.next());
        }

        for (String i : teams) {
            System.out.print(i + " ");
        }
        LinkedList<String> homeT = new LinkedList<>();
        LinkedList<String> awayT = new LinkedList<>();
        LinkedList<String> cloneList = new LinkedList<>();
        int counter = 1;
        boolean afterFirstWeek = false;
        boolean t = false;
        for (int i = 0; i < teamCount - 1; i++) {
            String homeTeam;
            String awayTeam;
            int matchCount = 0;
            String weeksMatches = "";

            cloneList = (LinkedList<String>) teams.clone(); // refresh teams each week

            while (matchCount < teamCount / 2) {
                boolean isContinue = true;
                if (afterFirstWeek) {
                    Collections.shuffle(cloneList);
                    homeTeam = cloneList.peek();
                    cloneList.removeFirst();
                    awayTeam = cloneList.peek();
                    cloneList.removeFirst();

                    for (int j = 0; j < homeT.size(); j++) {
                        if ((homeTeam.equals(homeT.get(j)) && awayTeam.equals(awayT.get(j))) || (awayTeam.equals(awayT.get(j)) && homeTeam.equals(homeT.get(j)))) {
                            isContinue = false;
                        }
                        if (homeTeam.equals(awayT.get(j)) && awayTeam.equals(homeT.get(j))) {
                            isContinue = false;
                        }
                    }

                    if (isContinue) {
                        homeT.add(homeTeam);
                        awayT.add(awayTeam);
                        weeksMatches += homeTeam + " vs " + awayTeam + "\n";
                        matchCount++;
                        counter++;
                    } else {
                        Collections.shuffle(cloneList);
                        cloneList.add(homeTeam);
                        cloneList.add(awayTeam);
                        Collections.shuffle(cloneList);
                    }
                } else {
                    Collections.shuffle(cloneList);
                    homeTeam = cloneList.peek();
                    cloneList.removeFirst();
                    awayTeam = cloneList.peek();
                    cloneList.removeFirst();

                    homeT.add(homeTeam);
                    awayT.add(awayTeam);
                    weeksMatches += homeTeam + " vs " + awayTeam + "\n";
                    matchCount++;
                    counter++;

                }
                if (!afterFirstWeek && matchCount == teamCount / 2)
                    afterFirstWeek = true;
            }
            System.out.println();
            System.out.println(i + 1 + ". week matches");
            System.out.println(weeksMatches);
        }

        for (int i = 0; i < counter; i++) {

        }

    }
}


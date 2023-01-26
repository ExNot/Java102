package AdvantureGame;

import java.util.Scanner;

public class Game {

    private Scanner scan = new Scanner(System.in);
    public void start(){
        System.out.println("Welcome to Advanture Game!");
        System.out.println("Please enter a name: ");
        String playerName = scan.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getName() + " prepare for battle!");
        System.out.println("This is gonna be a long journey!");
        System.out.println();
        System.out.println("Please choose a character");
        player.selectChar();

        Location location = null;
        while (true){
            player.printInfo();
            System.out.println();
            System.out.println("************ Locations ************");
            System.out.println();
            System.out.println("Location 1 : Safe House");
            System.out.println("Location 2 : Store");
            System.out.println("Location 3 : Award: <Food> Cave -> there is dangerous!");
            System.out.println("Location 4 : Award: <Firewood> Forest -> there is more dangerous!");
            System.out.println("Location 5 : Award: <Water> River -> You can die!");
            System.out.println("Location 6 : Award: <Random!> Mine -> Secret bounties!");
            System.out.println("0 - For Exit");

            System.out.println("Please select the location: ");
            int selectLoc = scan.nextInt();
            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new safeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if (!player.getInventory().isFood()){
                        location = new Cave(player);
                    }
                    else {
                        System.out.println("You've already destroyed them!");
                    }
                    break;
                case 4:
                    if (!player.getInventory().isFireWood()){
                        location = new Forest(player);
                    }else {
                        System.out.println("You've already destroyed them!");
                    }

                    break;
                case 5:
                    if (!player.getInventory().isWater()){
                        location = new River(player);
                    }
                    else {
                        System.out.println("You've already destroyed them!");
                    }
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Please enter valid location!");


            }


            if (location == null){
                System.out.println("The game is over goodbye freak!");
                break;


            }

            if (!location.onLocation()){
                System.out.println("GAME OVER");
                break;
            }
            if (player.getInventory().isWater() && player.getInventory().isFood() && player.getInventory().isFireWood()){
                System.out.println("You destroyed all of them and runaway the island good luck for rest of your life fighter!");
                break;
            }

        }

    }

}

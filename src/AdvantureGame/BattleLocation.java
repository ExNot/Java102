package AdvantureGame;

import javax.tools.Tool;
import java.util.Random;
import java.util.Scanner;

public abstract class BattleLocation extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    private Scanner scann = new Scanner(System.in);



    public BattleLocation(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        if (this.name.equals("Cave") && this.getPlayer().getInventory().isFood()){
            return true;
        }
        if (this.name.equals("Forest") && this.getPlayer().getInventory().isFireWood()){
            return true;
        }
        if (this.name.equals("River") && this.getPlayer().getInventory().isWater()){
            return true;
        }
        int obsNum = this.randomObstacleNum();
        System.out.println("You are in " + this.getName());
        System.out.println("Becarefull! " + obsNum + " " + this.getObstacle().getName()+ " is living in here!");
        System.out.println("<F>ight or <R>un");

        String selectCase = scann.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("F" ) && combat(obsNum)){
                locAwards();
                System.out.println(this.getName() + " You defeated all of them!");
                return true;
        }

        if (this.getPlayer().getHealth()<=0){
            System.out.println("You dead!");
            return false;
        }

        return true;
    }
    public boolean combat(int obsNumber){

        for (int i=0; i<obsNumber; i++){
            if (this.getName().equals("Mine")){
                Random rand = new Random();
                int randomDamage = rand.nextInt(4);
                randomDamage +=3;
                this.getObstacle().setDamage(randomDamage);
            }
            this.getObstacle().setHealth(this.obstacle.getDefaultHealt());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth()>0 && this.getObstacle().getHealth()>0){
                System.out.println("<A>ttack or <R>un: ");
                String selectCombat = scan.next().toUpperCase();
                if (selectCombat.equals("A")){
                    Random rand = new Random();
                    int rando = rand.nextInt(100);
                    rando++;

                if (rando>=50){
                    System.out.println("You Hitted");
                    this.getObstacle().setHealth(obstacle.getHealth()-this.getPlayer().getTotalDamage());
                    afterHit();

                    if (this.getObstacle().getHealth() >0){
                        System.out.println();
                        System.out.println("Obstacle hitted you!");
                        int obstacleDmg = obstacle.getDamage()-this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDmg<0) obstacleDmg = 0;
                        this.getPlayer().setHealth(this.getPlayer().getHealth()-obstacleDmg);
                        afterHit();
                    }
                }

                if (rando<50){
                    if (this.getObstacle().getHealth() >0){
                        System.out.println();
                        System.out.println("Obstacle hitted you!");
                        int obstacleDmg = obstacle.getDamage()-this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDmg<0) obstacleDmg = 0;
                        this.getPlayer().setHealth(this.getPlayer().getHealth()-obstacleDmg);
                        afterHit();
                    }
                    if (this.getPlayer().getHealth()>0){
                        System.out.println("You Hitted");
                        this.getObstacle().setHealth(obstacle.getHealth()-this.getPlayer().getTotalDamage());
                        afterHit();
                    }


                }





                }else {
                    return false;
                }

            }

            if (this.getObstacle().getHealth()<this.getPlayer().getHealth()){
                if (this.getObstacle().getName().equals("Sneak")){
                    System.out.println("You defeated sneak!");
                    Random random = new Random();
                    int percent = random.nextInt(100);
                    percent+=1;
                    if (percent>0 && percent<=15){
                        int inPercent = random.nextInt(100);
                        inPercent+=1;

                        // Earn Weapons

                        if (inPercent>0 && inPercent<=20){
                            if (!this.getPlayer().getInventory().getWeapon().getName().equals("Shotgun")){
                                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(3));
                                System.out.println("You earned a Shotgun! Your new damage is: " + this.getPlayer().getTotalDamage());
                            }
                            else {
                                System.out.println("You earned but already have a shotgun and you haven't got a pack!");
                            }


                        }


                        else if (inPercent>20 && inPercent<50){
                            if (this.getPlayer().getInventory().getWeapon().getDamage()<Weapon.getWeaponObjById(2).getDamage()){
                                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(2));
                                System.out.println("You've earnd a Sword! Your new damage is: " + this.getPlayer().getTotalDamage());

                            }
                            else{
                                System.out.println("You earned but your gun is better and you haven't got a pack!");
                            }

                        }


                        else if (inPercent>=50 && inPercent<100) {
                            if (this.getPlayer().getInventory().getWeapon().getName().equals("Fist")){

                                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(1));
                                System.out.println("You've earnd a Pistol! Your new damage is: " + this.getPlayer().getTotalDamage());
                            }
                            System.out.println("You earned but your gun is better and you haven't got a pack!");
                        }


                    }
                    if (percent>15 && percent <= 30){
                        int inPercent = random.nextInt(100);
                        inPercent+=1;

                        if (inPercent>0 && inPercent<=20){
                            if (!this.getPlayer().getInventory().getArmor().getName().equals("Heavy")){
                                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(3));
                                System.out.println("You've earned Heavy armor! your new block is: " + this.getPlayer().getTotalBlock());
                            }
                        }
                        else if (inPercent>20 && inPercent<=50){
                               if (this.getPlayer().getInventory().getArmor().getBlock()<Armor.getArmorObjById(2).getBlock()){
                                   this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(2));
                                   System.out.println("You've earned Medium Armor! Your new block is: " + this.getPlayer().getTotalBlock());
                               }
                               else {
                                   System.out.println("You earned but your armor is better and you haven't got a pack!");
                               }
                        }
                        else if(inPercent>50){
                            if (this.getPlayer().getInventory().getArmor().getName().equals("Clothes")){
                                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(1));
                                System.out.println("You've earned Light Armor! Your new block is: " + this.getPlayer().getTotalBlock());
                            }
                        }
                    }


                    if (percent>30 && percent<=55){
                        int inPercent = random.nextInt(100);
                        inPercent+=1;
                        if (inPercent<= 20){
                            this.getPlayer().setMoney(this.getPlayer().getMoney()+10);
                            System.out.println("You've earned 10 gold! Your new balance is: " + this.getPlayer().getMoney());
                        }

                        if (inPercent>20 && inPercent<=50){
                            this.getPlayer().setMoney(this.getPlayer().getMoney()+5);
                            System.out.println("You've earned 5 gold! Your new balance is: " + this.getPlayer().getMoney());
                        }

                        if (inPercent>50){
                            this.getPlayer().setMoney(this.getPlayer().getMoney()+1);
                            System.out.println("You've earned 1 gold! Your new balance is: " + this.getPlayer().getMoney());
                        }
                    }

                    if (percent>55){
                        System.out.println("You didin't get anything!");
                    }
                }



                else {
                    System.out.println("You defeated the enemy!");
                    System.out.println("You " + this.getObstacle().getAward() + " gold earned!");
                    this.getPlayer().setMoney(this.getPlayer().getMoney()+this.obstacle.getAward());
                    System.out.println("Your balance: " + this.getPlayer().getMoney());
                }

            }else {
                return false;
            }

        }



        return true;

    }
    public void afterHit(){
        System.out.println("Your health: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() +"'s health: " + this.getObstacle().getHealth());
        System.out.println("----------------------");
    }
    public void playerStats(){
        System.out.println(this.getPlayer().getName()+ "'s Stats:");
        System.out.println("----------------------------");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Armor: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Block: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Money: " + this.getPlayer().getMoney());
        System.out.println();

    }
    public void obstacleStats(int i){
        System.out.println(i+1 + ". " + this.getObstacle().getName()+ "'s Stats: ");
        System.out.println("----------------------------");
        System.out.println("Health: " + this.getObstacle().getHealth());
        System.out.println("Damage: " + this.getObstacle().getDamage());
        System.out.println("Health: " + this.getObstacle().getHealth());
        System.out.println("Award: " + this.getObstacle().getAward());
    }

    public void locAwards(){              //Loc Awards**********************
        if (this.getName().equals("Cave")){
            this.getPlayer().getInventory().setFood(true);
        }
        if (this.getName().equals("Forest")){
            this.getPlayer().getInventory().setFireWood(true);
        }
        if (this.getName().equals("River")){
            this.getPlayer().getInventory().setWater(true);
        }

    }


    public int randomObstacleNum(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle())+1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }


}

package AdvantureGame;

public class ToolStore extends normalLocation{
    public ToolStore(Player player) {
        super(player, "Store");

    }

    @Override
    public boolean onLocation() {
        System.out.println("--------Welcome to store!--------");
        boolean showMenu = true;
        while (showMenu){
            System.out.println("1- Weapons");
            System.out.println("2- Armors");
            System.out.println("3- Exit");
            int selectCase = Location.scan.nextInt();
            while (selectCase<1 || selectCase > 3){
                System.out.print("Invalid entery, please enter again!");
                selectCase = Location.scan.nextInt();
            }
            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("GoodBye");
                    showMenu = false;
                    break;

            }
        }
        return true;
    }
    public void printWeapon(){
        System.out.println("---------- Weapons ----------");
        for (Weapon w: Weapon.weapons()){
            System.out.println(w. getId() +  "-" + w.getName() + "<Money: " + w.getPrice() + ", Damage: " + w.getDamage()+ ">");
        }
        System.out.println("0 - For exit!");
    }

    public void buyWeapon(){
        System.out.print("Select Weapon!");
        int selectWeaponID = scan.nextInt();
        while (selectWeaponID <0 || selectWeaponID > Weapon.weapons().length){
            System.out.print("Invalid entery, please enter again!");
            selectWeaponID = Location.scan.nextInt();
        }
        if (selectWeaponID != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponID);
            if (selectedWeapon != null){
                if (selectedWeapon.getPrice()> this.getPlayer().getMoney()){
                    System.out.println("Insufficient Balance!");
                }
                else {
                    System.out.println(selectedWeapon.getName()+ " Buyed");
                    int balance = this.getPlayer().getMoney()- selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Your balance: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }

    }
    public void printArmor(){
        System.out.println("---------- Armors ----------");
        for (Armor a: Armor.armors()){
            System.out.println(a.getId() + "- " + a.getName() + "<Price : " + a.getPrice()+ "," + " Block: " + a.getBlock() + ">");
        }
        System.out.println("0 - For exit!");
    }
    public void buyArmor(){
        System.out.print("Select Armor!");
        int selectArmorID = scan.nextInt();
        while (selectArmorID <0 || selectArmorID > Armor.armors().length){
            System.out.print("Invalid entery, please enter again!");
            selectArmorID = Location.scan.nextInt();
        }
        if (selectArmorID != 0){
            Armor selectedArmor = Armor.getArmorObjById(selectArmorID);
            if (selectedArmor != null){
                if (selectedArmor.getPrice()> this.getPlayer().getMoney()){
                    System.out.println("Insufficient Balance!");
                }else {
                    System.out.println(selectedArmor.getName() + " Buyed!");
                    int balance = this.getPlayer().getMoney()-selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Your balance: " + this.getPlayer().getMoney());
                }
            }

        }
    }
}

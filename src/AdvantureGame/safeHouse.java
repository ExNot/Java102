package AdvantureGame;

public class safeHouse extends normalLocation{
    public safeHouse(Player player) {
        super(player, "Safe House");
    }
    @Override
    public boolean onLocation() {
        System.out.println("You are in safe house");
        System.out.println("You refreshed health!");
        this.getPlayer().setHealth(this.getPlayer().getDefaultHealth());
        return true;
    }
}

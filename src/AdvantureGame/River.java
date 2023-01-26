package AdvantureGame;

public class River extends BattleLocation{
    private boolean water;

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public River(Player player) {
        super(player, "River", new Bear(), "Water",2);

    }
}

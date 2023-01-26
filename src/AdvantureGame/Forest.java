package AdvantureGame;

public class Forest extends BattleLocation{
    private boolean fireWood;

    public boolean isFireWood() {
        return fireWood;
    }

    public void setFireWood(boolean fireWood) {
        this.fireWood = fireWood;
    }

    public Forest(Player player) {
        super(player, "Forest", new Vampire(), "Firewood",3);

    }
}

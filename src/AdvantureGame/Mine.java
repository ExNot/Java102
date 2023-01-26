package AdvantureGame;

import java.util.Random;

public class Mine extends BattleLocation{

    public Mine(Player player) {
        super(player, "Mine",new Sneak(),"None",5);
    }
}

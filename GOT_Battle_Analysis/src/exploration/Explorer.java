package exploration;

import java.util.ArrayList;
import models.Battle;
import util.Parser;

public class Explorer {

    public static void main(String[] args) {

        String format = "%-35s %-10s %-10s %-10s\n";
        System.out.printf(format, "Battle Name", "Attackers", "Defenders", "Total");
        System.out.println();

        // parse dataset and get all battles
        ArrayList<Battle> battles = new Parser().parseDataset();

        // iterate each battle
        for (Battle battle : battles) {

            //getting battle name
            String battleName = battle.getName();

            //getting attackers (might contain null values)
            Integer attackers = battle.getAttacker_size();

            // String representation of the attacker numbers, n/a means data not available
            String attackersStr = attackers == null ? "n/a" : Integer.toString(attackers);

            //getting defenders (might contain null values)
            Integer defenders = battle.getDefender_size();

            // String representation of the attacker numbers, n/a means data not available
            String defendersStr = attackers == null ? "n/a" : Integer.toString(attackers);

            // String representation of the total of participants
            String total = (attackers == null || defenders == null) ? "n/a" : Integer.toString(attackers + defenders);

            format = "%-35s %-10s %-10s %-10s\n";
            System.out.printf(format, battleName, attackersStr, defendersStr, total);

        }

    }

}

package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import models.Battle;

/**
 *
 * @author eduardo alapisco
 *
 */
public class Parser {

    /* Este metodo lee el contenido del dataset y devuelve una lista de objetos 
       de tipo Battle ( data model ) 
     */
    public ArrayList<Battle> parseDataset() {
        
        ArrayList<Battle> battles = new ArrayList<>();

        // Getting data ser from data package
        InputStream inputstream = getClass().getResourceAsStream("/data/5kings_battles_v1.csv");
        Reader fileReader = new InputStreamReader(inputstream);

        // read dataset line by line
        try (BufferedReader br = new BufferedReader(fileReader)) {

            //ignore first line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {

                Battle battle = new Battle();

                // parse the contents of each line
                String[] values = parseCSVLine(line);

                //Construct the battle object 
                battle.setName(values[0]);
                battle.setYear(Integer.parseInt(values[1]));
                battle.setBattle_number(Integer.parseInt(values[2]));

                battle.setAttacker_king(values[3]);

                battle.setDefender_king(values[4]);

                // there might be up to 4 attackers
                String[] attackers = new String[4];
                attackers[0] = values[5];
                attackers[1] = values[6];
                attackers[2] = values[7];
                attackers[3] = values[8];

                // there might be up to 4 defenders
                String[] defenders = new String[4];
                defenders[0] = values[9];
                defenders[1] = values[10];
                defenders[2] = values[11];
                defenders[3] = values[12];

                // setting attacker outcome
                switch (values[13]) {

                    case "win":
                        battle.setAttacker_outcome(Battle.AttackerOutcome.win);

                        break;

                    case "loss":
                        battle.setAttacker_outcome(Battle.AttackerOutcome.loss);

                        break;

                    case "draw":
                        battle.setAttacker_outcome(Battle.AttackerOutcome.draw);

                        break;

                }

                // setting attacker outcome
                switch (values[14]) {

                    case "pitched battle":
                        battle.setBattle_type(Battle.BattleType.pitched_battle);

                        break;

                    case "ambush":
                        battle.setBattle_type(Battle.BattleType.ambush);

                        break;

                    case "siege":
                        battle.setBattle_type(Battle.BattleType.siege);

                        break;

                    case "razing":
                        battle.setBattle_type(Battle.BattleType.razing);

                        break;

                }

                battle.setMajor_death(values[15].equals("1"));
                battle.setMajor_capture(values[16].equals("1"));

                // Attacker size and defender size might have blank values
                Integer attacker_size = values[17].isEmpty() ? null : Integer.parseInt(values[17]);
                battle.setAttacker_size(attacker_size);

                Integer defender_size = values[18].isEmpty() ? null : Integer.parseInt(values[18]);
                battle.setDefender_size(defender_size);

                // attacker commander field can have comma separated values 
                // and empty spaces between them
                String[] attacker_commander = values[19].split(",");
                for (int i = 0; i < attacker_commander.length; i++) {
                    attacker_commander[i] = attacker_commander[i].trim();
                }

                battle.setAttacker_commander(attacker_commander);

                // defender commander field can have comma separated values 
                // and empty spaces between them
                String[] defender_commander = values[20].split(",");
                for (int i = 0; i < defender_commander.length; i++) {
                    defender_commander[i] = defender_commander[i].trim();
                }

                battle.setDefender_commander(defender_commander);
                battle.setSummer(values[21].equals("1"));
                battle.setLocation(values[22]);

                // setting attacker outcome
                switch (values[23]) {

                    case "Beyond the Wall":
                        battle.setRegion(Battle.Region.Beyond_the_Wall);

                        break;

                    case "The North":
                        battle.setRegion(Battle.Region.The_North);

                        break;

                    case "The Iron Islands":
                        battle.setRegion(Battle.Region.The_Iron_Islands);

                        break;

                    case "The Riverlands":
                        battle.setRegion(Battle.Region.The_Riverlands);

                        break;
                    case "The Vale of Arryn":
                        battle.setRegion(Battle.Region.The_Vale_of_Arryn);

                        break;

                    case "The Westerlands":
                        battle.setRegion(Battle.Region.The_Westerlands);

                        break;

                    case "The Crownlands":
                        battle.setRegion(Battle.Region.The_Crownlands);

                        break;

                    case "The Reach":
                        battle.setRegion(Battle.Region.The_Reach);

                        break;
                    case "The Stormlands":
                        battle.setRegion(Battle.Region.The_Stormlands);

                        break;
                    case "Dorne":
                        battle.setRegion(Battle.Region.Dorne);

                        break;

                }

                battle.setNote(values[24]);
                battles.add(battle);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return battles;
    }

    /* Este metodo lee una linea del archivo csv y devuelve los valores 
       encontrados.
       Incluye soporte para valores separados por comas que se encuentran 
       dentro de campos con comillas dobles y que se consideran uno solo.
     */
    public String[] parseCSVLine(String line) {

        boolean ignoreComma = false;
        ArrayList<String> values = new ArrayList();
        String value = "";

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                ignoreComma = !ignoreComma;
            }

            if ((c == ',' && !ignoreComma) || i == line.length() - 1) {
                values.add(value);
                value = "";

            } else if (c != '"') {

                value += c;
            }

        }

        if (values.size() == 24) {
            values.add("");
        }

        return values.toArray(new String[values.size()]);
    }

}

package models;


public class Battle {

    public enum AttackerOutcome {
        win, draw, loss
    }

    public enum BattleType {
        pitched_battle, ambush, siege, razing
    }

    public enum Region {
        Beyond_the_Wall, The_North, The_Iron_Islands,
        The_Riverlands, The_Vale_of_Arryn, The_Westerlands, The_Crownlands, The_Reach, The_Stormlands, Dorne
    }

    String name;
    int year;
    int battle_number;
    String attacker_king;
    String defender_king;
    String[] attackers;
    String[] defenders;
    AttackerOutcome attacker_outcome;
    BattleType battle_type;
    boolean major_death;
    boolean major_capture;
    Integer attacker_size;
    Integer defender_size;
    String[] attacker_commander;
    String[] defender_commander;
    boolean summer;
    String location;
    Region region;
    String note;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBattle_number() {
        return battle_number;
    }

    public void setBattle_number(int battle_number) {
        this.battle_number = battle_number;
    }

    public String getAttacker_king() {
        return attacker_king;
    }

    public void setAttacker_king(String attacker_king) {
        this.attacker_king = attacker_king;
    }

    public String getDefender_king() {
        return defender_king;
    }

    public void setDefender_king(String defender_king) {
        this.defender_king = defender_king;
    }

    public String[] getAttackers() {
        return attackers;
    }

    public void setAttackers(String[] attackers) {
        this.attackers = attackers;
    }

    public String[] getDefenders() {
        return defenders;
    }

    public void setDefenders(String[] defenders) {
        this.defenders = defenders;
    }

    public AttackerOutcome getAttacker_outcome() {
        return attacker_outcome;
    }

    public void setAttacker_outcome(AttackerOutcome attacker_outcome) {
        this.attacker_outcome = attacker_outcome;
    }

    public BattleType getBattle_type() {
        return battle_type;
    }

    public void setBattle_type(BattleType battle_type) {
        this.battle_type = battle_type;
    }

    public boolean isMajor_death() {
        return major_death;
    }

    public void setMajor_death(boolean major_death) {
        this.major_death = major_death;
    }

    public boolean isMajor_capture() {
        return major_capture;
    }

    public void setMajor_capture(boolean major_capture) {
        this.major_capture = major_capture;
    }

    public Integer getAttacker_size() {
        return attacker_size;
    }

    public void setAttacker_size(Integer attacker_size) {
        this.attacker_size = attacker_size;
    }

    public Integer getDefender_size() {
        return defender_size;
    }

    public void setDefender_size(Integer defender_size) {
        this.defender_size = defender_size;
    }

    public String[] getAttacker_commander() {
        return attacker_commander;
    }

    public void setAttacker_commander(String[] attacker_commander) {
        this.attacker_commander = attacker_commander;
    }

    public String[] getDefender_commander() {
        return defender_commander;
    }

    public void setDefender_commander(String[] defender_commander) {
        this.defender_commander = defender_commander;
    }

    public boolean isSummer() {
        return summer;
    }

    public void setSummer(boolean summer) {
        this.summer = summer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

   

}

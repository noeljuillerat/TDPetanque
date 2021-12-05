package ParametreEquipe;

import java.util.HashMap;
import java.util.Map;

import Interfaces.ConcoursParties;

public class Concours8Equipes implements ConcoursParties{
    public Map<String, int[]> roundOne() {
        Map<String, int[]> round = new Concours6Equipes().roundOne();
        round.put("4", new int[]{7, 8});
        return round;
    }
    public Map<String, int[]> roundTwo() {
        Map<String, int[]> round = new HashMap<>();
        round.put("1", new int[]{1, 3});
        round.put("2", new int[]{2, 4});
        round.put("3", new int[]{5, 7});
        round.put("4", new int[]{6, 8});
        return round;
    }
    public Map<String, int[]> roundThree() {
        Map<String, int[]> round = new HashMap<>();
        round.put("1", new int[]{1, 4});
        round.put("2", new int[]{2, 3});
        round.put("3", new int[]{5, 8});
        round.put("4", new int[]{6, 7});
        return round;
    }
    public Map<String, int[]> roundFour() {
        Map<String, int[]> round = new HashMap<>();
        round.put("1", new int[]{1, 5});
        round.put("2", new int[]{2, 8});
        round.put("3", new int[]{3, 7});
        round.put("4", new int[]{4, 6});
        return round;
    }
}

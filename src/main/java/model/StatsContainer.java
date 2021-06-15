package model;

public class StatsContainer {

    private static final Stats STATS = new Stats();

    public static Stats getStatistics() {
        return STATS;
    }
}

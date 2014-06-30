package core.simulator.core;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 24. 6. 2014 17:57
 */
public class TimeStamp implements Comparable<TimeStamp> {

    private int simulationTime;

    public TimeStamp(int simulationTime) {
        this.simulationTime = simulationTime;
    }

    @Override
    public int compareTo(TimeStamp o) {
        return new Integer(simulationTime).compareTo(o.simulationTime);
    }

    @Override
    public String toString() {
        return "TimeStamp{" +
                "simulationTime=" + simulationTime +
                '}';
    }

    public TimeStamp add(int timeSpan) {
        return new TimeStamp(simulationTime + timeSpan);
    }
}

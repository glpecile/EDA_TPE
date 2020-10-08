package pathfinder;

import model.SubwayStop;

public class Test {
    public static void main(String[] args) {
        SubwayStop subwayStop = new SubwayStop(12.0, 21.0, "H");
        System.out.println(subwayStop.getTransferPenalty());
    }
}

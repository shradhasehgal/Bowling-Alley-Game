import javax.swing.*;
import java.util.HashMap;
import java.util.Vector;

public class LaneViewBallLabel {

    public LaneViewBallLabel(LaneEvent le, Vector bowlers, JLabel[][] ballLabel, int k){
        for (int i = 0; i < 21; i++) {
            if (((int[]) ((HashMap) le.getScore())
                    .get(bowlers.get(k)))[i]
                    != -1)
                if (((int[]) ((HashMap) le.getScore())
                        .get(bowlers.get(k)))[i]
                        == 10
                        && (i % 2 == 0 || i == 19))
                    ballLabel[k][i].setText("X");
                else if (
                        i > 0
                                && ((int[]) ((HashMap) le.getScore())
                                .get(bowlers.get(k)))[i]
                                + ((int[]) ((HashMap) le.getScore())
                                .get(bowlers.get(k)))[i
                                - 1]
                                == 10
                                && i % 2 == 1)
                    ballLabel[k][i].setText("/");
                else if ( ((int[])((HashMap) le.getScore()).get(bowlers.get(k)))[i] == -2 ){

                    ballLabel[k][i].setText("F");
                } else
                    ballLabel[k][i].setText(
                            (Integer.valueOf(((int[]) ((HashMap) le.getScore())
                                    .get(bowlers.get(k)))[i]))
                                    .toString());
        }

    }
}

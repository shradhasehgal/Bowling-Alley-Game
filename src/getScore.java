public class getScore {

    private int curScore[];
    private int cumulScores[];
    private boolean done;

    public getScore(int[] CurPlayerScore, int index) {
        cumulScores = new int[10];
        curScore = CurPlayerScore;
        int current = index - 2;


        for (int i = 0; i != current+2; i++)
        {
            done = false;
            CheckOne(i, current);
            if( i < current && i%2 == 0 && curScore[i] == 10  && i < 18){
                handleStrike(i);
                if(!done) break;
            }

            else {
                handleNormal(i);
                if (i / 2 == 9) {
                    if (i == 18) {
                        cumulScores[9] += cumulScores[8];
                    }
                    cumulScores[9] += curScore[i];

                } else if (i / 2 == 10) {
                    cumulScores[9] += curScore[i];

                }
            }
        }
    }

    public void CheckOne(int i, int current){

        if( i%2 == 1 && curScore[i - 1] + curScore[i] == 10 && i < current - 1 && i < 19){
            cumulScores[(i/2)] += curScore[i+1] + curScore[i];
            done = true;
        }
    }

    public void handleStrike(int i)
    {
        if( curScore[i+4]!=-1 || (curScore[i + 2] != -1 && (curScore[i+3]!=-1))) {
            done = true;
            cumulScores[i / 2] += 10;
            if (curScore[i + 1] != -1) {
                cumulScores[i / 2] += curScore[i + 1] + cumulScores[(i / 2) - 1];
                if (curScore[i + 2] != -1) {
                    cumulScores[(i / 2)] += curScore[i + 2];

                } else {
                    if (curScore[i + 3] != -2) {
                        cumulScores[(i / 2)] += curScore[i + 3];
                    }
                }
            } else {
                if (i / 2 > 0) {
                    cumulScores[i / 2] += curScore[i + 2] + cumulScores[(i / 2) - 1];
                } else {
                    cumulScores[i / 2] += curScore[i + 2];
                }
                if (curScore[i + 3] != -1 && curScore[i + 3] != -2) {
                    cumulScores[(i / 2)] += curScore[i + 3];
                } else {
                    cumulScores[(i / 2)] += curScore[i + 4];
                }
            }
        }
    }

    public void handleNormal(int i){
        if( i%2 == 0 && i < 18){
            if ( i/2 == 0 ) {
                //First frame, first ball.  Set his cumul score to the first ball
                if(curScore[i] != -2){
                    cumulScores[i/2] += curScore[i];
                }
            } else if (i/2 != 9){
                //add his last frame's cumul to this ball, make it this frame's cumul.
                if(curScore[i] != -2){
                    cumulScores[i/2] += cumulScores[i/2 - 1] + curScore[i];
                } else {
                    cumulScores[i/2] += cumulScores[i/2 - 1];
                }
            }
        } else if (i < 18){
            if(curScore[i] != -1){
                cumulScores[i/2] += curScore[i];

            }
        }
    }

    public int[] getScores() {
        return cumulScores;
    }

}

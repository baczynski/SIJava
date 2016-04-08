/**
 * Created by Konrad on 08.04.2016.
 */
public class NQueenProblem {

    Variable[][] v;

    public NQueenProblem(int n) {
        v = new Variable[n][n];

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v.length; j++) {
                v[i][j] = new Variable(i, j);
            }
        }
    }

}

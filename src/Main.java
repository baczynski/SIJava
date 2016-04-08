/**
 * Created by Konrad on 08.04.2016.
 */
public class Main {

    public static void main(String [] args){
        int nProblem=4;

        BacktrackingNQueenProblem backtrackingNQueenProblem = new BacktrackingNQueenProblem(nProblem,new MostConstraintVariable(nProblem));

        backtrackingNQueenProblem.algorithm();
    }
}

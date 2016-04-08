import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 08.04.2016.
 */
public class BacktrackingNQueenProblem {
    NQueenProblem nQueenProblem;
    private List<Integer> rowsUsed;
    private List<Integer> columnsUsed;
    private List<Variable> used;
    private NextVariableHeuristic nextVariableHeuristic;
    private boolean foundAll;
    private int visitedNodes;

    public BacktrackingNQueenProblem(int n, NextVariableHeuristic nextVariableHeuristic) {
        nQueenProblem = new NQueenProblem(n);
        rowsUsed = new ArrayList<>();
        columnsUsed = new ArrayList<>();
        used = new ArrayList<>();
        this.nextVariableHeuristic = nextVariableHeuristic;
        foundAll=false;
        visitedNodes=0;

    }

    public void algorithm() {

        while(!foundAll) {
            while (used.size() < nQueenProblem.v.length && !foundAll) {
                try {

                    Variable variable = nextVariableHeuristic.getNext();
                    nQueenProblem.v[variable.i][variable.j].queen = true;
                    if (rowsUsed.contains(variable.i) || columnsUsed.contains(variable.j) || isOnDiagonal(variable.i, variable.j)) {
                        nQueenProblem.v[variable.i][variable.j].queen = false;
                    } else {
                        rowsUsed.add(variable.i);
                        columnsUsed.add(variable.j);
                        used.add(variable);
                        visitedNodes++;
                    }
                } catch (IndexOutOfBoundsException e) {
                    popLast();
                }
            }


            if(!foundAll) {
                printToFile();
                foundOneOfSolution();
            }
        }
        System.out.println(visitedNodes);
    }

    private boolean isOnDiagonal(int y, int x) {

        boolean found = false;
        int n = nQueenProblem.v.length;
        for (int i = y - 1, j = x - 1; i >= 0 && j >= 0 && !found; i--, j--) {
            found = found(i, j);
        }
        for (int i = y - 1, j = x + 1; i >= 0 && j < n && !found; i--, j++) {
            found = found(i, j);
        }
        for (int i = y + 1, j = x - 1; i < n && j >= 0 && !found; i++, j--) {
            found = found(i, j);
        }
        for (int i = y + 1, j = x + 1; i < n && j < n && !found; i++, j++) {
            found = found(i, j);
        }
        return found;
    }

    private boolean found(int i, int j) {
        return nQueenProblem.v[i][j].queen;
    }

    private void printToFile() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < nQueenProblem.v.length; i++) {
            for (int j = 0; j < nQueenProblem.v.length; j++) {
                if (nQueenProblem.v[i][j].queen) {
                    s.append(1);
                } else {
                    s.append(0);
                }
                s.append(' ');
            }
            s.append("\n");
        }
        s.append("\n\n");
        try {
            Writer writer = new BufferedWriter(new FileWriter("backtracking_raport.txt",true));
            writer.write(s.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void popLast(){

        Variable last = nextVariableHeuristic.getLast();
        if (used.get(0).i != last.i || used.get(0).j !=last.j) {
            Variable previousVariable = used.get(used.size() - 1);
            nextVariableHeuristic.goTo(previousVariable.i, previousVariable.j);

            nQueenProblem.v[previousVariable.i][previousVariable.j].queen = false;
            rowsUsed.remove(new Integer(previousVariable.i));
            columnsUsed.remove(new Integer(previousVariable.j));
            used.remove(used.size() - 1);
        } else {
            foundAll = true;
        }

    }
    private void foundOneOfSolution(){
        Variable previousVariable = used.get(used.size() - 1);
        nQueenProblem.v[previousVariable.i][previousVariable.j].queen = false;
        rowsUsed.remove(new Integer(previousVariable.i));
        columnsUsed.remove(new Integer(previousVariable.j));
        used.remove(used.size() - 1);
        popLast();
    }
}

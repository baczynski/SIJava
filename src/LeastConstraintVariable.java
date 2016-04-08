/**
 * Created by Konrad on 08.04.2016.
 */
public class LeastConstraintVariable extends NextVariableHeuristic {

    public LeastConstraintVariable(int n){
        super(n);
    }

    @Override
    protected boolean condition(int existedConstraints,int addedConstraints){
        return existedConstraints<addedConstraints;
    }
}

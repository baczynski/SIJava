/**
 * Created by Konrad on 08.04.2016.
 */
public class MostConstraintVariable extends NextVariableHeuristic {

    public MostConstraintVariable(int n){
        super(n);
    }

    @Override
    protected boolean condition(int existedConstraints,int addedConstraints){
        return existedConstraints>=addedConstraints;
    }


}

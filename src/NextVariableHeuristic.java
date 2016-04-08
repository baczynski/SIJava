import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 08.04.2016.
 */
public abstract class NextVariableHeuristic {

    private List<Variable> queue;
    private int n;
    private int it;
    private Variable last;

    NextVariableHeuristic(int n){
        queue = new ArrayList<>();
        this.n=n;
        it=-1;
        fillQueue();
        setLast();
    }

    public Variable getNext(){
        return queue.get(++it);
    }

    public Variable getPrevious(){
        return queue.get(--it);
    }
    private Variable getCurrent(){
        return queue.get(it);
    }

    public void goTo(int i,int j){
        Variable v = getPrevious();
        while(v.i != i || v.j !=j){
            v =getPrevious();
        }
    }

    private void setLast(){
        boolean found=false;
        for(int j=queue.size()-1;j>=0 && !found;j--){
            if(queue.get(j).i==0){
                found = true;
                last=queue.get(j);
            }
        }
    }

    public Variable getLast(){

        return last;
    }

    private void fillQueue(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int constraints = countConstraints(i,j);
                insertToQueue(i,j,constraints);
            }
        }
    }

    private int countConstraints(int y,int x){
        int counter =0;
        for(int i=y-1,j=x-1;i>=0 && j>=0;i--,j--){
            counter++;
        }
        for(int i=y-1,j=x+1;i>=0 && j<n;i--,j++){
            counter++;
        }
        for(int i=y+1,j=x-1;i<n && j>=0;i++,j--){
            counter++;
        }
        for(int i=y+1,j=x+1;i<n && j<n ;i++,j++){
            counter++;
        }
        return counter;
    }
    private void insertToQueue(int y, int x, int constraints) {
        int i =0;
        while(i<queue.size() && condition(queue.get(i).constraints,constraints)){
            i++;
        }
        Variable v = new Variable(y,x);
        v.constraints = constraints;
        queue.add(i,v);
    }
    protected abstract boolean condition(int existedConstraints,int addedConstraints);
}

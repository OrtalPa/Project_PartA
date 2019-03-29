package algorithms.search;

import jdk.internal.dynalink.beans.StaticClass;

import java.util.Objects;

/**
 * An abstract class that represents a problem situation of a particular problem that we wish to solve
 */
public abstract class AState {

    protected  AState parent; //the parent of the state
    protected boolean m_bVisited; //true if the state was visited before
    protected int cost;

    public AState()
    {
        m_bVisited = false;
        parent = null;
    }

    // A function that compares two
    public abstract int compareTo(AState obj);

    public void setVisited(boolean visited)
    {
        m_bVisited = visited;
    }

    @Override
    public abstract boolean equals(Object o);

    public AState getParent() {
        return this.parent;
    }

    public abstract void setParent(AState parent);

    /**
     * A function which puts the cost of the point. For diagonal cost is one
     * @param cost
     */
    public void setCost(int cost) {
        //You can only put 1 or 0
        if(cost == 10 || cost == 15 || cost==0){
            this.cost = cost;
        }
    }

    public int getCost() {
        return cost;
    }
}

package algorithms.search;



import jdk.internal.dynalink.beans.StaticClass;

import java.io.Serializable;
import java.util.Objects;

/**
 * An abstract class that represents a problem situation of a particular problem that we wish to solve
 */
//ADDD
public abstract class AState implements Serializable, Comparable<AState>{

    protected  AState parent; //the parent of the state
    protected int cost;

    public AState()
    {
        parent = null;
        cost = 10;
    }
    public AState(int cost)
    {
        parent = null;
        this.cost = cost;


    }

    @Override
    // A function that compares two
    public  int compareTo(AState obj){
        if(this.getCost() > obj.getCost()){
            return 1;
        }
        else if(this.getCost() == obj.getCost()){
            return 0;
        }
        return -1;
    }

    /**
     * Function hash
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 23;
        hash = hash * 31 + cost;
        hash = hash * 31 + parent.cost;
        return hash;

    }



    /**
     * A function which compares two modes
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof AState){
            if(this.cost == ((AState)o).cost){
                return true;
            }
            return false;
        }
        return false;

    }

    public AState getParent() {
        return this.parent;
    }

    /**
     *  @param parent the parents to set for the state
     *
     */
    public  void setParent(AState parent){
        this.parent = parent;
    }

    /**
     * A function which puts the cost of the point. For diagonal cost is one
     * @param cost
     */
    public void setCost(int cost) {
        this.cost = cost;

    }

    /**
     * A function that returns the cost of the given point
     * @return int cost
     */
    public int getCost() {
        return cost;
    }

}

package algorithms.search;

import jdk.internal.dynalink.beans.StaticClass;

/**
 * An abstract class that represents a problem situation of a particular problem that we wish to solve
 */
public abstract class AState {

    protected  AState parent; //the parent of the state
    protected boolean m_bVisited; //true if the state was visited before
    protected boolean m_bHasMoreNeighbors; //true if there are unvisited neighbors

    public AState()
    {
        m_bHasMoreNeighbors = true;
        m_bVisited = false;
        parent = null;
    }
    // A function that compares two
    public abstract int compareTo(AState obj);

    public void setVisited(boolean visited)
    {
        m_bVisited = visited;
    }

    public void setM_bHasMoreNeighbors(boolean hasMoreNeighbors)
    {
        m_bHasMoreNeighbors = hasMoreNeighbors;
    }

    public AState getParent() {
        return this.parent;
    }


    public abstract void setParent(AState parent);

}
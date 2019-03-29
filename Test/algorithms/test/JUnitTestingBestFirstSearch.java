package algorithms.test;

import algorithms.search.BestFirstSearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingBestFirstSearch {

    @Test
    void solve() {

        BestFirstSearch BestFirstSearch = new BestFirstSearch();
        assertEquals(BestFirstSearch.solve(null),null);
    }

    @Test
    void getNumberOfNodesEvaluated() {



    }



    @Test
    void getName() {

        BestFirstSearch BestFirstSearch = new BestFirstSearch();
        assertEquals(BestFirstSearch.getName(),"Best First Search");
    }
}
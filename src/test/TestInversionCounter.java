import inversioncounter.InversionCounter;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestInversionCounter extends junit.framework.TestCase {

    private InversionCounter<Integer> ms;
    
    @BeforeClass
    public void setUp() {
        ms = new InversionCounter<Integer>();
    }

    public void testMergeAndCount() {
        Integer[] left  = new Integer[] {1,4,6};
        Integer[] right = new Integer[] {2,3,5};
        Integer[] expected = new Integer[] {1,2,3,4,5,6};

        assertArrayEquals(expected, ms.mergeAndCount( left, right ));
        assertEquals(5, ms.getInversions().intValue());
    }
    
    public void testInversionCounter() {
        Integer[] list     = new Integer[] {7, 2, 1, 5, 77, 23, 12};
        Integer[] expected = new Integer[] {1, 2, 5, 7, 12, 23, 77};
        
        assertArrayEquals(expected, ms.inversionCounter( list ));
        assertEquals(7, ms.getInversions().intValue());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestInversionCounter.class);
            for (Failure failure : result.getFailures()) {
                  System.out.println(failure.toString());
        }
    }
}

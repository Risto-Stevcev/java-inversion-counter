package inversioncounter;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class InversionCounter<T extends Comparable<T>>
{
    private Long inversions;

    public InversionCounter() {
        inversions = (long) 0;
    }

    public T[] mergeAndCount(T[] left, T[] right)
    {
        @SuppressWarnings("unchecked")
        T[] list = (T[]) Array.newInstance( left[0].getClass(), 
                                            left.length + right.length );

        for (int i = 0, l = 0, r = 0; l < left.length || r < right.length; i++)
        {
            if (l < left.length && r < right.length) {
                if (left[l].compareTo( right[r] ) < 0)
                    list[i] = left[l++];
                else {
                    list[i] = right[r++];
                    inversions += (left.length - l);
                }
            } 
            else if (l < left.length)
                list[i] = left[l++];

            else if (r < right.length)
                list[i] = right[r++];
        }
        return list;
    }

    public T[] inversionCounter(T[] list)
    {
        if (list.length == 1)
            return list;

        Integer mid = list.length / 2;

        T[] left  = inversionCounter(Arrays.copyOfRange( list, 0, mid ));
        T[] right = inversionCounter(Arrays.copyOfRange( list, mid, list.length ));

        return mergeAndCount(left, right);
    }

    public Long getInversions()
    {
        return inversions;
    }

    public static void main(String[] args)
    {
        if (args.length == 2) {
            try {
                BufferedReader file = new BufferedReader(new FileReader( args[0] ));
                Integer length = Integer.parseInt(args[1]);
                Integer[] list = new Integer[length];

                for(int i = 0; i < length; i++)
                    list[i] = Integer.parseInt(file.readLine());

                InversionCounter<Integer> ms = new InversionCounter<Integer>();
                Integer[] merged = ms.inversionCounter(list);
                System.out.println(ms.getInversions());
            }
            catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
            catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        else {
            System.out.println("Usage: ./program [list file] [list length]");
        }
    }
}

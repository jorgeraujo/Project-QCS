package types;
import static java.lang.Math.max;

public class Voter {

    public static int vote(int[] results, int n){



        int [] values = new int[999];
        int bigger = -1;
        int best = 0;
        int half;
        int which = -1;
        for (int i = 0; i < n; i++){
            values[results[i]]++; //incrementing votes on a specific value
            bigger = max(bigger, results[i]); //bigger gets the biggest number voted to restrict the next cycle for
        }

        for (int i = 0; i <= bigger; i++){
            if (values[i] > 0){ //it means it was voted

                if (best < values[i]){
                    best = values[i]; //best gets the number the biggest number of votes that a option had
                    which = i; //save the voted option
                }
            }
        }
        if (n%2 == 0){
            half = (n/2);
        }
        else{
            half = (n/2) + 1;
        }

        if (best > half){
            return which;
        }
        return -1;

    }
}
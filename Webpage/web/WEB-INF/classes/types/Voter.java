package types;
import static java.lang.Math.max;

public class Voter {

    public static int vote(int[] results, int n){

        int [] values = new int[999];
        int bigger = -1;
        int best = 0;
        int half;
        int which = -1;
        int countInvalid = 0;
        for (int i = 0; i < n; i++){
            if (results[i] == -1){
                countInvalid++;
            }
            else {
                values[results[i]]++; //incrementing votes on a specific value
                bigger = max(bigger, results[i]); //bigger gets the biggest number voted to restrict the next cycle for
            }
        }
        if (countInvalid == n){
            return -1;
        }

        for (int i = 0; i <= bigger; i++){
            if (values[i] > 0){ //it means it was voted

                if (best < values[i]){
                    best = values[i]; //best gets the number the biggest number of votes that a option had
                    which = i; //save the voted option
                }
            }
        }

        /*if (n - countInvalid == 1){
            return which;
        }

        else*/ //if ( ((n /*- countInvalid*/) %2) == 0){
        half = (n/2);
        //}


        if (best > half){
            return which;
        }
        return -1;

    }
}
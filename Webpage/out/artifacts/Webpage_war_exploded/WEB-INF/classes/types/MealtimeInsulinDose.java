package types;

import client.Insulin;
import client.InsulinService;

/**
 * Created by Afonso on 17/05/2017.
 */
public class MealtimeInsulinDose {
    private int input1_1;
    private int input1_2;
    private int input1_3;
    private int input1_4;
    private int input1_5;
    private MealtimeInsulinDose.Webservice webservices[] = new MealtimeInsulinDose.Webservice[1];
    private int results[] = new int[1];

    public int getInput1_1() {
        return input1_1;
    }

    public void setInput1_1(int input1_1) {
        this.input1_1 = input1_1;
    }

    public int getInput1_2() {
        return input1_2;
    }

    public void setInput1_2(int input1_2) {
        this.input1_2 = input1_2;
    }

    public int getInput1_3() {
        return input1_3;
    }

    public void setInput1_3(int input1_3) {
        this.input1_3 = input1_3;
    }

    public int getInput1_4() {
        return input1_4;
    }

    public void setInput1_4(int input1_4) {
        this.input1_4 = input1_4;
    }

    public int getInput1_5() {
        return input1_5;
    }

    public void setInput1_5(int input1_5) {
        this.input1_5 = input1_5;
    }



    public String getResult() {
        webservices[0] = new Webservice();
        webservices[0].start();
        try {
            webservices[0].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("Results: "+webservices[0].getResult());
        results[0] = webservices[0].getResult();
        return Integer.toString(results[0]);
    }

    public class Webservice extends Thread{
        private int result;

        public int getResult() {
            return this.result;
        }

        public void run() {
            try {
                InsulinService service = new InsulinService();
                Insulin proxy = service.getInsulinPort();
                result = proxy.mealtimeInsulinDose(getInput1_1(), getInput1_2(), getInput1_3(), getInput1_4(), getInput1_5());
            } catch (Exception e) {
                System.out.println(e);
            }
            //System.out.println(result);
        }
    }

}

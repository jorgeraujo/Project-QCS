package types;

import client.Insulin;
import client.InsulinService;

import java.net.URL;

/**
 * Created by Afonso on 17/05/2017.
 */
public class MealtimeInsulinDose {
    private int input1_1;
    private int input1_2;
    private int input1_3;
    private int input1_4;
    private int input1_5;
    private int n = 3;
    private int finalResult;
    private Webservice webservices[] = new Webservice[n];
    private int results[] = new int[n];
    private String urls[] = {"http://10.17.1.17:8081/insulindosecalculator?wsdl", "http://10.17.1.10:8080/WebServiceQCS/services/InsulinDoseCalculator?wsdl", "http://10.17.1.24:9000/InsulinDoseCalculatorEndpoint?wsdl"};

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



    public String getResult(){
        runThreads();
        finalResult = Voter.vote(results, n);

        if (finalResult != -1){
            return Integer.toString(finalResult);
        }
        else{
            return "Error, try again";
        }
    }

    public void runThreads() {

        for (int i = 0; i < n; i++){
            webservices[i] = new Webservice(urls[i]);
            webservices[i].start();
        }

        for (int i = 0; i < n; i++){
            try {
                webservices[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            results[i] = webservices[i].getResult();
        }

    }

    public String getWebServiceName(int i){
        return this.webservices[i].name;
    }

    public class Webservice extends Thread{
        private int result;
        private String name;

        public Webservice(String name){
            this.name = name;
            this.result = -1;
        }

        public int getResult() {
            return this.result;
        }

        public void run() {
            try {
                InsulinService service = new InsulinService(new URL(name));
                Insulin proxy = service.getInsulinPort();
                result = proxy.mealtimeInsulinDose(getInput1_1(), getInput1_2(), getInput1_3(),getInput1_4(),getInput1_5());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}

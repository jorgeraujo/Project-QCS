package types;
import client.Insulin;
import client.InsulinService;

import java.net.URL;

public class BackgroundInsulinDose {

    private int input3_1;
    private int n = 3;
    private Webservice webservices[] = new Webservice[n];
    private int results[] = new int[n];
    private int finalResult;
    private String urls[] = {"http://10.17.1.17:8081/insulindosecalculator?wsdl", "http://10.17.1.10:8080/WebServiceQCS/services/InsulinDoseCalculator?wsdl", "http://10.17.1.24:9000/InsulinDoseCalculatorEndpoint?wsdl"};

    public int getInput3_1() {
        return input3_1;
    }

    public void setInput3_1(int input3_1) {
        this.input3_1 = input3_1;
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
            //System.out.println("Results: "+webservices[i].getResult());
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
                result = proxy.backgroundInsulinDose(getInput3_1());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
package types;

import client.Insulin;
import client.InsulinService;
import com.sun.xml.internal.ws.client.BindingProviderProperties;

import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.Map;

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
    private String urls[] = {"http://10.17.1.17:8081/insulindosecalculator?wsdl", "http://10.17.1.14:9000/InsulinDoseCalculator?wsdl", "http://localhost:8081/insulin?wsdl"};

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
        for (int i = 0; i < 3; i++){
            runThreads();
            finalResult = Voter.vote(results, n);

            if (finalResult != -1){
                break;
            }
        }
        if (finalResult != -1) {
            return Integer.toString(finalResult);
        }
        else{
            return "Error, try again";
        }
    }

    public void runThreads() {

        for (int i = 0; i < n; i++){
            webservices[i] = new Webservice(urls[i], i);
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
        if (this.webservices[i].result == -1){
            return (this.webservices[i].name + " - Timeout");
        }
        else{
            return (this.webservices[i].name + " - "+this.webservices[i].result);
        }
    }

    public class Webservice extends Thread{
        private int result;
        private String name;
        private int i;

        public Webservice(String name, int i){
            this.name = name;
            this.i = i;
            this.result = -1;
        }

        public int getResult() {
            return this.result;
        }

        public void run() {
            try {
                if (this.i == 0){
                    InsulinDoseCalculator.InsulinDoseCalculatorService service1 = new InsulinDoseCalculator.InsulinDoseCalculatorService(new URL(name));
                    InsulinDoseCalculator.InsulinDoseCalculator proxy1 = service1.getInsulinDoseCalculatorPort();
                    result = proxy1.mealtimeInsulinDose(getInput1_1(), getInput1_2(), getInput1_3(),getInput1_4(),getInput1_5());

                    Map<String, Object> requestContext = ((BindingProvider)proxy1).getRequestContext();
                    requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, 665);
                    requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, 665);
                }
                else if (this.i == 1){
                    InsulinDoseCalculatorService.InsulinDoseCalculatorService service2 = new InsulinDoseCalculatorService.InsulinDoseCalculatorService(new URL(name));
                    InsulinDoseCalculatorService.InsulinDoseCalculator proxy2 = service2.getInsulinDoseCalculatorPort();
                    result = proxy2.mealtimeInsulinDose(getInput1_1(), getInput1_2(), getInput1_3(),getInput1_4(),getInput1_5());

                    Map<String, Object> requestContext = ((BindingProvider)proxy2).getRequestContext();
                    requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, 665);
                    requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, 665);
                }
                else if (this.i == 2){
                    InsulinService service = new InsulinService(new URL(name));
                    Insulin proxy = service.getInsulinPort();
                    result = proxy.mealtimeInsulinDose(getInput1_1(), getInput1_2(), getInput1_3(),getInput1_4(),getInput1_5());

                    Map<String, Object> requestContext = ((BindingProvider)proxy).getRequestContext();
                    requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, 665);
                    requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, 665);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}

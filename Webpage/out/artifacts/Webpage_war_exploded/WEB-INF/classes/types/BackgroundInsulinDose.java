package types;
import client.Insulin;
import client.InsulinService;

import com.sun.xml.internal.ws.client.BindingProviderProperties;

import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.Map;

public class BackgroundInsulinDose {

    private int input3_1;
    private int n = 3;
    private Webservice webservices[] = new Webservice[n];
    private int results[] = new int[n];
    private int finalResult;
    private String urls[] = {"http://10.17.1.17:8081/insulindosecalculator?wsdl", "http://10.17.1.14:9000/InsulinDoseCalculator?wsdl", "http://localhost:8081/insulin?wsdl"};

    public int getInput3_1() {
        return input3_1;
    }

    public void setInput3_1(int input3_1) {
        this.input3_1 = input3_1;
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
            //System.out.println("Results: "+webservices[i].getResult());
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
                    result = proxy1.backgroundInsulinDose(getInput3_1());

                    Map<String, Object> requestContext = ((BindingProvider)proxy1).getRequestContext();
                    requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, 665);
                    requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, 665);
                }
                else if (this.i == 1){
                    InsulinDoseCalculatorService.InsulinDoseCalculatorService service2 = new InsulinDoseCalculatorService.InsulinDoseCalculatorService(new URL(name));
                    InsulinDoseCalculatorService.InsulinDoseCalculator proxy2 = service2.getInsulinDoseCalculatorPort();
                    result = proxy2.backgroundInsulinDose(getInput3_1());

                    Map<String, Object> requestContext = ((BindingProvider)proxy2).getRequestContext();
                    requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, 665);
                    requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, 665);
                }
                else if (this.i == 2){
                    InsulinService service = new InsulinService(new URL(name));
                    Insulin proxy = service.getInsulinPort();
                    result = proxy.backgroundInsulinDose(getInput3_1());

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
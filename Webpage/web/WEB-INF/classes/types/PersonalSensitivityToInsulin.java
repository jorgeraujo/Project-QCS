package types;

import client.Insulin;
import client.InsulinService;
import com.sun.xml.internal.ws.client.BindingProviderProperties;

import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Afonso on 17/05/2017.
 */
public class PersonalSensitivityToInsulin {

    private int input2_1;
    private int input2_2;
    private int input2_3;
    private int input2_4;
    private int input2_5;
    ArrayList<Integer> list1;
    ArrayList<Integer> list2;
    private int p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
    private int b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    int n = 3;
    private Webservice webservices[] = new Webservice[n];
    private int results[] = new int[n];
    private int finalResult;
    private String urls[] = {"http://10.17.1.17:8081/insulindosecalculator?wsdl", "http://10.17.1.10:8080/WebServiceQCS/services/InsulinDoseCalculator?wsdl", "http://10.17.1.24:9000/InsulinDoseCalculatorEndpoint?wsdl"};

    public int getInput2_1() {
        return input2_1;
    }

    public void setInput2_1(int input2_1) {
        this.input2_1 = input2_1;
    }

    public int getInput2_2() {
        return input2_2;
    }

    public void setInput2_2(int input2_2) {
        this.input2_2 = input2_2;
    }

    public int getInput2_3() {
        return input2_3;
    }

    public void setInput2_3(int input2_3) {
        this.input2_3 = input2_3;
    }

    public int getInput2_4() {
        return input2_4;
    }

    public void setInput2_4(int input2_4) {
        this.input2_4 = input2_4;
    }

    public int getInput2_5() {
        return input2_5;
    }

    public void setInput2_5(int input2_5) {
        this.input2_5 = input2_5;
    }


    public List<Integer> getList1() {
        return list1;
    }

    public void setList1(ArrayList<Integer> list1) {
        this.list1 = list1;
    }

    public List<Integer> getList2() {
        return list2;
    }

    public void setList2(ArrayList<Integer> list2) {
        this.list2 = list2;
    }

    public void setP1(int p1) {
        list1 = new ArrayList<Integer>();
        list1.add(p1);
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        list1.add(p2);
    }

    public int getP3() {
        return p3;
    }

    public void setP3(int p3) {
        list1.add(p3);
    }

    public int getP4() {
        return p4;
    }

    public void setP4(int p4) {
        list1.add(p4);
    }

    public int getP5() {
        return p5;
    }

    public void setP5(int p5) {
        list1.add(p5);
    }

    public int getP6() {
        return p6;
    }

    public void setP6(int p6) {
        list1.add(p6);
    }

    public int getP7() {
        return p7;
    }

    public void setP7(int p7) {
        list1.add(p7);
    }

    public int getP8() {
        return p8;
    }

    public void setP8(int p8) {
        list1.add(p8);
    }

    public int getP9() {
        return p9;
    }

    public void setP9(int p9) {
        list1.add(p9);
    }

    public int getP10() {
        return p10;
    }

    public void setP10(int p10) {
        list1.add(p10);
    }

    public int getB1() {
        return b1;
    }

    public void setB1(int b1) {
        list2 = new ArrayList<Integer>();
        list2.add(b1);
    }

    public int getB2() {
        return b2;
    }

    public void setB2(int b2) {
        list2.add(b2);
    }

    public int getB3() {
        return b3;
    }

    public void setB3(int b3) {
        list2.add(b3);
    }

    public int getB4() {
        return b4;
    }

    public void setB4(int b4) {
        list2.add(b4);
    }

    public int getB5() {
        return b5;
    }

    public void setB5(int b5) {
        list2.add(b5);
    }

    public int getB6() {
        return b6;
    }

    public void setB6(int b6) {
        list2.add(b6);
    }

    public int getB7() {
        return b7;
    }

    public void setB7(int b7) {
        list2.add(b7);
    }

    public int getB8() {
        return b8;
    }

    public void setB8(int b8) {
        list2.add(b8);
    }

    public int getB9() {
        return b9;
    }

    public void setB9(int b9) {
        list2.add(b9);
    }

    public int getB10() {
        return b10;
    }

    public void setB10(int b10) {
        list2.add(b10);
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
        if (this.webservices[i].result == -1){
            return (this.webservices[i].name + " - Timeout");
        }
        else{
            return (this.webservices[i].name + " - "+this.webservices[i].result);
        }
    }


    public class Webservice extends Thread{
        private int personal;
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
                Map<String, Object> requestContext = ((BindingProvider)proxy).getRequestContext();
                requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, 2000);
                requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, 2000);
                personal = proxy.personalSensitivityToInsulin(getInput2_1(), getList1(), getList2());
                result = proxy.mealtimeInsulinDose(getInput2_2(), getInput2_3(), getInput2_4(), getInput2_5(), personal); //change this parameters
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

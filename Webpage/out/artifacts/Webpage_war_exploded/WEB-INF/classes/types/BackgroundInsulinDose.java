package types;
import client.Insulin;
import client.InsulinService;

public class BackgroundInsulinDose {

    private int input3_1;
    private Webservice webservices[] = new Webservice[1];
    private int results[] = new int[1];

    public int getInput3_1() {
        return input3_1;
    }

    public void setInput3_1(int input3_1) {
        this.input3_1 = input3_1;
    }

    public String getResult() {
        webservices[0] = new Webservice("*name to give*");
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

    public String getWebServiceName(){
        return this.webservices[0].name;
    }

    public class Webservice extends Thread{
        private int result;
        private String name;

        public Webservice(String name){
            this.name = name;
        }

        public int getResult() {
            return this.result;
        }

        public void run() {
            try {
                InsulinService service = new InsulinService();
                Insulin proxy = service.getInsulinPort();
                result = proxy.backgroundInsulinDose(getInput3_1());
            } catch (Exception e) {
                System.out.println(e);
            }
            //System.out.println(result);
        }
    }

}
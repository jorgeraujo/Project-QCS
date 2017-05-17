package types;
import client.Insulin;
import client.InsulinService;

public class BackgroundInsulinDose {

    private int input3_1;
    private Webservice webservice;

    public int getInput3_1() {
        return input3_1;
    }

    public void setInput3_1(int input3_1) {
        this.input3_1 = input3_1;
    }

    public String getResult() {
        InsulinService service = new InsulinService();
        Insulin proxy = service.getInsulinPort();
        int result = proxy.backgroundInsulinDose(getInput3_1());
        return Integer.toString(result);
    }


    public class Webservice extends Thread{
        private int result;

        public int getResult() {
            return this.result;
        }

        public void run() {
           /* try {
                InsulinService service = new InsulinService();
                Insulin proxy = service.getInsulinPort();
                result = proxy.backgroundInsulinDose(getInput3_1());
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(result);*/
        }


    }

}
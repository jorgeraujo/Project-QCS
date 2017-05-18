package types;

import client.Insulin;
import client.InsulinService;
import java.util.List;

/**
 * Created by Afonso on 17/05/2017.
 */
public class PersonalSensitivityToInsulin {

    private int input2_1;
    private List<Integer> list1;
    private List<Integer> list2;

    public int getInput2_1() {
        return input2_1;
    }

    public void setInput2_1(int input2_1) {
        this.input2_1 = input2_1;
    }

    public List<Integer> getList1() {
        return list1;
    }

    public void setList1(List<Integer> list1) {
        this.list1 = list1;
    }

    public List<Integer> getList2() {
        return list2;
    }

    public void setList2(List<Integer> list2) {
        this.list2 = list2;
    }


    public String getResult() {
        InsulinService service = new InsulinService();
        Insulin proxy = service.getInsulinPort();
        int result = proxy.personalSensitivityToInsulin(getInput2_1(), getList1(), getList2());
        return Integer.toString(result);
    }



    public class Webservice extends Thread {
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

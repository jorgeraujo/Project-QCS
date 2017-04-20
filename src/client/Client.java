package client;
import java.util.*;


class Client {
	public static void main(String[] args) {
		InsulinService service = new InsulinService();
		Insulin proxy = service.getInsulinPort();
		System.out.println("Insulin doses needed after meal: " + proxy.mealtimeInsulinDose(100, 13, 150, 100, 50));
		System.out.println("Background insulin dose: " + proxy.backgroundInsulinDose(80));
		System.out.println("Drop in blood sugar resulting from one unit of insulin: " + proxy.personalSensitivityToInsulin(8, Arrays.asList(0, 5, 9), Arrays.asList(50, 75, 100)));
	}
}

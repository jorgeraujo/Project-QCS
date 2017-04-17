package client;

class Client {
	public static void main(String[] args) {
		InsulinService service = new InsulinService();
		Insulin proxy = service.getInsulinPort();
		System.out.println("Insulin doses needed after meal: " + proxy.mealtimeInsulinDose(100, 13, 150, 100, 50));
		System.out.println("Background insulin dose: " + proxy.backgroundInsulinDose(80));

		System.out.println("Insulin doses needed after meal: " + proxy.mealtimeInsulinDose(120, 13, 150, 100, 50));
		System.out.println("Background insulin dose: " + proxy.backgroundInsulinDose(100));
	}
}

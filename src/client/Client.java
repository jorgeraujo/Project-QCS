package client;

class Client {
	public static void main(String[] args) {
		InsulinService service = new InsulinService();
		Insulin proxy = service.getInsulinPort();
		int isGood = proxy.mealtimeInsulinDose(100, 13, 150, 100, 50);
		if (isGood == 0){
			System.out.println("Mau");
		}
		else{
			System.out.println("Bom");
		}
		
	}
}

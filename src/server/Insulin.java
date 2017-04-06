package server;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class Insulin {
	@WebMethod
	public int mealtimeInsulinDose(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity) {
		
		if (carbohydrateAmount < 60 || carbohydrateAmount > 120 || carbohydrateToInsulinRatio < 10 || carbohydrateToInsulinRatio > 15 
			|| preMealBloodSugar < 120 || preMealBloodSugar > 160 || targetBloodSugar < 80 
			|| targetBloodSugar > 120 || personalSensitivity < 15 || personalSensitivity > 100){
			return 0;
		}
		else{
			return 1;
		}
	}


	@WebMethod
	public int backgroundInsulinDose(int bodyWeight){
		return 0;
	}

	@WebMethod
	public int personalSensitivityToInsulin(int physicalActivityLevel, int[] physicalActivitySamples, int[] bloodSugarDropSamples){
		return 0;
	}

}

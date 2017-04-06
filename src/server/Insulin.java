package server;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class Insulin {
	@WebMethod
	public int mealtimeInsulinDose(int mealCarbohydrates, int carbohydratesBy1Unit, int bloodSugarBeforeMeal, int targetBloodSugarBeforeMeal, int individualSensivity) {
		
		if (mealCarbohydrates < 60 || mealCarbohydrates > 120 || carbohydratesBy1Unit < 10 || carbohydratesBy1Unit > 15 
			|| bloodSugarBeforeMeal < 120 || bloodSugarBeforeMeal > 160 || targetBloodSugarBeforeMeal < 80 
			|| targetBloodSugarBeforeMeal > 120 || individualSensivity < 15 || individualSensivity > 100){
			return 0;
		}
		else{
			return 1;
		}
	}


	@WebMethod
	public int backgroundInsulinDose(int weight){
		return 0;
	}

	@WebMethod
	public int personalSensitivityToInsulin(int physicalActivityLevel, int[] physicalActivityLevels, int[] bloodSugarLevels){
		return 0;
	}

}

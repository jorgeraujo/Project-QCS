package server;

import javax.jws.WebService;
import javax.jws.WebMethod;
import java.math.BigDecimal;
import java.math.MathContext;

@WebService
public class Insulin { //return the number of units of rapid acting insulin needed after meal
	@WebMethod
	public int mealtimeInsulinDose(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity) {
		if (carbohydrateAmount < 60 || carbohydrateAmount > 120 || carbohydrateToInsulinRatio < 10 || carbohydrateToInsulinRatio > 15 
			|| preMealBloodSugar < 120 || preMealBloodSugar > 160 || targetBloodSugar < 80 
			|| targetBloodSugar > 120 || personalSensitivity < 15 || personalSensitivity > 100){
			return 0;
		}

		BigDecimal preMealBloodSugarDecimal = new BigDecimal(preMealBloodSugar);
		BigDecimal targetBloodSugarDecimal = new BigDecimal(targetBloodSugar);
		BigDecimal personalSensitivityDecimal = new BigDecimal(personalSensitivity);
		BigDecimal carbohydrateAmountDecimal = new BigDecimal(carbohydrateAmount);
		BigDecimal carbohydrateToInsulinRatioDecimal = new BigDecimal(carbohydrateToInsulinRatio);

		BigDecimal highBloodSugarDoseDecimal = preMealBloodSugarDecimal.subtract(targetBloodSugarDecimal).divide(personalSensitivityDecimal, MathContext.DECIMAL128);
		//int highBloodSugarDose = (preMealBloodSugar - targetBloodSugar) / personalSensitivity;
		BigDecimal carbohydrateDoseDecimal = carbohydrateAmountDecimal.divide(carbohydrateToInsulinRatioDecimal, MathContext.DECIMAL128);
		//int carbohydrateDose = carbohydrateAmount / carbohydrateToInsulinRatio;
		BigDecimal result = highBloodSugarDoseDecimal.add(carbohydrateDoseDecimal);
		//int result = highBloodSugarDose + carbohydrateDose;

		return (int) Math.ceil(result.doubleValue());
	}


	@WebMethod
	public int backgroundInsulinDose(int bodyWeight){ //return the background insulin dose
		if (bodyWeight < 40 || bodyWeight > 130){
			return 0;
		}

		BigDecimal bodyWeightDecimal = new BigDecimal(bodyWeight);
		BigDecimal tmp = new BigDecimal(0.5 * 0.55);
		BigDecimal result = bodyWeightDecimal.multiply(tmp);

		return (int) Math.ceil(result.doubleValue());
	}

	@WebMethod
	public int personalSensitivityToInsulin(int physicalActivityLevel, int[] physicalActivitySamples, int[] bloodSugarDropSamples){
		return 0;
	}

}

package server;

import javax.jws.WebService;
import javax.jws.WebMethod;
import java.math.*;

@WebService
public class Insulin { //return the number of units of rapid acting insulin needed after meal
	@WebMethod
	public int mealtimeInsulinDose(int carbohydrateAmount, int carbohydrateToInsulinRatio, int preMealBloodSugar, int targetBloodSugar, int personalSensitivity) {
		if (carbohydrateAmount < 60 || carbohydrateAmount > 120 || carbohydrateToInsulinRatio < 10 || carbohydrateToInsulinRatio > 15
			|| preMealBloodSugar < 120 || preMealBloodSugar > 160 || targetBloodSugar < 80
			|| targetBloodSugar > 120 || personalSensitivity < 15 || personalSensitivity > 100){
			return 0;
		}

		else if (targetBloodSugar > preMealBloodSugar) {
			return 0;
		}

		//BigDecimal preMealBloodSugarDecimal = new BigDecimal(preMealBloodSugar);
		//BigDecimal targetBloodSugarDecimal = new BigDecimal(targetBloodSugar);
		//BigDecimal personalSensitivityDecimal = new BigDecimal(personalSensitivity);
		//BigDecimal carbohydrateAmountDecimal = new BigDecimal(carbohydrateAmount);
		//BigDecimal carbohydrateToInsulinRatioDecimal = new BigDecimal(carbohydrateToInsulinRatio);

		//BigDecimal highBloodSugarDoseDecimal = preMealBloodSugarDecimal.subtract(targetBloodSugarDecimal).divide(personalSensitivityDecimal, MathContext.DECIMAL128);
		double highBloodSugarDose = (double)(preMealBloodSugar - targetBloodSugar) / (double)personalSensitivity;
		//BigDecimal carbohydrateDoseDecimal = carbohydrateAmountDecimal.divide(carbohydrateToInsulinRatioDecimal, MathContext.DECIMAL128);
		double carbohydrateDose = (double)(carbohydrateAmount / carbohydrateToInsulinRatio);
		//BigDecimal result = highBloodSugarDoseDecimal.add(carbohydrateDoseDecimal);
		double result = (highBloodSugarDose + carbohydrateDose);

		//return (int) Math.ceil(result.doubleValue());
		return (int) Math.ceil(result);
	}


	@WebMethod
	public int backgroundInsulinDose(int bodyWeight){ //return the background insulin dose
		if (bodyWeight < 40 || bodyWeight > 130){
			return 0;
		}

		//BigDecimal bodyWeightDecimal = new BigDecimal(bodyWeight);
		double result = (0.5 * 0.55) * (double) (bodyWeight);
		//BigDecimal tmp = new BigDecimal(0.5 * 0.55);
		//BigDecimal result = bodyWeightDecimal.multiply(tmp);

		//return (int) Math.ceil(result.doubleValue());
		return (int) Math.ceil(result);
	}

	@WebMethod
	public int personalSensitivityToInsulin(int physicalActivityLevel, int[] physicalActivitySamples, int[] bloodSugarDropSamples){ //return the drop in blood sugar resulting from one unit of insulin
		if (physicalActivityLevel < 0 || physicalActivityLevel > 10 || physicalActivitySamples.length != bloodSugarDropSamples.length){ //both have to have K samples
			return 0;
		}
		if (physicalActivitySamples.length < 2 || physicalActivitySamples.length > 10){
			return 0;
		}

		//BigDecimal sumx = new BigDecimal(0);
		//BigDecimal sumy = new BigDecimal(0);
		double sumx [] = new double[physicalActivitySamples.length];
		double sumy [] = new double[bloodSugarDropSamples.length];
		double meanx = 0;
		double meany = 0;

		//BigDecimal sumx2 = new BigDecimal(0); not needed
		//BigDecimal sumy2 = new BigDecimal(0);
		//BigDecimal sumxy = new BigDecimal(0);

		for(int i = 0; i < physicalActivitySamples.length; i++) {
			if(physicalActivitySamples[i] < 0 || physicalActivitySamples[i] > 10 || bloodSugarDropSamples[i] < 15 || bloodSugarDropSamples[i] > 100){
				return 0;
			}

			//sumx = sumx.add(new BigDecimal(physicalActivitySamples[i]));
			//sumy = sumy.add(new BigDecimal(bloodSugarDropSamples[i]));
			sumx[i] = (double)physicalActivitySamples[i];
			meanx += sumx[i];
			sumy[i] = (double)bloodSugarDropSamples[i];
			meany += sumy[i];

			//sumx2 = sumx2.add(new BigDecimal(physicalActivitySamples[i] * physicalActivitySamples[i])); not needed
			//sumy2 = sumy2.add(new BigDecimal(bloodSugarDropSamples[i] * bloodSugarDropSamples[i]));
			//sumxy = sumxy.add(new BigDecimal(physicalActivitySamples[i] * bloodSugarDropSamples[i]));
		}

		//BigDecimal numberSamples = new BigDecimal(physicalActivitySamples.length);
		//BigDecimal meanx = sumx.divide(numberSamples, MathContext.DECIMAL128);
		meanx = meanx / (double) physicalActivitySamples.length;
		//BigDecimal meany = sumy.divide(numberSamples, MathContext.DECIMAL128);
		meany = meany / (double) bloodSugarDropSamples.length;
		//BigDecimal beta = new BigDecimal(0);
		double beta = 0;
		//BigDecimal betaUp = new BigDecimal(0);
		double betaUp = 0;
		//BigDecimal betaDown = new BigDecimal(0);
		double betaDown = 0;
		for(int i = 0; i < physicalActivitySamples.length; i++) {
			//betaUp = betaUp.add(new BigDecimal(physicalActivitySamples[i]).subtract(meanx).multiply(new BigDecimal(bloodSugarDropSamples[i]).subtract(meany)));
			betaUp += (sumy[i] - meany) * (sumx[i] - meanx);
			//betaDown = betaDown.add( (new BigDecimal(physicalActivitySamples[i]).subtract(meanx)).pow(2) );
			betaDown += Math.pow((sumx[i] - meanx),2);
		}
		//beta = betaUp.divide(betaDown, MathContext.DECIMAL128);
		beta = betaUp / betaDown;
		//BigDecimal beta = (numberSamples.multiply(sumxy).subtract(sumx.multiply(sumy))).divide(numberSamples.multiply(sumx2).subtract(sumx.multiply(sumx)), MathContext.DECIMAL128);

		//BigDecimal alpha = meany.subtract(beta.multiply(meanx));
		double alpha = meany - (beta * meanx);
		//BigDecimal physicalActivityLevelDecimal = new BigDecimal(physicalActivityLevel);
		//BigDecimal result = alpha.multiply(physicalActivityLevelDecimal.add(beta));
		double result = alpha + beta * (double) physicalActivityLevel;
		
		//return (int) Math.ceil(result.doubleValue());
		return (int) Math.ceil(result);
	}
}

package AnnealingMethod;

/**
 *
 * @author Almaz
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class SimulatedAnnealing {

    // Константы:
    public static final double PI = 3.14;  //число Пи
    public static final double G = 6.67 * Math.pow(10, -11);  // Гравитационная постоянная
    public static final double X = 0.0;  //координата по x(горизонт)

    // Рассчитать вероятность принятия
    public static double acceptanceProbability(double energy, double newEnergy, double temperature) {
        // Если новое решение лучше, примите его
        if (newEnergy < energy) {
            return 1.0;
        }
        // Если новое решение хуже, вычисляем вероятность принятия
       System.out.println("acceptanceProbability e-n ="+ (energy - newEnergy));
        System.out.println("acceptanceProbability ="+ Math.exp((energy - newEnergy)*Math.pow(10, 9) / temperature));
        return Math.exp((energy - newEnergy)*Math.pow(10, 9) / temperature);
    }

    public static double roundHalfUp(double num, int signs) {
        return (new java.math.BigDecimal(num).setScale(signs, java.math.BigDecimal.ROUND_HALF_UP)).doubleValue();
    }

    //Заполнение массива по интервалу, шагу и предоставленному значению
    public static List<Double> fillArray(double intervalPercent, double intervalStep, double providedValue) {
        System.out.println("fillArray start");

        List<Double> array = new ArrayList<>();
        double intervalRightValue = providedValue + providedValue * intervalPercent / 100;
        double intervalLeftValue = providedValue - providedValue * intervalPercent / 100;

        if (intervalPercent == 0 || intervalStep == 0) {
            array.add(intervalRightValue);
        } else {
            for (double i = intervalLeftValue; i <= intervalRightValue; i = i + intervalStep) {
                array.add(i);
            }
        }
        System.out.println("fillArray end");
        return array;

    }

    // Получаем случайное значение из массива
    public static double getRandomValue(List<Double> array) {
        Random rand = new Random();
        Double randomValue = array.get(rand.nextInt(array.size()));
        return randomValue;
    }

    // Получаем Массу объекта по форумуле
    public static double getMassValueFromFormula(double r, double p) {
        double massValue = 4 / 3 * PI * Math.pow(r, 3) * p;
        return massValue;
    }

    // Получаем w(дельта g) объекта по форумуле
    public static double getWValueFromFormula(double massValue, double h) {
        double w = G * massValue * (h / (Math.pow(Math.sqrt(Math.pow(X, 2) + Math.pow(h, 2)), 3)));
        return w;
    }

    public static Solution getWDiffRandom(List<Double> rArr, List<Double> pArr, List<Double> hArr, double wTheoretical, Solution currentSolution) {

        double r = getRandomValue(rArr);
        double p = getRandomValue(pArr);
        double h = getRandomValue(hArr);

        // Рассчитаем массу объекта:
        double m = getMassValueFromFormula(r, p);

        // Рассчитаем w (дельта g)
        double wСalculated = getWValueFromFormula(m, h);

        // Рассчитаем разницу между теоретической и рассчитанной w
        double wDiff = Math.abs(wTheoretical - wСalculated);

        currentSolution.setR(r);
        currentSolution.setP(p);
        currentSolution.setH(h);
        currentSolution.setM(m);
        currentSolution.setwСalculated(wСalculated);
        currentSolution.setwTheoretical(wTheoretical);
        currentSolution.setwDiff(wDiff);
        return currentSolution;
    }

    public String startCalc(double rProvidedValue, double pProvidedValue, double hProvidedValue, double wTheoretical, double rIntervalPercent, double pIntervalPercent,
            double hIntervalPercent, double rIntervalStep, double pIntervalStep, double hIntervalStep, double temp, double coolingRate) throws Exception {
        System.out.println("Start startCalc: ");

        // Сгенерируем по интервалу и шагу, все возможные вариации r, p, h. Для дальнейшего случайного избятия значения из этого массива:
        // Создадим Массив:
        List<Double> rArr = new ArrayList<>();// Массив r (радиусы)
        List<Double> pArr = new ArrayList<>();// Массив p (плотностей)
        List<Double> hArr = new ArrayList<>();// Массив h (глубин)

        // Заполнение массивов согласно заданным параметрам:
        rArr = fillArray(rIntervalPercent, rIntervalStep, rProvidedValue);
        pArr = fillArray(pIntervalPercent, pIntervalStep, pProvidedValue);
        hArr = fillArray(hIntervalPercent, hIntervalStep, hProvidedValue);

        long rArrl = rArr.size();
        long pArrl = pArr.size();
        long hArrl = hArr.size();
        System.out.println("rArr.size(): " + rArrl);
        System.out.println("pArr.size(): " + pArrl);
        System.out.println("hArr.size(): " + hArrl);

        long possibleСombinationCount = rArrl * pArrl * hArrl;
        System.out.println("possibleСombinationCount " + possibleСombinationCount);
        //  начальную температуру temp;
        // Скорость охлаждения coolingRate

        // Инициализация начального решения
        // Выбираем случайное значение r,p,h из заполненного массива:
        Solution currentSolution = new Solution();
        getWDiffRandom(rArr, pArr, hArr, wTheoretical, currentSolution);

        System.out.println("Начальное решение: " + currentSolution.getwDiff());

        // Установить в качестве текущего лучшего
        Solution wDiffBest = currentSolution;
        int iterations = 0;

        //////////////////////////////////////////////////
        //Втыкаем данные на график
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("W - " + new Date());
        //////////////////////////////////////////////////

        // Цикл, пока система не остынет
        while (temp > 1) {
            Solution wDiffCurrentSolution = currentSolution;
            Solution wDiffNewSolution = new Solution();
            getWDiffRandom(rArr, pArr, hArr, wTheoretical, wDiffNewSolution);

            // Получить энергию решений
            double currentEnergy = wDiffCurrentSolution.getwDiff();
            double newEnergy = wDiffNewSolution.getwDiff();
            //System.out.println("newEnergy: " + wDiffNewSolution.getwDiff());
            // Решаем, должны ли мы принять новое решение
            if (acceptanceProbability(currentEnergy, newEnergy, temp) > Math.random()) {
                wDiffCurrentSolution = wDiffNewSolution;
            ///////////////////////////////////////////
            //Втыкаем данные на график
            series1.add(temp, wDiffCurrentSolution.getwDiff());
            //////////////////////////////////////////
            }

            // Отслеживаем лучшее решение, найденное
            if (wDiffCurrentSolution.getwDiff() < wDiffBest.getwDiff()) {
                wDiffBest = wDiffCurrentSolution;

            }


            //Охлождаем систему
           // temp = temp - coolingRate;
           temp *= 1 - coolingRate;
            //System.out.println("temp ="+ temp);

            iterations++;
        }

        String result = "Количество возможных комбинация: " + possibleСombinationCount + "\n"
                + "Решение найдено за: " + iterations + " итераций\n"
                + "Окончательное решение:\n"
                + "wDiff: " + wDiffBest.getwDiff() + "\n"
                + "При r: " + wDiffBest.getR() + "\n"
                + "При p: " + wDiffBest.getP() + "\n"
                + "При m: " + wDiffBest.getM() + "\n"
                + "При h: " + wDiffBest.getH() + "\n"
                + "wTheoretical: " + wDiffBest.getwTheoretical() + "\n"
                + "wСalculated: " + wDiffBest.getwСalculated() + "\n";
        System.out.println("result: " + result);

        dataset.addSeries(series1);
        ScatterPlot example = new ScatterPlot("График изменение W от температуры", dataset);
        example.setSize(800, 400);
        example.setLocationRelativeTo(null);
        // example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        example.setVisible(true);

        return result;
    }

}

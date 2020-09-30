package AnnealingMethod;

/**
 *
 * @author Almaz
 */
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

class AnnealingMethod extends JFrame implements ActionListener {

    // create a frame
    static JFrame f;

    // create a textfield
    static JTextField rProvidedValue;
    static JLabel rProvidedValueLabel;
    static JTextField pProvidedValue;
    static JLabel pProvidedValueLabel;
    static JTextField hProvidedValue;
    static JLabel hProvidedValueLabel;

    static JTextField rIntervalPercent;
    static JLabel rIntervalPercentLabel;

    static JTextField pIntervalPercent;
    static JLabel pIntervalPercentLabel;

    static JTextField hIntervalPercent;
    static JLabel hIntervalPercentLabel;

    static JTextField rIntervalStep;
    static JLabel rIntervalStepLabel;

    static JTextField pIntervalStep;
    static JLabel pIntervalStepLabel;

    static JTextField hIntervalStep;
    static JLabel hIntervalStepLabel;

    static JTextField wTheoretical;
    static JLabel wTheoreticalLabel;

    static JTextField temp;
    static JLabel tempLabel;

    static JTextField coolingRate;
    static JLabel coolingRateLabel;

    static JTextArea result;
    static JLabel resultLabel;
    static JLabel author;
    static JButton start;

    // default constrcutor
    AnnealingMethod() {
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("JavaFX Chart (Series)");

        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        LineChart<Number, Number> numberLineChart = new LineChart<Number, Number>(x, y);
        numberLineChart.setTitle("Series");
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("cos(x)");
        series1.setName("sin(x)");
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> datas2 = FXCollections.observableArrayList();
        for (int i = 0; i < 20; i++) {
            datas.add(new XYChart.Data(i, Math.sin(i)));
            datas2.add(new XYChart.Data(i, Math.cos(i)));
        }

        series1.setData(datas);
        series2.setData(datas2);

        Scene scene = new Scene(numberLineChart, 600, 600);
        numberLineChart.getData().add(series1);
        numberLineChart.getData().add(series2);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    // main function
    public static void main(String args[]) {
        // create a frame
        f = new JFrame("Annealing method");

        try {
            // set look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // create a object of class
        AnnealingMethod c = new AnnealingMethod();

        // create a textfield
        rProvidedValue = new JTextField(16);
        rProvidedValue.setEditable(true);
        rProvidedValueLabel = new JLabel("r - Радиус(предоставленное значение):");

        pProvidedValue = new JTextField(16);
        pProvidedValue.setEditable(true);
        pProvidedValueLabel = new JLabel("p - Плоность(предоставленное значение):");

        hProvidedValue = new JTextField(16);
        hProvidedValue.setEditable(true);
        hProvidedValueLabel = new JLabel("h - Глубина(предоставленное значение):");

        rIntervalPercent = new JTextField(16);
        rIntervalPercent.setEditable(true);
        rIntervalPercentLabel = new JLabel("Процент для определения Интервала выборки(+-), для r(Радиус):");

        pIntervalPercent = new JTextField(16);
        pIntervalPercent.setEditable(true);
        pIntervalPercentLabel = new JLabel("Процент для определения Интервала выборки(+-), для p(Плоность):");

        hIntervalPercent = new JTextField(16);
        hIntervalPercent.setEditable(true);
        hIntervalPercentLabel = new JLabel("Процент для определения Интервала выборки(+-), для h(Глубина):");

        ///
        rIntervalStep = new JTextField(16);
        rIntervalStep.setEditable(true);
        rIntervalStepLabel = new JLabel("Шаг в интервале, для r(Радиус):");

        pIntervalStep = new JTextField(16);
        pIntervalStep.setEditable(true);
        pIntervalStepLabel = new JLabel("Шаг в интервале, для p(Плоность):");

        hIntervalStep = new JTextField(16);
        hIntervalStep.setEditable(true);
        hIntervalStepLabel = new JLabel("Шаг в интервале, для h(Глубина):");

        ///
        wTheoretical = new JTextField(16);
        wTheoretical.setEditable(true);
        wTheoreticalLabel = new JLabel("Теоретическое значение для W(предоставленное значение):");

        //
        temp = new JTextField(16);
        temp.setEditable(true);
        tempLabel = new JLabel("Начальная Температура:");

        coolingRate = new JTextField(16);
        coolingRate.setEditable(true);
        coolingRateLabel = new JLabel("Скорость охлаждения:");

        result = new JTextArea();
        result.setEditable(false);
        result.setFont(new Font("Dialog", Font.PLAIN, 10));

        resultLabel = new JLabel("Результат:");

        author = new JLabel("<html>Оптимизации Методом Отжига;<br /> Автор: Назирова Асем;<br /><br />Введите данные для начала процесса Оптимизации:<br /><html>");

        // create  buttons
        start = new JButton("Начать иммитацию Отжига");

        // create a panel
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        // add action listeners
        start.addActionListener(c);

        p.add(author);
        // add elements to panel
        p.add(rProvidedValueLabel);
        p.add(rProvidedValue);
        p.add(pProvidedValueLabel);
        p.add(pProvidedValue);
        p.add(hProvidedValueLabel);
        p.add(hProvidedValue);

        p.add(rIntervalPercentLabel);
        p.add(rIntervalPercent);

        p.add(pIntervalPercentLabel);
        p.add(pIntervalPercent);

        p.add(hIntervalPercentLabel);
        p.add(hIntervalPercent);

        p.add(rIntervalStepLabel);
        p.add(rIntervalStep);

        p.add(pIntervalStepLabel);
        p.add(pIntervalStep);

        p.add(hIntervalStepLabel);
        p.add(hIntervalStep);

        p.add(wTheoreticalLabel);
        p.add(wTheoretical);

        p.add(tempLabel);
        p.add(temp);

        p.add(coolingRateLabel);
        p.add(coolingRate);

        p.add(start);

        p.add(resultLabel);
        p.add(result);

        // set Background of panel
        p.setBackground(Color.lightGray);

        // add panel to frame
        f.add(p);

        f.setSize(600, 720);
        f.show();

        /////////////////////////////////////////////////
    }

    public void actionPerformed(ActionEvent e) {
        //start.setEnabled(false);
        System.out.println("Start: ");
        String s = e.getActionCommand();
        System.out.println("Start: s=" + s);
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();
        try {

            String simulatedAnnealingResult = simulatedAnnealing.startCalc(Double.parseDouble(rProvidedValue.getText()),
                    Double.parseDouble(pProvidedValue.getText()), Double.parseDouble(hProvidedValue.getText()),
                    Double.parseDouble(wTheoretical.getText()), Double.parseDouble(rIntervalPercent.getText()),
                    Double.parseDouble(pIntervalPercent.getText()), Double.parseDouble(hIntervalPercent.getText()),
                    Double.parseDouble(rIntervalStep.getText()), Double.parseDouble(pIntervalStep.getText()),
                    Double.parseDouble(hIntervalStep.getText()), Double.parseDouble(temp.getText()), Double.parseDouble(coolingRate.getText()));

            //start.setEnabled(true);
            result.setText(simulatedAnnealingResult);
        } catch (Exception e1) {
            e1.printStackTrace();

        }
    }
}

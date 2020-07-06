
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries.CategorySeriesRenderStyle;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler.LegendPosition;

public class konvolüsyon implements Chart<CategoryChart> {

    public static void main(String[] args) {

        Chart<CategoryChart> exampleChart = (Chart<CategoryChart>) new konvolüsyon();
        Chart<CategoryChart> exampleChart1 = (Chart<CategoryChart>) new x();
        Chart<CategoryChart> exampleChart2 = (Chart<CategoryChart>) new h();
        CategoryChart chart1 = exampleChart1.getChart();
        CategoryChart chart2 = exampleChart2.getChart();
        CategoryChart chart = exampleChart.getChart();
        new SwingWrapper<>(chart1).displayChart();
        new SwingWrapper<>(chart2).displayChart();
        new SwingWrapper<>(chart).displayChart();
    }

    @Override
    public CategoryChart getChart() {
        int ybas = x.xbas + h.hbas;
        int yuz;
        if ((x.x.length + h.h.length) % 2 == 0) {
            yuz = (x.x.length + h.h.length) - 1;
        } else {
            yuz = (x.x.length + h.h.length);
        }
        int yData[] = new int[yuz];
        for (int i = 0; i < yuz; i++) {
            yData[i] = ybas;
            ybas++;
        }
        int xson[];
        int hson[];
        if (x.xuz >= h.huz) {
            xson = new int[x.xuz];
            hson = new int[x.xuz];
        } else {
            xson = new int[h.huz];
            hson = new int[h.huz];
        }
        for (int i = 0; i < x.xuz; i++) {
            xson[i] = x.x[i];
        }
        for (int i = 0; i < h.huz; i++) {
            hson[i] = h.h[i];
        }
        
        //     KONVOLÜSYON İŞLEMİNİN YAPILDIĞI YER
        int y[] = new int[yuz];
        int indis = 0;
        for (int i = 0; i < yuz / 2 + 1; i++) {
            for (int j = 0; j <= i; j++) {
                y[indis] += hson[i - j] * xson[j];
            }
            indis++;
        }
        int indis2 = indis;
        for (int i = indis - 1; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                y[indis2] += hson[indis - j] * xson[indis - i + j - 1];
            }
            indis2++;
        }
        //********************************************************************
        
        
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Y grafiği").build();

        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setDefaultSeriesRenderStyle(CategorySeriesRenderStyle.Stick);
        chart.addSeries("y[n]", yData, y);
        return chart;
    }
}

class h implements Chart<CategoryChart> {

    Scanner s = new Scanner(System.in);
    static int hbas;
    static int huz;
    static int h[];

    @Override
    public CategoryChart getChart() {
        System.out.print("h[n] grafiğinin başlama noktasını giriniz: ");
        hbas = s.nextInt();
        System.out.print("h grafiğinin uzunluğunu giriniz: ");
        huz = s.nextInt();
        System.out.print(huz + " tane h değerlerini gir: ");
        h = new int[huz];
        for (int i = 0; i < huz; i++) {
            h[i] = s.nextInt();
        }
        int h2bas = hbas;
        int Datay[] = new int[huz];
        for (int i = 0; i < huz; i++) {
            Datay[i] = h2bas;
            h2bas++;
        }
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("H grafiği").build();

        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setDefaultSeriesRenderStyle(CategorySeriesRenderStyle.Stick);
        chart.addSeries("h[n]", Datay, h);

        return chart;
    }
}

class x implements Chart<CategoryChart> {

    Scanner s = new Scanner(System.in);
    static int xbas;
    static int xuz;
    static int x[];

    @Override
    public CategoryChart getChart() {
        System.out.print("x[n] grafiğinin başlama noktasını giriniz: ");
        xbas = s.nextInt();
        System.out.print("x grafiğinin uzunluğunu giriniz: ");
        xuz = s.nextInt();
        System.out.print(xuz + " tane x değerlerini gir: ");
        x = new int[xuz];
        for (int i = 0; i < xuz; i++) {
            x[i] = s.nextInt();
        }
        int Datax[] = new int[xuz];
        int x2bas = xbas;
        for (int i = 0; i < xuz; i++) {
            Datax[i] = x2bas;
            x2bas++;
        }
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("X grafiği").build();

        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setDefaultSeriesRenderStyle(CategorySeriesRenderStyle.Stick);
        chart.addSeries("x[n]", Datax, x);
        return chart;
    }
}

interface Chart<T> {

    public CategoryChart getChart();

}

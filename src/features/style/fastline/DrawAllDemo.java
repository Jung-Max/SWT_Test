/*
 * DrawAllDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.fastline;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.styles.FastLine;

import features.ChartSample2;

/**
 * @author tom
 *
 */
public class DrawAllDemo extends ChartSample2 implements SelectionListener {

	private FastLine lineSeries;
	private long startTime;
	private long endTime;
	

	public DrawAllDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {
	}

	public void widgetSelected(SelectionEvent se) {
		Widget source = se.widget;
		if (source instanceof Button) {
			if (source == drawAllPoints) {
				lineSeries.setDrawAllPoints(true);
			} else if (source == drawNonRepeated) {
				lineSeries.setDrawAllPoints(false);
			}
		}
	}

	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Draw: ");
		drawAllPoints = addRadioButton("All points (one million)", "", this);
		drawNonRepeated = addRadioButton("Non-repeated X only", "", this);
	}

	protected void initContent() {
		super.initContent();
//		drawNonRepeated.setSelection(true);
		drawAllPoints.setSelection(true);
	}

	protected void initSeries() {
		double[] MyX = new double[NUM_POINTS];
		double[] MyY = new double[NUM_POINTS];

		Random generator = new Random();
		double tmpRandom = 10000 * generator.nextDouble();

		for (int t = 0; t < NUM_POINTS; t++) {
			tmpRandom += (100 * generator.nextDouble()) - 50;
			MyX[t] = t;
			MyY[t] = tmpRandom;
		}
		startTime = System.currentTimeMillis();
		for (int i = 0; i < chartList.size(); i++) {
			lineSeries = new com.steema.teechart.styles.FastLine(chartList.get(i).getChart());
			lineSeries.getXValues().count = NUM_POINTS;
			lineSeries.getXValues().value = MyX;
			lineSeries.getYValues().count = NUM_POINTS;
			lineSeries.getYValues().value = MyY;

			// tell lineSeries to draw non-repeated points only ( much faster ! )
//			lineSeries.setDrawAllPoints(false);
        lineSeries.setDrawAllPoints(false);
		}
	}

	protected void initChart() {
		super.initChart();
		// Set axis calculations in "fast mode".
		// Note: For Windows Me and 98 might produce bad drawings when
		// chart zoom is very big.
		// TODO myChart.getAxes().setFastCalc(true);
		for (int i = 0; i < chartList.size(); i++) {
			chartList.get(i).getAspect().setView3D(false);
			chartList.get(i).getLegend().setVisible(false);
			chartList.get(i).getHeader().setVisible(true);
			chartList.get(i).setText("ywjung One million points !\nDisplaying non-repeated X position only.");

			chartList.get(i).getZoom().setAnimated(true);
			chartList.get(i).getZoom().setAnimatedSteps(3);
			chartList.get(i).getZoom().getBrush().setColor(Color.BLUE);
			chartList.get(i).getZoom().getBrush().setSolid(true);
			chartList.get(i).getZoom().getPen().setColor(Color.RED);
			chartList.get(i).getZoom().getPen().setWidth(3);
		}



		initSeries();
		
		chartList.get(chartList.size()-1).addChartPaintListener(new ChartPaintAdapter() {
			public void chartPainted(ChartDrawEvent e) {
				endTime = System.currentTimeMillis();
				Long diff = endTime - startTime;
				System.out.print(Long.toString(diff) + "  ");
			}
		});			


		

	}

	private Button drawAllPoints;
	private Button drawNonRepeated;
//    private final static int NUM_POINTS = 1000000;  // one million !	
	private final static int NUM_POINTS = 15000; // one million !

}

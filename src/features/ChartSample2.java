/*
 * ChartSample.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.TChart;
import com.steema.teechart.drawing.Gradient;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.themes.ColorPalettes;

/**
 *
 * @author tom
 */
public class ChartSample2 extends BaseSample {

	protected List<TChart> chartList;
//	private int chartNum = 4;

	/** Creates a new instance of ChartSample */
	public ChartSample2(Composite c) {
		super(c);

		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				for (int i = 0; i < chartList.size(); i++) {
					if (chartList.get(i) != null) {
						chartList.get(i).dispose();
					}
				}

			}
		});
	}

	/**
	 * initializes TChart stuff
	 * 
	 * @throws IOException
	 */
	protected void initChart() {
		for (int i = 0; i < chartList.size(); i++) {
			chartList.get(i).getFooter().setVisible(false);
			chartList.get(i).getHeader().setVisible(false);
			chartList.get(i).getAspect().setView3D(true);

			Gradient g = chartList.get(i).getPanel().getGradient();

			g.setDirection(GradientDirection.VERTICAL);

			// Smooth gray-like colors that adapt correctly to all demos:
			g.setEndColor(new com.steema.teechart.drawing.Color(109, 109, 109));
			g.setMiddleColor(new com.steema.teechart.drawing.Color(149, 202, 255));
			g.setStartColor(new com.steema.teechart.drawing.Color(0, 115, 230));

			g.setVisible(true);

			chartList.get(i).getAxes().getLeft().getAxisPen().setWidth(1);
			chartList.get(i).getAxes().getBottom().getAxisPen().setWidth(1);

			chartList.get(i).getHeader().getFont().setColor(new com.steema.teechart.drawing.Color(255, 255, 128));

			com.steema.teechart.themes.ThemesList.applyTheme(chartList.get(i).getChart(), 13);

		}

		// ColorPalettes.applyPalette(chartList.getChart(),1); // Excel color palette
	}

	/**
	 * Creates the UI components.
	 */
	protected void createContent() {
		super.createContent();
		chartList = new ArrayList<TChart>();
		for(int i=0; i< 128; i++) {
			chartList.add(new TChart(getSamplePane(), SWT.NONE));
		}

		// Trick: Show the Chart editor modal dialog when double-clicking the
		// "description" text area
		// sampleDescription.addMouseListener( new MouseAdapter() {
		// public void mouseDoubleClick(MouseEvent me) {
		// //TODO ChartEditor.editChart(chartList.getChart());
		// }
		// });
	}

	/**
	 * Initializes and configures the UI components.
	 */
	protected void initContent() {
		super.initContent();
		initChart();
	}
}

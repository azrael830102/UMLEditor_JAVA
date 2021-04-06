package Utilities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class CommonUse {

	public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	public static final Color DIAGRAM_BACKGROUND_COLOR = new Color(245, 255, 250);
	public static final Color FOCUS_COLOR = new Color(102, 255, 255);
	public static final Color LINE_FOCUS_COLOR = new Color(50, 120, 225);
	public static final Color LINE_BLUR_COLOR = Color.BLACK;
	public static final Color SELECTED_AREA_COLOR = new Color(37, 148, 216);

	public static final Font DIAGRAM_NAME_FONT = new Font("Comic Sans MS", Font.BOLD, 18);

	public static final BasicStroke SELECT_AREA_STROKE = new SelectAreaStroke();
	public static final BasicStroke USE_CASE_DIAG_STROKE = new UseCaseDiagStroke();
	public static final BasicStroke GROUP_CONTAINER_STROKE = new GroupContainerStroke();

	// -----------------------------------------------------------------------------------------
	public static class SelectAreaStroke extends BasicStroke {
		private final static float dash[] = { 5.0f };
		private final static float thickness = 1.0f;

		public SelectAreaStroke() {
			super(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
		}
	}

	public static class UseCaseDiagStroke extends BasicStroke {
		private final static float dash[] = { 10.0f };
		private final static float thickness = 2.0f;

		public UseCaseDiagStroke() {
			super(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
		}
	}

	public static class GroupContainerStroke extends BasicStroke {
		private final static float dash[] = { 5.0f };
		private final static float thickness = 2.0f;

		public GroupContainerStroke() {
			super(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
		}
	}
}

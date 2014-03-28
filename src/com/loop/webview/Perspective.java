package com.loop.webview;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
	    layout.createFolder("left", IPageLayout.LEFT, 0.2f, IPageLayout.ID_EDITOR_AREA);
        layout.createFolder("right", IPageLayout.RIGHT, 0.7f, IPageLayout.ID_EDITOR_AREA);
        layout.createFolder("bottom", IPageLayout.BOTTOM, 0.7f, IPageLayout.ID_EDITOR_AREA);
	}
}
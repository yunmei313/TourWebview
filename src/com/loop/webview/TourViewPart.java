package com.loop.webview;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class TourViewPart extends ViewPart {

	private Browser browser;

	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.RESIZE);
		composite.setLayout(new GridLayout(2, false));
		browser = new Browser(composite, SWT.RESIZE);
		browser.setLayoutData(new GridData(GridData.FILL,
				GridData.FILL, true, true, 1, 1));
	}

	public void setFocus() {
	}

	public void setUrl(String url) {
		browser.setUrl(url);
		System.out.println("TourViewPart setUrl: " + url);
	}
}


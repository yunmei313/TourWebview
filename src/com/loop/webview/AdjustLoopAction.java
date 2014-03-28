package com.loop.webview;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

public class AdjustLoopAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;

	public void run(IAction action) {
		
		try {
//			StartLoopAction loopaction = new StartLoopAction();
//			loopaction.dispose();
			
//			try 
//			{
//			   PlatformUI.getWorkbench().showPerspective("com.loop.webview.Perspective",       
//			         PlatformUI.getWorkbench().getActiveWorkbenchWindow());
//			} 
//			catch (WorkbenchException e) 
//			{
//			   e.printStackTrace();
//			}
			StartLoopAction.closeSocket();
			String url = "http://106.186.118.169:70/tour2";
				
			TourViewPart viewPart = (TourViewPart) PlatformUI
					.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView("com.loop.webview.TourViewPart");
			viewPart.setUrl(url);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		this.window = window;
	}
}


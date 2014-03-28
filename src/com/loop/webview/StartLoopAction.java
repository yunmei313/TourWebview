package com.loop.webview;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

public class StartLoopAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;
	public static WebSocketClient cc;
	private static final String starturl = "http://106.186.118.169:70/signin";
	private static final String toururl = "http://106.186.118.169:70/tour2";
	private static final String serverSocket = "ws://106.186.118.169:3000";
	
	public void run(IAction action) {
		try {
			connectSocketServer();
			try {
			   PlatformUI.getWorkbench().showPerspective("com.loop.webview.Perspective",  PlatformUI
						.getWorkbench().getActiveWorkbenchWindow());
			} 
			catch (WorkbenchException e) 
			{
			   e.printStackTrace();
			}
				
			ControlViewPart viewPart = (ControlViewPart) PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.showView("com.loop.webview.ControlViewPart");
			viewPart.setUrl(starturl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dispose() {		
		// TODO Auto-generated method stub
		System.out.println("StartLoopAction dispose");
//		if (cc != null)
//			cc = null;
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		this.window = window;
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub
	}
	
	public void connectSocketServer() {
		
		System.out.println( "createSocketClient" );
		try {
			cc = new WebSocketClient( new URI( serverSocket ), new Draft_17() ) {
				@Override
				public void onMessage( String message ) {
					System.out.println( "got message: " + message);
					if ("start Tour".equals(message)) {
						showTourOutline();
					}
				}
				
				@Override
				public void onOpen( ServerHandshake handshake ) {
					System.out.println( "You are connected to LoopServer: " + getURI());
				}
	
				@Override
				public void onClose( int code, String reason, boolean remote ) {
					System.out.println( "You have been disconnected from: " + getURI() + "; Code: " + code + " " + reason);
				}
	
				@Override
				public void onError( Exception ex ) {
					System.out.println( "Exception occured ...\n" + ex);
					ex.printStackTrace();
				}
             };
			cc.connect();
		} catch ( URISyntaxException ex ) {
			System.out.println( serverSocket + " is not a valid WebSocket URI\n" );
		} catch ( Exception ex ) {
			System.out.println("createSocketClient: " + ex );
		}
	}
	
	public void showTourOutline() {
		try {
			System.out.println("showTourOutline");
			//in order to get activeWorkbenchWindow (we have to be in a ui thread for this to work)
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			    public void run() {
					try {
						TourViewPart viewPart = (TourViewPart) PlatformUI
								.getWorkbench().getActiveWorkbenchWindow().getActivePage()
								.showView("com.loop.webview.TourViewPart");
						viewPart.setUrl(toururl);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeSocket() {
		if( cc != null ) {
			cc.close();
		}
	}
}


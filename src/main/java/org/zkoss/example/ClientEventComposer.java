package org.zkoss.example;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Textbox;

public class ClientEventComposer extends SelectorComposer<Component> {

	@Wire
	private Textbox tb1;
	@Wire
	private Textbox tb2;
	@Wire
	private Textbox tb3;
	
	@Wire
	private Textbox tb4;
	@Wire
	private Textbox tb5;
	@Wire
	private Textbox tb6;
	
	@Wire
	private Textbox tb7;
	@Wire
	private Textbox tb8;
	@Wire
	private Textbox tb9;

	@Wire
	private Textbox tb10;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		tb1.setAttribute("next", tb2);
		tb2.setAttribute("next", tb3);
	
		tb4.setWidgetOverride("_next", "'$tb5'");
		tb5.setWidgetOverride("_next", "'$tb6'");

		tb7.setWidgetOverride("_next", "'$tb8'");
		tb8.setWidgetOverride("_previous", "'$tb7'");
		tb8.setWidgetOverride("_next", "'$tb9'");
		tb9.setWidgetOverride("_previous", "'$tb8'");
	}
	
	@Listen("onOK=#group1 > textbox")
	public void handleGroup1textboxes(Event event) {
		Textbox next = (Textbox) event.getTarget().getAttribute("next");
		if(null != next) {
			next.focus();
		}
	}
	
	@Listen("onCustomKeyEvent = #tb10")
	public void handleCustomKeyEvent(Event event) {
		Clients.log("can use this event from server listener");
	}
	
}

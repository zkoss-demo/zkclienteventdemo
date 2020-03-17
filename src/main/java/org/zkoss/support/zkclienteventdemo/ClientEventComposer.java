package org.zkoss.support.zkclienteventdemo;

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
		tb3.setAttribute("next", tb1);
	
		initGroup2ClientListeners();
		initGroup3ClientListeners();
		
	}
	
	public void initGroup2ClientListeners() {
		String script = "function(event){doCustomKeyDownEnter_(event,this,$(\"$%s\"))}";
		tb4.setWidgetOverride("doKeyDown_", String.format(script, "tb5"));
		tb5.setWidgetOverride("doKeyDown_", String.format(script, "tb6"));
		tb6.setWidgetOverride("doKeyDown_", String.format(script, "tb4"));
		tb10.setWidgetOverride("doKeyDown_","function(event){interceptCtrlShiftFunctionKey_(event,this)}");
	}
	public void initGroup3ClientListeners() {
		String script = "function(event){doBankEntryFormCheck(event,this,%s,%s)}";
		tb7.setWidgetOverride("doKeyDown_", String.format(script, "$(\"$tb8\")","null"));
		tb8.setWidgetOverride("doKeyDown_", String.format(script, "$(\"$tb9\")","$(\"$tb7\")"));
		tb9.setWidgetOverride("doKeyDown_", String.format(script, "null","$(\"$tb8\")"));
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

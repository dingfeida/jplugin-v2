package net.luis.main.event;

import net.jplugin.core.event.api.Event;
import net.jplugin.core.event.api.IEventFilter;

/**
 *
 * @author: LiuHang
 * @version ����ʱ�䣺2015-3-15 ����10:09:56
 **/

public class EventFilter implements IEventFilter{

	/* (non-Javadoc)
	 * @see net.luis.plugin.event.api.IEventFilter#match(net.luis.plugin.event.api.Event)
	 */
	public boolean match(Event e) {
		TestEvent te = (TestEvent) e;
		if (te.getKey() % 2 ==0){
			return true;
		}else{
			return false;
		}
	}

}

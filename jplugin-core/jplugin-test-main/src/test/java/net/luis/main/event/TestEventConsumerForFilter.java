package net.luis.main.event;

import net.jplugin.core.event.api.Channel;
import net.jplugin.core.event.api.Event;
import net.jplugin.core.event.api.EventConsumer;
import net.jplugin.core.event.api.Channel.ChannelType;

/**
 *
 * @author: LiuHang
 * @version ����ʱ�䣺2015-3-15 ����10:15:49
 **/

public class TestEventConsumerForFilter extends EventConsumer{
	/**
	 * @param etype
	 * @param ctype
	 */
	public TestEventConsumerForFilter() {
	}

	/* (non-Javadoc)
	 * @see net.luis.plugin.event.api.EventConsumer#consume(net.luis.plugin.event.api.Event)
	 */
	@Override
	public void consume(Event e) {
			System.out.println("consumer in filter ... "+e);
	}
}

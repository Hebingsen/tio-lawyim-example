/**
 * 
 */
package org.tio.showcase.websocket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.WsServerAioListener;

/**
 * @author tanyaowu
 * 用户根据情况来完成该类的实现
 */
public class ShowcaseServerAioListener extends WsServerAioListener {
	private static Logger log = LoggerFactory.getLogger(ShowcaseServerAioListener.class);

	public static final ShowcaseServerAioListener me = new ShowcaseServerAioListener();

	private ShowcaseServerAioListener() {}

	@Override
	public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) throws Exception {
		super.onBeforeClose(channelContext, throwable, remark, isRemove);
		WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
		if (wsSessionContext.isHandshaked()) {
			int count = Aio.getAllChannelContexts(channelContext.getGroupContext()).getObj().size();
			String msg = channelContext.getClientNode().toString() + " 离开了，现在共有【" + count + "】人在线";
			//用tio-websocket，服务器发送到客户端的Packet都是WsResponse
			WsResponse wsResponse = WsResponse.fromText(msg, ShowcaseServerConfig.CHARSET);
			//群发
			Aio.sendToGroup(channelContext.getGroupContext(), Const.GROUP_ID, wsResponse);
		}
	}

}

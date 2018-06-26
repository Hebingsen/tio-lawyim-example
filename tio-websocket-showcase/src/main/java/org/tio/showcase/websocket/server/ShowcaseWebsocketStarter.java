package org.tio.showcase.websocket.server;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.tio.core.ssl.SslConfig;
import org.tio.server.ServerGroupContext;
import org.tio.websocket.server.WsServerStarter;

/**
 * @author tanyaowu 2017年6月28日 下午5:34:04
 */
public class ShowcaseWebsocketStarter {

	private WsServerStarter wsServerStarter;
	private ServerGroupContext serverGroupContext;

	public static void main(String[] args) throws Exception {
		start();
	}

	/**
	 * @param args
	 * @author tanyaowu
	 * @throws IOException
	 */
	public static void start() throws Exception {
		ShowcaseWebsocketStarter appStarter = new ShowcaseWebsocketStarter(ShowcaseServerConfig.SERVER_PORT,
				ShowcaseWsMsgHandler.me);
		appStarter.wsServerStarter.start();
	}

	/**
	 *
	 * @author tanyaowu
	 */
	public ShowcaseWebsocketStarter(int port, ShowcaseWsMsgHandler wsMsgHandler) throws Exception {
		wsServerStarter = new WsServerStarter(port, wsMsgHandler);

		serverGroupContext = wsServerStarter.getServerGroupContext();
		serverGroupContext.setName(ShowcaseServerConfig.PROTOCOL_NAME);
		serverGroupContext.setServerAioListener(ShowcaseServerAioListener.me);
		// 设置心跳超时时间
		serverGroupContext.setHeartbeatTimeout(ShowcaseServerConfig.HEARTBEAT_TIMEOUT);
	}

}

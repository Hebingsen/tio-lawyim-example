package org.tio.showcase.websocket.server;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;

import cn.hutool.json.JSONUtil;

/**
 * @author tanyaowu 2017年6月28日 下午5:32:38
 */
public class ShowcaseWsMsgHandler implements IWsMsgHandler {
	private static Logger log = LoggerFactory.getLogger(ShowcaseWsMsgHandler.class);

	public static ShowcaseWsMsgHandler me = new ShowcaseWsMsgHandler();

	private ShowcaseWsMsgHandler() {

	}

	/**
	 * 握手时走这个方法，业务可以在这里获取cookie，request参数等
	 */
	@Override
	public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext)
			throws Exception {
		System.err.println("准备开始握手");
		String clientip = request.getClientIp();
		log.info("收到来自{}的ws握手包\r\n{}", clientip, request.toString());

		/** 解决跨域问题 **/
		httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		httpResponse.addHeader("Access-Control-Max-Age", "3600");
		httpResponse.addHeader("Access-Control-Allow-Headers", "x-requested-with");
		return httpResponse;
	}

	/**
	 * @param httpRequest
	 * @param httpResponse
	 * @param channelContext
	 * @throws Exception
	 * @author tanyaowu
	 */
	@Override
	public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext)
			throws Exception {
		System.err.println("握手成功");
		// 握手成功后,绑定当前用户id
		String userId = httpRequest.getParam("userId");
		Aio.bindUser(channelContext, userId);
		System.err.println("============用户绑定成功,当前绑定的用户ID=" + userId + "============");

		// 服务器返回信息,响应提示用户连接成功
		String msg = "欢迎您登录法义聊天室,亲爱的用户:" + userId;
		ToMessage tomessage = new ToMessage("系统提示", "http://tva2.sinaimg.cn/crop.0.0.180.180.180/5033b6dbjw1e8qgp5bmzyj2050050aa8.jpg", "1000", "friend", msg);
		String pushMsg = JSONUtil.toJsonPrettyStr(tomessage);
		WsResponse wsResponse = WsResponse.fromText(pushMsg, ShowcaseServerConfig.CHARSET);
		//WsResponse wsResponse = WsResponse.fromText(msg, ShowcaseServerConfig.CHARSET);

		// 发送给指定用户
		
		boolean result = Aio.sendToUser(channelContext.getGroupContext(), userId, wsResponse);
		System.out.println("登录上线时消息发送结果:"+result);

		// 统计当前在线人数
		int count = Aio.getAllChannelContexts(channelContext.getGroupContext()).getObj().size();
		System.err.println(userId + " 进来了，现在共有【" + count + "】人在线");

	}

	/**
	 * 字节消息（binaryType = arraybuffer）过来后会走这个方法
	 */
	@Override
	public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
		return null;
	}

	/**
	 * 当客户端发close flag时，会走这个方法
	 */
	@Override
	public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
		Aio.remove(channelContext, "receive close flag");
		return null;
	}

	/*
	 * 字符消息（binaryType = blob）过来后会走这个方法
	 */
	@Override
	public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
		WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
		HttpRequest httpRequest = wsSessionContext.getHandshakeRequestPacket();// 获取websocket握手包

		log.info("收到ws消息:{}", text);
		if (Objects.equals("@heart", text) || Objects.equals("心跳内容", text)) {
			return null;
		}
		
		try {
			// 解析为实体对象
			FromMessage message = JSONUtil.toBean(text, FromMessage.class);
			// 发送给好友的单点消息
			To to = message.getTo();
			Mine mine = message.getMine();
			if(Objects.equals("friend", to.getType())) {
				ToMessage tomessage = new ToMessage(mine.getUsername(), mine.getAvatar(), mine.getId(), to.getType(), mine.getContent());
				String pushMsg = JSONUtil.toJsonPrettyStr(tomessage);
				WsResponse wsResponse = WsResponse.fromText(pushMsg, ShowcaseServerConfig.CHARSET);
				boolean result = Aio.sendToUser(channelContext.getGroupContext(), to.getId(), wsResponse);
				System.err.println("单聊信息消息发送结果:"+result);
				if(result) {
					System.err.println("消息发送成功");
				}else {
					System.err.println("消息发送失败");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("消息发送失败:"+e.getMessage());
		}
		
		return null;
	}

}

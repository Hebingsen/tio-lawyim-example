var MsgHandler = function() {
	this.onopen = function(event, ws) {
		console.log("连接成功");
	}

	/**
	 * 收到服务器发来的消息
	 * @param {*} event 
	 * @param {*} ws 
	 */
	this.onmessage = function(event, ws) {
		var data = JSON.parse(event.data);
		console.log(data);
		window.layui.layim.getMessage(data);
	}

	this.onclose = function(e, ws) {
		console.log("连接关闭");
	}

	this.onerror = function(e, ws) {
		console.log("连接错误");
		// error(e, ws)
	}

	/**
	 * 发送心跳，本框架会自动定时调用该方法，请在该方法中发送心跳
	 * @param {*} ws 
	 */
	this.ping = function(ws) {
		// log("发心跳了")
		ws.send('心跳内容')
	}
}
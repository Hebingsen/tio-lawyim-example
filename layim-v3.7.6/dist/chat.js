var tiows = {
	ws_protocol : 'ws',
	ip : '127.0.0.1',
	port : 9326,
	heartbeatTimeout : 5000,// 心跳超时时间，单位：毫秒
	reconnInterval : 1000,// 重连间隔时间，单位：毫秒
	binaryType : 'blob',// 'blob' or 'arraybuffer';//arraybuffer是字节
	initWs : function(userId){
		var queryString = 'userId='+userId;
		var param = null;
		var handler = new MsgHandler();
		this.tiows = new tio.ws(this.ws_protocol, this.ip, this.port, queryString, 
			this.param, handler, this.heartbeatTimeout, this.reconnInterval, this.binaryType);
		this.tiows.connect();
	},
	// 发送消息
	send : function(data){
		console.log("准备发送消息:"+data.mine.content)
		this.tiows.send(JSON.stringify(data));
	},
	getUserIdByUrl : function(){
		var name = "userId";
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
        var r = window.location.search.substr(1).match(reg); 
        if (r != null) return unescape(r[2]); 
        return null; 
	}
}

//function initWs () {
////var queryString = 'name=科比&name=科比&name=库里'
//var queryString = 'userId=1111'
//var param = null
//tiows = new tio.ws(ws_protocol, ip, port, queryString, param, handler, heartbeatTimeout, reconnInterval, binaryType)
//tiows.connect()
//}



//function send () {
////var msg = document.getElementById('textId')
////tiows.send(msg.value)
//}
//
//function clearMsg () {
////document.getElementById('contentId').innerHTML = ''
//}
//
//initWs()
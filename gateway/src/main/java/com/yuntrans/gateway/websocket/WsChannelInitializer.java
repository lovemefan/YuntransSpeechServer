/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 21:03
 */
package com.yuntrans.gateway.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.timeout.IdleStateHandler;

class WsChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final int IDLE_TIMEOUT_DISABLED = 0;

    private static final int IDLE_TIMEOUT = 10;

    private final WsServer server;

    WsChannelInitializer(WsServer wsServer) {
        this.server = wsServer;
    }

    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        p.addLast((ChannelHandler)new IdleStateHandler(0, 0, 10));
        p.addLast((ChannelHandler)new HttpServerCodec());
        p.addLast((ChannelHandler)new HttpObjectAggregator(65536));
        p.addLast((ChannelHandler)new WsAuthHandler(this.server));
        p.addLast((ChannelHandler)new WebSocketServerCompressionHandler());
        p.addLast((ChannelHandler)new WebSocketServerProtocolHandler(this.server.config.getPath(), null, true, 65536, false, true));
        p.addLast((ChannelHandler)new WsFrameHandler(this.server));
    }
}


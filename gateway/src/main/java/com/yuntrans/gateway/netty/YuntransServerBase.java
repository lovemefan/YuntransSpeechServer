package com.yuntrans.gateway.netty;
import com.yuntrans.common.utils.YuntransThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.util.concurrent.ThreadFactory;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Slf4j
public class YuntransServerBase {

    private static final String BOSS_THREAD_NAME_PREFIX = "-boss-";

    private static final String WORKER_THREAD_NAME_PREFIX = "-worker-";

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private Channel channel;

    protected void start(YuntransServerOptions options) throws Exception {
        options.check();
        this.bossGroup = (EventLoopGroup)new NioEventLoopGroup(1, (ThreadFactory)new YuntransThreadFactory(options.getName() + "-boss-"));
        this.workerGroup = (EventLoopGroup)new NioEventLoopGroup(options.getWorkerNum(), (ThreadFactory)new YuntransThreadFactory(options.getName() + "-worker-"));
        ServerBootstrap b = new ServerBootstrap();
        ((ServerBootstrap)b.group(this.bossGroup, this.workerGroup)
                .channel(NioServerSocketChannel.class))
                .childHandler((ChannelHandler)options.getChannelInitializer())
                .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE);
        if (options.getRecvByteBufAllocator() != null)
            b.childOption(ChannelOption.RCVBUF_ALLOCATOR, options.getRecvByteBufAllocator());
        if (options.getWriteBufferWaterMark() != null)
            b.childOption(ChannelOption.WRITE_BUFFER_WATER_MARK, options.getWriteBufferWaterMark());
        this.channel = b.bind(options.getAddress(), options.getPort()).sync().channel();
        log.info("Started Yuntrans stream server successfully!");
    }

    protected void stop() {
        log.info("Closing tcp listen channel");
        if (this.channel != null)
            this.channel.close();
        log.info("Stopping boss event loop group");
        if (this.bossGroup != null)
            this.bossGroup.shutdownGracefully();
        log.info("Stopping worker event loop group");
        if (this.workerGroup != null)
            this.workerGroup.shutdownGracefully();
        log.info("Stopped server");
    }
}

package io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    public static void main(String[] args) throws Exception {

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap  bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)//配置主从Reactor
                    .channel(NioSocketChannel.class)//配置主Reactor中的channel类型
                    .handler(new ChannelInitializer<SocketChannel>() {//设置从Reactor中注册channel的pipeline
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            //p.addLast(new LoggingHandler(LogLevel.INFO));
                        }
                    });

            // Start the server. 绑定端口启动服务，开始监听accept事件
            ChannelFuture f = bootstrap.connect("localhost", PORT).sync();
            f.channel().writeAndFlush("hello");
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}

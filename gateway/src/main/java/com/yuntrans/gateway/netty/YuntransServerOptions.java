package com.yuntrans.gateway.netty;
import com.yuntrans.common.utils.StringUtils;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.socket.SocketChannel;

public class YuntransServerOptions {
    private String name;

    private String address;

    private int port;

    private int workerNum;

    private ChannelInitializer<SocketChannel> channelInitializer;

    private RecvByteBufAllocator recvByteBufAllocator;

    private WriteBufferWaterMark writeBufferWaterMark;

    public YuntransServerOptions(String name, String address, int port, int workerNum, ChannelInitializer<SocketChannel> channelInitializer, RecvByteBufAllocator recvByteBufAllocator, WriteBufferWaterMark writeBufferWaterMark) {
        this.name = name;
        this.address = address;
        this.port = port;
        this.workerNum = workerNum;
        this.channelInitializer = channelInitializer;
        this.recvByteBufAllocator = recvByteBufAllocator;
        this.writeBufferWaterMark = writeBufferWaterMark;
    }

    public static YuntransServerOptionsBuilder builder() {
        return new YuntransServerOptionsBuilder();
    }

    public static class YuntransServerOptionsBuilder {
        private String name;

        private String address;

        private int port;

        private int workerNum;

        private ChannelInitializer<SocketChannel> channelInitializer;

        private RecvByteBufAllocator recvByteBufAllocator;

        private WriteBufferWaterMark writeBufferWaterMark;

        public YuntransServerOptionsBuilder name(String name) {
            this.name = name;
            return this;
        }

        public YuntransServerOptionsBuilder address(String address) {
            this.address = address;
            return this;
        }

        public YuntransServerOptionsBuilder port(int port) {
            this.port = port;
            return this;
        }

        public YuntransServerOptionsBuilder workerNum(int workerNum) {
            this.workerNum = workerNum;
            return this;
        }

        public YuntransServerOptionsBuilder channelInitializer(ChannelInitializer<SocketChannel> channelInitializer) {
            this.channelInitializer = channelInitializer;
            return this;
        }

        public YuntransServerOptionsBuilder recvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
            this.recvByteBufAllocator = recvByteBufAllocator;
            return this;
        }

        public YuntransServerOptionsBuilder writeBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
            this.writeBufferWaterMark = writeBufferWaterMark;
            return this;
        }

        public YuntransServerOptions build() {
            return new YuntransServerOptions(this.name, this.address, this.port, this.workerNum, this.channelInitializer, this.recvByteBufAllocator, this.writeBufferWaterMark);
        }

        public String toString() {
            return "YuntransServerOptions.YuntransServerOptionsBuilder(name=" + this.name + ", address=" + this.address + ", port=" + this.port + ", workerNum=" + this.workerNum + ", channelInitializer=" + this.channelInitializer + ", recvByteBufAllocator=" + this.recvByteBufAllocator + ", writeBufferWaterMark=" + this.writeBufferWaterMark + ")";
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setWorkerNum(int workerNum) {
        this.workerNum = workerNum;
    }

    public void setChannelInitializer(ChannelInitializer<SocketChannel> channelInitializer) {
        this.channelInitializer = channelInitializer;
    }

    public void setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
        this.recvByteBufAllocator = recvByteBufAllocator;
    }

    public void setWriteBufferWaterMark(WriteBufferWaterMark writeBufferWaterMark) {
        this.writeBufferWaterMark = writeBufferWaterMark;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof YuntransServerOptions))
            return false;
        YuntransServerOptions other = (YuntransServerOptions)o;
        if (!other.canEqual(this))
            return false;
        Object thisName = getName(), otherName = other.getName();
        if ((thisName == null) ? (otherName != null) : !thisName.equals(otherName))
            return false;
        Object thisAddress = getAddress(), otherAddress = other.getAddress();
        if ((thisAddress == null) ? (otherAddress != null) : !thisAddress.equals(otherAddress))
            return false;
        if (getPort() != other.getPort())
            return false;
        if (getWorkerNum() != other.getWorkerNum())
            return false;
        ChannelInitializer<SocketChannel> thisChannelInitializer = (ChannelInitializer<SocketChannel>)getChannelInitializer(), otherChannelInitializer = (ChannelInitializer<SocketChannel>)other.getChannelInitializer();
        if ((thisChannelInitializer == null) ? (otherChannelInitializer != null) : !thisChannelInitializer.equals(otherChannelInitializer))
            return false;
        Object thisRecvByteBufAllocator = getRecvByteBufAllocator(), otherRecvByteBufAllocator = other.getRecvByteBufAllocator();
        if ((thisRecvByteBufAllocator == null) ? (otherRecvByteBufAllocator != null) : !thisRecvByteBufAllocator.equals(otherRecvByteBufAllocator))
            return false;
        Object thisWriteBufferWaterMark = getWriteBufferWaterMark(), otherWriteBufferWaterMark = other.getWriteBufferWaterMark();
        return !((thisWriteBufferWaterMark == null) ? (otherWriteBufferWaterMark != null) : !thisWriteBufferWaterMark.equals(otherWriteBufferWaterMark));
    }

    protected boolean canEqual(Object other) {
        return other instanceof YuntransServerOptions;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String name = getName();
        result = result * 59 + ((name == null) ? 43 : name.hashCode());
        String address = getAddress();
        result = result * 59 + ((address == null) ? 43 : address.hashCode());
        result = result * 59 + getPort();
        result = result * 59 + getWorkerNum();
        ChannelInitializer<SocketChannel> channelInitializer = (ChannelInitializer<SocketChannel>)getChannelInitializer();
        result = result * 59 + ((channelInitializer == null) ? 43 : channelInitializer.hashCode());
        RecvByteBufAllocator recvByteBufAllocator = getRecvByteBufAllocator();
        result = result * 59 + ((recvByteBufAllocator == null) ? 43 : recvByteBufAllocator.hashCode());
        WriteBufferWaterMark writeBufferWaterMark = getWriteBufferWaterMark();
        return result * 59 + ((writeBufferWaterMark == null) ? 43 : writeBufferWaterMark.hashCode());
    }

    public String toString() {
        return "YuntransServerOptions(name=" + getName() + ", address=" + getAddress() + ", port=" + getPort() + ", workerNum=" + getWorkerNum() + ", channelInitializer=" + getChannelInitializer() + ", recvByteBufAllocator=" + getRecvByteBufAllocator() + ", writeBufferWaterMark=" + getWriteBufferWaterMark() + ")";
    }

    public YuntransServerOptions() {}

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public int getPort() {
        return this.port;
    }

    public int getWorkerNum() {
        return this.workerNum;
    }

    public ChannelInitializer<SocketChannel> getChannelInitializer() {
        return this.channelInitializer;
    }

    public RecvByteBufAllocator getRecvByteBufAllocator() {
        return this.recvByteBufAllocator;
    }

    public WriteBufferWaterMark getWriteBufferWaterMark() {
        return this.writeBufferWaterMark;
    }

    void check() throws Exception {
        if (StringUtils.nullOrEmpty(this.name))
            throw new Exception("The server name can't be empty!");
        if (StringUtils.nullOrEmpty(this.address))
            throw new Exception("The server address can't be empty!");
        if (this.port <= 0)
            throw new Exception("The server port is invalid!");
        if (this.workerNum <= 0)
            throw new Exception("The worker num is invalid!");
        if (this.channelInitializer == null)
            throw new Exception("The channel initializer can't be null!");
    }
}

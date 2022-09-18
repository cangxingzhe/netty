package io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import org.junit.jupiter.api.Test;

public class ByteBufTest {

    @Test
    void test() {
        ByteBufAllocator allocator = ByteBufAllocator.DEFAULT;
        ByteBuf byteBuf = allocator.directBuffer();
        byteBuf.release();
    }
}

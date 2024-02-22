import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        //create a selector

        try{
            Selector selector= Selector.open();

            ServerSocketChannel serverChannel=ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress("localhost",5454));

            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer buffer= ByteBuffer.allocate(256);

            System.out.println("Server Started and listening to port 5454");

            while(true)
            {
                selector.select();
                Set<SelectionKey> selectedKeys=selector.selectedKeys();
                Iterator<SelectionKey> iter=selectedKeys.iterator();
                while(iter.hasNext()){
                    SelectionKey key=iter.next();
                    if(key.isAcceptable()){
                       ServerSocketChannel server =(ServerSocketChannel) key.channel();
                       SocketChannel clientChannel = server.accept();

                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);

                    } else if(key.isReadable()){
                         SocketChannel client =(SocketChannel) key.channel();
                         int bytesRead = client.read(buffer);
                         if(bytesRead == -1){
                             key.cancel();
                             client.close();
                             continue;
                         }
                         buffer.flip();
                         String receivedMessage = new String(buffer.array());
                         System.out.println("Message Received :"+ receivedMessage);

                         client.write(buffer);

                    }
                }
            }


        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }
}

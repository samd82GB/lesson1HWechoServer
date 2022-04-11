import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
       public static ByteBuffer byteBuffer = ByteBuffer.allocate(256);

       public static void main(String[] args) {

         new Client().start();

        }

        public void start() {

                //new Thread(() -> {
                    System.out.println("New client started on thread " + Thread.currentThread().getName());
                    try {
                        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 9000));
                        while (true) {
                            channel.write(ByteBuffer.wrap(String.format(
                                    "[%s] Message from thread %s\n",
                                    LocalDateTime.now(),
                                    Thread.currentThread().getName()
                            ).getBytes()));
                            Thread.sleep(3000);
                            channel.read(byteBuffer);
                            String message = new String(byteBuffer.array());
                            System.out.println(message);
                            byteBuffer.clear();
                        }



                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }//);
            }
      // }




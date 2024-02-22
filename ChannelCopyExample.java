import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopyExample {
    public static void main(String[] args) {
        String sourceFile = "source.txt";
        String destinationFile = "destination.txt";

        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destinationFile);
             FileChannel sourceChannel = fis.getChannel();
             FileChannel destChannel = fos.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(1024); // Allocate a buffer of 1KB

            while (sourceChannel.read(buffer) != -1) {
                buffer.flip(); // Prepare the buffer for writing
                destChannel.write(buffer); // Write from buffer to destination channel
                buffer.clear(); // Clear the buffer for next read
            }

            System.out.println("File copied successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

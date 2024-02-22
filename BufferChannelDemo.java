import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.RemoteException;

public class BufferChannelDemo {
    public static void main(String[] args) throws IOException {
        String FileName="testfile.txt";
        Path filePath= Paths.get(FileName);

        writeFile(filePath);
        readFile(filePath);

        FileInputStream in=new FileInputStream("file1.txt");
        FileChannel source=in.getChannel();
    }

    private static void writeFile(Path filePath) throws IOException {
        String input="This text will be written from java code";
        System.out.println("Writing Text:"+ filePath.getFileName()+" :: "+input);
        byte[] inputBytes=input.getBytes();

        ByteBuffer writer = ByteBuffer.wrap(inputBytes);

        FileChannel writeChannel=null;

        try{
            writeChannel = FileChannel.open(filePath, StandardOpenOption.CREATE , StandardOpenOption.WRITE);
            int numberOfBytesWritten=writeChannel.write(writer);
        }catch (IOException e)
        {
            throw new IOException();
        }
        finally {
            try{
                writeChannel.close();
            }
            catch(IOException e){
                throw new RuntimeException(e);

            }

        }
    }

    private static void readFile(Path filePath) throws IOException {
        FileChannel readChannel=null;
        try{
            readChannel= FileChannel.open(filePath);
            ByteBuffer readBuffer=ByteBuffer.allocate(Math.toIntExact(readChannel.size()));
            readChannel.read(readBuffer);

            byte[] bytes=readBuffer.array();
            String output = new String(bytes);
            System.out.println("Text from File : "+filePath.getFileName()+" :: "+output);
        }
        catch(IOException e){
            throw new RemoteException();
        }
        finally {
            try{
                readChannel.close();
            }
            catch(IOException e){
                throw new RuntimeException(e);

            }

        }

    }
}


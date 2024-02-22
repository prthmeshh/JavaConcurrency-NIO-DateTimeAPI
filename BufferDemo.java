import java.nio.CharBuffer;

public class BufferDemo {
    public static void main(String[] args) {
        CharBuffer buffer=CharBuffer.allocate(16);
        String text= "example";
        System.out.println("Input text: "+text);

       // Writing to buffer
        for(int i=0;i<text.length();i++)
        {
            char c=text.charAt(i);
            buffer.put(c);
        }

        System.out.println("Position after data is written : "+buffer.position());
        buffer.flip();
        System.out.println("Position after flip : "+buffer.position());
        //Reading from buffer
        while(buffer.hasRemaining()){
            System.out.print(buffer.get());

        }


    }
}


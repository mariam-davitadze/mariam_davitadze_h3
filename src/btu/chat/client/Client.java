package btu.chat.client;

import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 8080);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        String generatedText;
        System.out.println("მიმდინარეობს კავშირის დამყარება...");

        while (true) {
            System.out.println("დასვი კითხვა:");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();

            objectOutputStream.writeObject(text);
            generatedText = (String) objectInputStream.readObject();

            System.out.println("პასუხი: " + generatedText);

            if (generatedText.contains("ნახვამდის")) {
                System.out.println("კავშირის დასასრული !!!");
                break;
            };
        }
        objectInputStream.close();
        objectOutputStream.close();
        socket.close();
    }
}
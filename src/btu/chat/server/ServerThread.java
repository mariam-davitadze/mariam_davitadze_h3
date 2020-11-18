package btu.chat.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String inputedText;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                inputedText = (String) objectInputStream.readObject();
                switch (inputedText) {
                    case "გამარჯობა" -> objectOutputStream.writeChar("მოგესალმებით, რით შემიძლია დაგეხმაროთ?");
                    case "მაჩვენე კურსი" -> objectOutputStream.writeChar("2.97");
                    case "მაჩვენე ფილიალები" -> objectOutputStream.writeChar("[ი.ჭავჭავაძის გამზ., ვაჟა-ფშაველას გამზ., გურამიშვილის გამზ.]");
                    case "ნახვამდის" -> {
                        objectOutputStream.writeChar("გმადლობთ რომ დაგვიკავშირდით, ნახვამდის !");
                        objectInputStream.close();
                        objectOutputStream.close();
                        socket.close();
                    }
                    default -> objectOutputStream.writeChar("სამწუხაროდ ამ კითხვაზე პასუხი არ მაქვს.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
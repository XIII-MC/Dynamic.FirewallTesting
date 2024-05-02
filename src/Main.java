import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final String runMode = getUserInput("Is this 'SERVER' or 'CLIENT' ?");

        if (runMode.equals("SERVER")) {

            final int startPort = Integer.parseInt(getUserInput("Start port: "));
            final int maxPorts = Integer.parseInt(getUserInput("Max ports: "));

            for (int i = startPort; i <= maxPorts; i++) {

                int finalI = i;
                new Thread(() -> {

                    try {
                        final ServerSocket serverSocket = new ServerSocket(finalI);

                        final Socket socket = serverSocket.accept();

                        // Display message
                        System.out.println(
                                "Client accepted through the port number: "
                                        + serverSocket.getLocalPort());
                    } catch (final Exception ignored) {}
                }).start();
            }

            System.out.println("All ports ready.");

            Thread.sleep(999999999);

        } else if (runMode.equals("CLIENT")) {

            final String serverIP = getUserInput("Server IP address: ");

            for (int i = 1; i <= 65535; i++) {
                try {
                    new Socket().connect(new InetSocketAddress(serverIP, i));
                    System.out.println(i + "/TCP");
                } catch (final IOException ignored) {}
            }
        }
    }

    public static String getUserInput(final String text) {
        final Scanner userInputScanner = new Scanner(System.in);
        System.out.println(text);
        return userInputScanner.nextLine();
    }
}
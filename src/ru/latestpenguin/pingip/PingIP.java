package ru.latestpenguin.pingip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class PingIP {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("MyLog");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String ip = null;
        int quantity = 0;


        try {
            System.out.println("Введите адрес:");
            ip = bufferedReader.readLine();

            // TODO сделать ограничение по времени/дате
            System.out.println("Сколько раз отправить запрос");
            quantity = Integer.parseInt(bufferedReader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }

        runPing(ip, logger, quantity);

    }

    private static void runPing(String ipAddress, Logger logger, int quantity) {

        SimpleDateFormat formatDaysTime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        try {
            FileHandler fh = new FileHandler("D:/MyLogFile.log");
            ConsoleHandler handler = new ConsoleHandler();

            logger.addHandler(fh);
            logger.addHandler(handler);
            logger.setUseParentHandlers(false);

            MyFormatter formatter = new MyFormatter();

            fh.setFormatter(formatter);
            handler.setFormatter(formatter);

            InetAddress pingIP = InetAddress.getByName(ipAddress);
            System.out.println("Ping до адреса: " + ipAddress);


            while (0 < quantity) {
                boolean status = pingIP.isReachable(2000); //Timeout = 2 second

                if (status) {
                    logger.info("Host is reachable");
                } else {
                    logger.info("Host is not reachable");
                }
                quantity--;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

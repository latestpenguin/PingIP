package ru.latestpenguin.pingip;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class PingIP {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("MyLog");

        String ip = "127.0.0.1";
        runPing(ip, logger);

    }

    private static void runPing(String ipAddress, Logger logger) {
        int i = 0;

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


            while (i < 100) {
                boolean status = pingIP.isReachable(2000); //Timeout = 2 second

                if (status) {
                    logger.info("Host is reachable");
                } else {
                    logger.info("Host is not reachable");
                }
                i++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package edu.hw6.task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task6 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<Integer, String> POTENTIAL_SERVICES = Map.of(
        80, "HTTP (HyperText Transfer Protocol); ранее — WWW",
        97, "SWIFT-RVF (Swift Remote Virtural File Protocol)",
        137, "Служба имен NetBIOS",
        147, "ISO-IP",
        359, "NSRMP (Network Security Risk Management Protocol)",
        443, "HTTPS (HyperText Transfer Protocol Secure) — HTTP с шифрованием по SSL или TLS",
        843, "Adobe Flash",
        3702, "Динамическое обнаружение веб-служб",
        5353, "Многоадресный DNS",
        47808, "BACnet Building Automation and Control Networks"
    );

    private enum Protocol {
        TCP, UDP
    }

    private enum Status {
        BUSY, FREE
    }

    private Task6() {
    }

    @SuppressWarnings("MagicNumber")
    public static void scanPorts() {
        LOGGER.info("Протокол  Порт  Статус  Сервис");
        for (int port = 0; port <= 49151; port++) {
            if (scanTcpPort(port) == Status.BUSY) {
                formattedPrint(Protocol.TCP, port);
                continue;
            }
            if (scanUdpPort(port) == Status.BUSY) {
                formattedPrint(Protocol.UDP, port);
            }
        }
    }

    private static Status scanTcpPort(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return Status.FREE;
        } catch (Exception e) {
            return Status.BUSY;
        }
    }

    private static Status scanUdpPort(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return Status.FREE;
        } catch (Exception e) {
            return Status.BUSY;
        }
    }

    private static void formattedPrint(Protocol protocol, int port) {
        String service = POTENTIAL_SERVICES.getOrDefault(port, "");
        LOGGER.info(String.format("%-10s %-6d %s\n", protocol.toString(), port, service));
    }
}

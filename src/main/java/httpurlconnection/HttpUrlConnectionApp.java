package httpurlconnection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class HttpUrlConnectionApp {

    public static void main(String[] args) throws IOException {
        var url = new URL("https://ifconfig.me/ip");
        var proxyAddress = new InetSocketAddress("127.0.0.1", 5555);

        var proxy = new Proxy(Proxy.Type.SOCKS, proxyAddress);
        var connection = (URLConnection) url.openConnection(proxy);

        try (var inputStream = connection.getInputStream()) {
            var bytes = inputStream.readAllBytes();
            var content = new String(bytes);
            System.out.println("IP: " + content);
        }
    }
}

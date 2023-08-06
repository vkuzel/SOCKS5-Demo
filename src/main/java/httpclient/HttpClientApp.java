package httpclient;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpClientApp {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        // !!! SOCKS PROXIES ARE NOT SUPPORTED !!!
        //
        // Even though API allows us to implement a custom ProxySelect, the
        // provided SOCKS proxy gets silently ignored in the
        // HttpRequestImpl.retrieveProxy() method.
        //
        // Reported bug: https://bugs.openjdk.org/browse/JDK-8214516
        var uri = new URI("https://ifconfig.me/ip");
        var proxyAddress = new InetSocketAddress("127.0.0.1", 1080);

        var proxySelector = createProxySelector(proxyAddress);
        var client = HttpClient.newBuilder().proxy(proxySelector).build();

        var request = HttpRequest.newBuilder(uri).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("IP: " + response.body());
        throw new RuntimeException("SOCKS proxy is not supported, the request was sent directly!");
    }

    private static ProxySelector createProxySelector(InetSocketAddress proxyAddress) {
        return new ProxySelector() {

            @Override
            public List<Proxy> select(URI uri) {
                return List.of(new Proxy(Proxy.Type.SOCKS, proxyAddress));
            }

            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                throw new RuntimeException("Connect failed!");
            }
        };
    }
}

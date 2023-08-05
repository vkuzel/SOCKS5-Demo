package apachehttpclient5;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.HttpsSupport;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.ssl.SSLContexts;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

public class ApacheHttpClient5App {

    public static void main(String[] args) throws IOException {
        var url = "https://ifconfig.me/ip";
        var proxyAddress = new InetSocketAddress("127.0.0.1", 5555);

        // The configuration channels ONLY HTTPS connections through the proxy.
        //
        // For handling HTTP connections an additional plain socket factory has
        // to be configured. Unfortunately, this is not supported by the builder,
        // so the connection manager has to be instantiated manually.
        //
        // See PoolingHttpClientConnectionManagerBuilder.build() for more details.
        var connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(createSSLConnectionSocketFactory(proxyAddress))
                .build();
        try (var client = HttpClients.custom().setConnectionManager(connectionManager).build()) {

            var httpGet = new HttpGet(url);
            var response = client.execute(httpGet, new BasicHttpClientResponseHandler());
            System.out.println("IP: " + response);
        }
    }

    private static SSLConnectionSocketFactory createSSLConnectionSocketFactory(InetSocketAddress proxyAddress) {
        return new SSLConnectionSocketFactory(SSLContexts.createDefault(), HttpsSupport.getDefaultHostnameVerifier()) {
            @Override
            public Socket createSocket(HttpContext context) {
                return new Socket(new Proxy(Proxy.Type.SOCKS, proxyAddress));
            }
        };
    }
}

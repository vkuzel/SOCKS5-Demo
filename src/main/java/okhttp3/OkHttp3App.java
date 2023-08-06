package okhttp3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Objects;

public class OkHttp3App {

    public static void main(String[] args) throws IOException {
        var url = "https://ifconfig.me/ip";
        var proxyAddress = new InetSocketAddress("127.0.0.1", 1080);

        var proxy = new Proxy(Proxy.Type.SOCKS, proxyAddress);
        // Due to Kotlin's field/function name conflicts I am unable to use
        // builder's fluent API. When used from Kotlin, the implementation
        // look nicer.
        var builder = new OkHttpClient.Builder();
        builder.setProxy$okhttp(proxy);
        var client = builder.build();

        var request = new Request.Builder().url(url).build();
        try (var response = client.newCall(request).execute()) {
            var body = response.body();
            Objects.requireNonNull(body);
            System.out.println("IP: " + body.string());
        }
    }
}

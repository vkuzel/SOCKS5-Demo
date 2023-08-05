# SOCKS5 Proxy Demo

Examples of using SOCKS v5 proxy from Java.

## Setup

Use ssh with parameter `-D` to act as a SOCKS server.
```shell
ssh -D 5555 100.100.100.100
```

Test your proxy server with `curl`.
```shell
curl --proxy socks5://localhost:5555 https://ifconfig.me/ip
```

## Examples

1. [Global proxy setting](https://docs.oracle.com/javase/8/docs/technotes/guides/net/proxies.html) for all outgoing Socket connections.
    ```shell
    java -DsocksProxyHost=localhost -DsocksProxyPort=5555 MyApp
    ```
2. [`java.net.HttpUrlConnection`](src/main/java/httpurlconnection/HttpUrlConnectionApp.java)

3. [`java.net.http.HttpClient`](src/main/java/httpclient/HttpClientApp.java)

   **The native Java 11 HttpClient cannot use SOCKS proxies at the moment!**

4. [OkHttp 3](src/main/java/okhttp3/OkHttp3App.java)
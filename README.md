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

1. [Global setting](https://docs.oracle.com/javase/8/docs/technotes/guides/net/proxies.html) for all outgoing Socket connections.
    ```shell
    java -DsocksProxyHost=localhost -DsocksProxyPort=5555 MyApp
    ```
2. [`java.net.HttpUrlConnection`](src/main/java/httpurlconnection/HttpUrlConnectionApp.java)

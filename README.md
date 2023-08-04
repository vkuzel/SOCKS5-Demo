# SOCKS5 Proxy Demo

Various ways of using SOCKS v5 proxy from Java.

## Setup

Use ssh to act as a SOCKS server by connecting to a machine with parameter `-D`.
```shell
ssh -D 5555 100.100.100.100
```

Test your proxy server with `curl`.
```shell
curl --proxy socks5://localhost:5555 https://ifconfig.me/ip
```

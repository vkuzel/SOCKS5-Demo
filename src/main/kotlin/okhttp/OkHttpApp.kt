package okhttp

import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.InetSocketAddress
import java.net.Proxy

fun main() {
    val url = "https://ifconfig.me/ip"
    val proxyAddress = InetSocketAddress("127.0.0.1", 5555)

    val proxy = Proxy(Proxy.Type.SOCKS, proxyAddress)
    val client = OkHttpClient.Builder().proxy(proxy).build()

    val request = Request.Builder().url(url).build()
    client.newCall(request).execute().use { response ->
        println("IP: ${response.body?.string()}")
    }
}
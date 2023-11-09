package com.autumnstudios.plugins.glass.tcp

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

object RelayServer {

    private lateinit var server: ServerSocket

    private var relisten: Boolean = false

    fun configure(relisten: Boolean) {
        RelayServer.relisten = true;
    }

    fun create() {
        server = ServerSocket(69)
        println("Server is running on port ${server.localPort}")

        listen()
    }

    fun end() {
        server.close()
    }

    fun listen() = runBlocking {
        launch {
            val client = server.accept()
            println("Client connected: ${client.inetAddress.hostAddress}")

            val input = BufferedReader(InputStreamReader(client.inputStream))
            val output = PrintWriter(client.outputStream, true)

            val message = input.readLine()
            println("Client says: $message")

            output.println("Hello from Kotlin server!")

            client.close()
        }

        relisten()

    }

    fun relisten() {
        if (relisten) {
            listen()
        }
    }
}
package com.grydtech.peershare.distributed.models;

import org.springframework.beans.factory.annotation.Value;

public class Node {

    private int initialTTL;

    private final String host;
    private final Integer port;
    private final Integer udpPort;
    private final String username;

    @Value("${node.ttl}")
    private int TTL;

    public Node(String host, Integer port) {
        this.host = host;
        this.port = port;

        this.udpPort = port + 10000;

        String s = getId().replace(".", "");
        this.username = s.substring(s.length() - 8);
    }

    public Node(String host, Integer port, String username) {
        this.host = host;
        this.port = port;
        this.username = username;

        this.udpPort = port + 10000;
    }

    public String getId() {
        return host + ":" + port;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public Integer getUdpPort() {
        return udpPort;
    }

    public String getUsername() {
        return username;
    }

    public void startTTL(int initialTTL) {
        this.initialTTL = initialTTL;
        this.TTL = initialTTL;
    }

    public void reduceTTL() {
        this.TTL--;
    }

    public void resetTTL() {
        this.TTL = this.initialTTL;
    }

    public boolean isTTLExpired() {
        return this.TTL <= 0;
    }
}

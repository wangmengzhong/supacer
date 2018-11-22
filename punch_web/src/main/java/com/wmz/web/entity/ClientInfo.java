package com.wmz.web.entity;

import java.util.Date;

public class ClientInfo {
    private String clientId;

    private Integer connected;

    private Long mostSignBits;

    private Long leastSignBits;

    private Date lastConnectedDate;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public Integer getConnected() {
        return connected;
    }

    public void setConnected(Integer connected) {
        this.connected = connected;
    }

    public Long getMostSignBits() {
        return mostSignBits;
    }

    public void setMostSignBits(Long mostSignBits) {
        this.mostSignBits = mostSignBits;
    }

    public Long getLeastSignBits() {
        return leastSignBits;
    }

    public void setLeastSignBits(Long leastSignBits) {
        this.leastSignBits = leastSignBits;
    }

    public Date getLastConnectedDate() {
        return lastConnectedDate;
    }

    public void setLastConnectedDate(Date lastConnectedDate) {
        this.lastConnectedDate = lastConnectedDate;
    }
}
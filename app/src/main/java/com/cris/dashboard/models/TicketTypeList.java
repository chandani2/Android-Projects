package com.cris.dashboard.models;

import java.io.Serializable;

public class TicketTypeList implements Serializable {
    String ticketId, month, journey, season, platform;

    public TicketTypeList(String ticketId, String month, String journey, String season, String platform) {
        this.ticketId = ticketId;
        this.month = month;
        this.journey = journey;
        this.season = season;
        this.platform = platform;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getJourney() {
        return journey;
    }

    public void setJourney(String journey) {
        this.journey = journey;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}

package it.unibz.archlab.digidojo.content.application.dto;

import lombok.Getter;
import lombok.Setter;

public class SimpleUUID {
    @Getter
    @Setter
    private String uuid;

    public SimpleUUID(String id) {
        uuid = id;
    }
}

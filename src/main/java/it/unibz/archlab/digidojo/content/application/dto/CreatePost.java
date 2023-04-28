package it.unibz.archlab.digidojo.content.application.dto;

import lombok.Getter;
import lombok.Setter;

public class CreatePost {
    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private String slug;
}

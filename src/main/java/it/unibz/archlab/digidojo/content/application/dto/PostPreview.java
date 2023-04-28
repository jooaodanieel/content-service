package it.unibz.archlab.digidojo.content.application.dto;

import it.unibz.archlab.digidojo.content.core.Post;
import lombok.Getter;
import lombok.Setter;

public class PostPreview {
    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String contentPreview;

    @Getter
    @Setter
    private String slug;

    public static PostPreview of(Post post) {
        PostPreview preview = new PostPreview();
        preview.setTitle(post.getTitle());
        preview.setSlug(post.getSlug());
        preview.setContentPreview(post.getContent().substring(0, 17) + "...");

        return preview;
    }
}

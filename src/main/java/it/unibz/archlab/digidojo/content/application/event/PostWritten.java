package it.unibz.archlab.digidojo.content.application.event;

import it.unibz.archlab.digidojo.content.core.Post;
import lombok.Getter;

public class PostWritten extends Event {
    public static final String POST_WRITTEN = "Post Written";

    @Getter
    private String type = POST_WRITTEN;

    @Getter
    private Post payload;

    public PostWritten(Post post) {
        payload = post;
    }

    public String toJson() {
        return "{" +
                    "\"type\": \""+type+"\"," +
                    "\"payload\": {" +
                        "\"uuid\": \""+payload.getId()+"\"," +
                        "\"title\": \""+payload.getTitle()+"\"," +
                        "\"content\": \""+payload.getContent()+"\"," +
                        "\"slug\": \""+payload.getSlug()+"\"" +
                    "}"+
                "}";
    }
}

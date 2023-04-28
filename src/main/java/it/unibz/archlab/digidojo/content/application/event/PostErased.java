package it.unibz.archlab.digidojo.content.application.event;

import it.unibz.archlab.digidojo.content.application.dto.SimpleUUID;
import it.unibz.archlab.digidojo.content.core.Post;
import lombok.Getter;

public class PostErased extends Event {
    public static final String POST_ERASED = "Post Erased";

    @Getter
    private String type = POST_ERASED;

    @Getter
    private SimpleUUID payload;

    public PostErased(Post post) {
        payload = new SimpleUUID(post.getId());
    }

    public String toJson() {
        return "{" +
                    "\"type\": \""+type+"\"," +
                    "\"payload\": {" +
                        "\"uuid\": \""+payload.getUuid()+"\"" +
                    "}"+
                "}";
    }
}

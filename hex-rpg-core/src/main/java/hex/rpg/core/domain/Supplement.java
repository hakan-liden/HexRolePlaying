package hex.rpg.core.domain;

import java.io.InputStream;

/**
 *
 * @author hln
 */
public interface Supplement extends NarrativeEntity, Comparable<Supplement> {

    public static final Type DEFAULT_CAMPAIGN_TYPE = Type.ILLUSTRATION,
            DEFAULT_STORY_TYPE = Type.MAP,
            DEFAULT_EPISODE_TYPE = Type.HANDOUT;
    public static final String BASE_PATH = "Supplements/";

    String getMediaType();

    void setMediaType(String mediaType);

    Type getType();

    void setType(Type type);

    InputStream getContent();

    byte[] getContentAsByteArray();

    void setContent(byte[] content);

    Integer getSize();
    
    String createPath();

    public enum Type {

        ILLUSTRATION, MAP, HANDOUT, OTHER;

    }
}

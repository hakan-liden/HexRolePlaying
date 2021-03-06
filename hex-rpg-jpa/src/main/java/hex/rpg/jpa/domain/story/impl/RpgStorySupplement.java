package hex.rpg.jpa.domain.story.impl;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.story.Story;
import hex.rpg.core.domain.story.StorySupplement;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hln
 */
@Entity
@Table(name = "StorySupplement")
public class RpgStorySupplement implements StorySupplement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column(length = 32 * Constants.KB)
    private String refereeInfo;
    @Column
    private Integer supplementIndex;
    @Column(length = Constants.KB)
    private String shortDescription;
    @Column(length = 32 * Constants.KB)
    private String description;
    @Column(length = 2 * Constants.KB)
    private String refereeNotes;
    @Column
    private String mediaType;
    @Lob
    @Column(length = 64 * Constants.MB)
    private byte[] content;
    @ManyToOne(targetEntity = RpgStory.class)
    private Story story;
    @Enumerated(EnumType.STRING)
    private Type type;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getParentId() {
        return getStory().getId();
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Integer getIndex() {
        return supplementIndex;
    }

    @Override
    public void setIndex(Integer index) {
        this.supplementIndex = index;
    }

    @Override
    public String getRefereeInfo() {
        return refereeInfo;
    }

    @Override
    public void setRefereeInfo(String refereeInfo) {
        this.refereeInfo = refereeInfo;
    }

    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getRefereeNotes() {
        return refereeNotes;
    }

    @Override
    public void setRefereeNotes(String refereeNotes) {
        this.refereeNotes = refereeNotes;
    }

    @Override
    public String getMediaType() {
        return mediaType;
    }

    @Override
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public InputStream getContent() {
        return new ByteArrayInputStream(content);
    }

    @Override
    public byte[] getContentAsByteArray() {
        return content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public Integer getSize() {
        return content.length;
    }

    @Override
    public List<Supplement> getSupplements() {
        throw new UnsupportedOperationException("N/A");
    }

    @Override
    public void addSupplement(Supplement supplement) {
        throw new UnsupportedOperationException("N/A");
    }

    @Override
    public Story getStory() {
        return story;
    }

    @Override
    public void setStory(Story story) {
        this.story = story;
    }

    @Override
    public String createPath() {
        String result = BASE_PATH + "Story/" + getStory().getTitle() + "/" + title;
        if (getFileExtension() != null) {
            result += "." + getFileExtension();
        }
        return result;
    }

    private String getFileExtension() {
        if (mediaType.startsWith("text")) {
            return "txt";
        } else if (mediaType.startsWith("image")) {
            return mediaType.substring(mediaType.lastIndexOf("/") + 1);
        } else if (mediaType.contains("+")) {
            return mediaType.substring(mediaType.lastIndexOf("+") + 1);
        }
        return null;
    }

    @Override
    public int compareTo(Supplement obj) {
        return this.getId().compareTo(((Supplement) obj).getId());
    }

    @Override
    public boolean hasSupplements() {
        return false;
    }
}

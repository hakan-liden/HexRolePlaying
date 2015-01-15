package hex.rpg.xml.pack.nodes;

import hex.rpg.core.domain.story.Episode;
import hex.rpg.xml.pack.AbstractRpgNode;
import hex.rpg.xml.HexRpgNode;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class EpisodeNode extends AbstractRpgNode<Episode> {

    public EpisodeNode(Episode episode) {
        super(episode);
    }

    @Override
    public XmlNode getXmlNode() {
        XmlNode result = buildNode(HexRpgNode.EPISODE);
        Episode episode = (Episode) entity();
        result.addAttribute("index", episode.getIndex());
        result.addChild(HexRpgNode.CONTENT.getXmlNode(episode.getContent()));
        return result;
    }

}
package xmlparser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.traversal.NodeFilter;

public class MyNodeFilter implements org.w3c.dom.traversal.NodeFilter {

    @Override
    public short acceptNode(Node n) {
        if (n.getNodeType() == Node.TEXT_NODE) {
            return NodeFilter.FILTER_SKIP;
        } else {

            return NodeFilter.FILTER_ACCEPT;
        }
    }

}

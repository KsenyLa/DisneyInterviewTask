package com.disney.interview;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XmlComparator {
    private static final String TAG = "Tag Name";
    private static final String VALUE = "Tag Value";

    public CompareResult compare(String xmlPath1, String xmlPath2) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
        Node xml1 = docBuilder.parse(new File(xmlPath1));
        Node xml2 = docBuilder.parse(new File(xmlPath2));
        CompareResult compareResult = new CompareResult();

        return compare(xml1, xml2, compareResult);
    }

    public CompareResult compare(Node node1, Node node2, CompareResult compareResult) throws Exception {
        compareValues(TAG, node1.getNodeName(), node2.getNodeName(), compareResult);
        compareAttributes(node1, node2, compareResult);

        NodeList childNodes1 = node1.getChildNodes();
        //if (node1.getNodeType() == Node.ELEMENT_NODE) {
        if (childNodes1 == null || childNodes1.getLength() == 0) {
            //compareValues(node1.getNodeName(), node1.getNodeValue(), node2.getNodeValue(), compareResult);
            compareValues(node1.getParentNode().getNodeName(), node1.getNodeValue(), node2.getNodeValue(), compareResult);
        } else {
            NodeList childNodes2 = node2.getChildNodes();
            if (childNodes1.getLength() != childNodes2.getLength()) {
                throw new Exception("Comparator can work only with XML files of same structure (same nodes in same order)");
            }
            for (int i = 0; i < childNodes1.getLength(); i++) {
                compare(childNodes1.item(i), childNodes2.item(i), compareResult);
            }
        }

        return compareResult;
    }

    private void compareAttributes(Node node1, Node node2, CompareResult compareResult) {
        Map<String, String> node1Map = toMap(node1.getAttributes());
        Map<String, String> node2Map = toMap(node2.getAttributes());

        // find attributes on node1 that not exists in node2 and add to differences
        node1Map.entrySet().stream()
                .filter(item -> !node2Map.keySet().contains(item.getKey()))
                .forEach(left -> compareResult.addDifference(
                        String.format("%s->%s", node1.getNodeName(), left.getKey()), left.getValue(), ""));

        // find attributes on node2 that not exists in node1 and add to differences
        node2Map.entrySet().stream()
                .filter(item -> !node1Map.keySet().contains(item.getKey()))
                .forEach(right -> compareResult.addDifference(
                        String.format("%s->%s", node2.getNodeName(), right.getKey()), "", right.getValue()));

        // find attributes that exists both sides and compare them all
        node1Map.entrySet().stream()
                .filter(item -> node2Map.keySet().contains(item.getKey()))
                .forEach(left -> compareValues(
                        String.format("%s->%s", node1.getNodeName(), left.getKey()), left.getValue(), node2Map.get(left.getKey()), compareResult));
    }

    private void compareValues(String field, String value1, String value2, CompareResult compareResult) {
        if (!value1.trim().equals(value2.trim())) {
            compareResult.addDifference(field, value1, value2);
        }
    }

    private Map<String, String> toMap(NamedNodeMap attrs) {
        Map<String, String> map = new HashMap<String, String>();
        if (attrs == null) {
            return map;
        }
        for (int j = 0; j < attrs.getLength(); j++) {
            Node attr = attrs.item(j);
            String attrName = attr.getNodeName();
            String val = attr.getNodeValue();
            map.put(attrName, val);
        }
        return map;
    }
}
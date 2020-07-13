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

    public CompareResult compare(String xmlPath1, String xmlPath2) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
        Node xml1 = docBuilder.parse(new File(xmlPath1));
        Node xml2 = docBuilder.parse(new File(xmlPath2));
        CompareResult compareResult = new CompareResult();

        return compare(xml1, xml2, compareResult);
    }

    private CompareResult compare(Node node1, Node node2, CompareResult compareResult) throws Exception {
        // Comparing tag names
        compareValues(TAG, node1.getNodeName(), node2.getNodeName(), compareResult);
        // Then compare all attributes in current nodes
        compareAttributes(node1, node2, compareResult);

        if (node1.getNodeType() == Node.TEXT_NODE) {
            // If current node is terminal node (text) then compare texts
            compareValues(node1.getParentNode().getNodeName(), node1.getNodeValue(), node2.getNodeValue(), compareResult);
        } else {
            // If current node is regular (not text) node then compare child nodes
            NodeList childNodes1 = node1.getChildNodes();
            NodeList childNodes2 = node2.getChildNodes();
            if (childNodes1.getLength() != childNodes2.getLength()) {
                throw new Exception("Comparator can work only with XML files of the same structure (same nodes in the same order)");
            }
            for (int i = 0; i < childNodes1.getLength(); i++) {
                // For every child node call the same compare method recursively
                compare(childNodes1.item(i), childNodes2.item(i), compareResult);
            }
        }

        return compareResult;
    }

    /**
     * Comparing attributes of both nodes
     * @param node1
     * @param node2
     * @param compareResult
     */
    private void compareAttributes(Node node1, Node node2, CompareResult compareResult) {
        // Convert attributes to map for using stream
        Map<String, String> node1Map = toMap(node1.getAttributes());
        Map<String, String> node2Map = toMap(node2.getAttributes());

        // Find attributes of node1 that doesn't not exist in node2 and add to differences
        node1Map.entrySet()
                .stream()
                .filter(item -> !node2Map.keySet().contains(item.getKey()))
                .forEach(left -> compareResult.addDifference(
                        String.format("%s->%s", node1.getNodeName(), left.getKey()),
                        left.getValue(),
                        ""));

        // Find attributes of node2 that doesn't not exist in node1 and add to differences
        node2Map.entrySet()
                .stream()
                .filter(item -> !node1Map.keySet().contains(item.getKey()))
                .forEach(right -> compareResult.addDifference(
                        String.format("%s->%s", node2.getNodeName(), right.getKey()),
                        "",
                        right.getValue()));

        // Find attributes that exist in both comparing nodes and compare them all
        node1Map.entrySet()
                .stream()
                .filter(item -> node2Map.keySet().contains(item.getKey()))
                .forEach(left -> compareValues(
                        String.format("%s->%s", node1.getNodeName(), left.getKey()),
                        left.getValue(),
                        node2Map.get(left.getKey()),
                        compareResult));
    }

    /**
     * Helper method for comparison of string values. If they are not equal add values in the list of differences
     * @param field name of element (node, attribute, text) with difference
     * @param value1 first value of field for comparing
     * @param value2 second value of field for comparing
     * @param compareResult used for storing differences
     */
    private void compareValues(String field, String value1, String value2, CompareResult compareResult) {
        if (!value1.trim().equals(value2.trim())) {
            compareResult.addDifference(field, value1, value2);
        }
    }

    /**
     * Helper method to convert attributes to map
     * @param attrs
     * @return Map containing names and values of attributes
     */
    private Map<String, String> toMap(NamedNodeMap attrs) {
        Map<String, String> map = new HashMap<>();
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
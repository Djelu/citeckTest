package com.fantasy.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxParser {
    private String xmlPath;

    public SaxParser(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getTagBody(String tagName) {
        String body = null;

        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            SaxHandler saxHandler = new SaxHandler(tagName);
            saxParser.parse(xmlPath, saxHandler);
            body = saxHandler.getTagBody();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return body;
    }

    private class SaxHandler extends DefaultHandler {
        private boolean rightTag = false;
        private StringBuilder tagBody = new StringBuilder();
        private String tagName;

        public SaxHandler(String tagName) {
            this.tagName = tagName;
        }

        public String getTagBody() {
            return tagBody.toString();
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if(rightTag){
                tagBody.append("<").append(qName).append(">");
            }
            if(tagName.equals(qName)){
                rightTag = true;
            }
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            if(tagName.equals(qName)){
                rightTag = false;
            }
            if(rightTag){
                tagBody.append("</").append(qName).append(">");
            }
        }

        public void characters(char ch[], int start, int length) throws SAXException {
            if(rightTag){
                tagBody.append(String.copyValueOf(ch, start, length).trim());
            }
        }
    }
}

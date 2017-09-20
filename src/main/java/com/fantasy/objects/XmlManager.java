package com.fantasy.objects;

import com.fantasy.parser.SaxParser;
import org.springframework.stereotype.Service;

/**
 * Created by Djelu on 20.09.2017.
 */
@Service
public class XmlManager {
    private final String pathToXml = "\\xml\\some.xml";

    public XmlManager() {
    }

    public String getValueFromTag(String tagName){
        String resultValue = null;

        String xmlPath = System.getProperty("user.dir") + pathToXml;

        SaxParser saxParser = new SaxParser(xmlPath);
        resultValue = saxParser.getTagBody(tagName);

        return resultValue;
    }
}

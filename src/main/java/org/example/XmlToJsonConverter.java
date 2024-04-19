package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
public class XmlToJsonConverter {
    public static void main(String[] args) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        WebsiteList websiteList = xmlMapper.readValue(new File("src/main/resources/website.xml"), WebsiteList.class);

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonString = jsonMapper.writeValueAsString(websiteList);
        System.out.println(jsonString);
    }
}

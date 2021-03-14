package com.topolski.file_strategy;

import com.topolski.dto.SongsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLReadWrite implements FileReadWrite {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(XMLReadWrite.class);

    @Override
    public SongsDTO readFile(final String filePath) {
        JAXBContext jaxbContext = null;
        SongsDTO songsDTO = null;
        try {
            jaxbContext = JAXBContext.newInstance(SongsDTO.class);
            Unmarshaller jaxbUnmarshaller = null;
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            songsDTO = (SongsDTO) jaxbUnmarshaller
                    .unmarshal(new File(filePath));
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            System.out.println("parsowanie xml");
        }
        return songsDTO;
    }

    @Override
    public void writeFile(final String filePath, final SongsDTO songsDTO) {
        try {
            createFolder("output");
            JAXBContext jaxbContext = JAXBContext.newInstance(SongsDTO.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            File file = new File(filePath);
            jaxbMarshaller.marshal(songsDTO, file);
        } catch (JAXBException e) {
            System.out.println(e);
            LOGGER.error("XML writing error");
        }

    }
}

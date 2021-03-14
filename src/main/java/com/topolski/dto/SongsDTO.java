package com.topolski.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "songs")
@XmlAccessorType(XmlAccessType.FIELD)
public class SongsDTO {
    @XmlElement(name = "song")
    private List<SongDTO> songList = null;
    public List<SongDTO> getSongList() {
        return songList;
    }
    public void setSongList(final List<SongDTO> songList) {
        this.songList = songList;
    }
}

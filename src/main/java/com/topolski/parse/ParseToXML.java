package com.topolski.parse;

import com.topolski.dto.SongDTO;
import com.topolski.dto.SongDTOBuilder;
import com.topolski.dto.SongsDTO;
import com.topolski.entities.Song;
import com.topolski.file_strategy.XMLReadWrite;

import java.util.ArrayList;
import java.util.List;

public class ParseToXML {
    private ParseToXML() { }
    public static void writeToXML(final String filePath,
                                  final List<Song> songList) {
        List<SongDTO> songDTOList = new ArrayList<>();
        for (Song song : songList) {
            songDTOList.add(new SongDTOBuilder()
                    .setTitle(song.getTitle())
                    .setAuthor(song.getAuthor().getAuthorName())
                    .setAlbum(song.getAlbum())
                    .setCategory(song.getCategory())
                    .setVotes(song.getVote().getVotes())
                    .build());
        }
        SongsDTO songsDTO = new SongsDTO();
        songsDTO.setSongList(songDTOList);
        new XMLReadWrite().writeFile(filePath, songsDTO);
    }
}

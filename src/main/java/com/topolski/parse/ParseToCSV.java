package com.topolski.parse;

import com.topolski.dto.SongDTO;
import com.topolski.dto.SongDTOBuilder;
import com.topolski.dto.SongsDTO;
import com.topolski.entities.Song;
import com.topolski.file_strategy.CSVReadWrite;

import java.util.ArrayList;
import java.util.List;

final public class ParseToCSV {
    private ParseToCSV() { }
    static public void writeToCSV(final String filePath,
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
        new CSVReadWrite().writeFile(filePath, songsDTO);
    }
}

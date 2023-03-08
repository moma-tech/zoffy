package top.moma.zoffy.chinook.album.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "\"Album\"")
public class Album {
  @Id
  @Column(name = "\"AlbumId\"")
  private Integer albumId;

  @Column(name = "\"Title\"", nullable = false)
  private String title;

  @Column(name = "\"ArtistId\"", nullable = false)
  private Integer artistId;
}

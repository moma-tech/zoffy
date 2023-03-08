package top.moma.zoffy.chinook.track.entity;

import jakarta.persistence.*;
import lombok.Data;
import top.moma.zoffy.chinook.album.entity.Album;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "\"Track\"")
public class Track {
  @Id
  @Column(name = "\"TrackId\"")
  private Integer trackId;

  @Column(name = "\"Name\"", nullable = false)
  private String name;

  @Column(name = "\"MediaTypeId\"", nullable = false)
  private Integer mediaTypeId;

  @Column(name = "\"GenreId\"")
  private Integer genreId;

  @Column(name = "\"Composer\"")
  private String composer;

  @Column(name = "\"Milliseconds\"", nullable = false)
  private Integer milliseconds;

  @Column(name = "\"Bytes\"")
  private Integer bytes;

  @Column(name = "\"UnitPrice\"", nullable = false)
  private BigDecimal unitPrice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "\"AlbumId\"")
  private Album album;

  public record TrackRecord(String name, String album, String composer) {}
}

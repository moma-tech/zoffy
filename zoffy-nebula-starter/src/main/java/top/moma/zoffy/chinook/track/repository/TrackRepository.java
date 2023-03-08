package top.moma.zoffy.chinook.track.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import top.moma.zoffy.chinook.track.entity.Track;

public interface TrackRepository extends JpaRepository<Track, Integer> {

  //  @Query(
  //      "SELECT new
  // top.moma.zoffy.chinook.track.entity.Track.TrackRecord(t.name,a.title,t.composer) FROM Track t
  // JOIN Album a ON t.album.albumId=a.albumId where t.trackId=:id")
  //  Track.TrackRecord findTrackRecord(@Param("id") Integer trackId);

  @Query(
      "SELECT new TrackRecord(t.name,a.title,t.composer) FROM Track t JOIN Album a ON t.album.albumId=a.albumId where t.trackId=:id")
  Track.TrackRecord findTrackRecordWithUtility(@Param("id") Integer trackId);
}

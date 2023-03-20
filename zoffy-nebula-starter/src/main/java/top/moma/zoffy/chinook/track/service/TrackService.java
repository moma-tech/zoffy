package top.moma.zoffy.chinook.track.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.moma.zoffy.chinook.track.entity.Track;
import top.moma.zoffy.chinook.track.repository.TrackRepository;

@Service
public class TrackService {

  @Autowired TrackRepository trackRepository;

  @PersistenceContext EntityManager entityManager;
  /**
   * getTrackRecord
   *
   * <p>Native way to query Track and Album two tables, then combine the data
   *
   * <p>Generate two selects and select 12 cols, but only use 3.
   *
   * <p>Waste memory,computing and networking
   *
   * @param trackId trackId
   * @return top.moma.zoffy.chinook.track.entity.Track.TrackRecord
   * @author Created by ivan
   * @since 2023/3/2 14:40
   */
  @Transactional(readOnly = true)
  public Track.TrackRecord getTrackRecord(Integer trackId) {
    Track track = trackRepository.findById(trackId).get();
    Track.TrackRecord trackRecord =
        new Track.TrackRecord(track.getName(), track.getAlbum().getTitle(), track.getComposer());
    return trackRecord;
  }

  /**
   * getTrackRecordWithTuple
   *
   * <p>Use Hibernate Query/Tuple
   *
   * <p>Custom Sql executed, then transform to Object
   *
   * <p>Efficient but implementation is complex(longer/wordier)
   *
   * @param trackId trackId
   * @return top.moma.zoffy.chinook.track.entity.Track.TrackRecord
   * @author Created by ivan
   * @since 2023/3/2 14:58
   */
  @Transactional(readOnly = true)
  public Track.TrackRecord getTrackRecordWithTuple(Integer trackId) {
    String sql =
        "SELECT t.name,a.title,t.composer FROM Track t JOIN Album a ON t.album.albumId=a.albumId"
            + " where t.trackId=:id";
    Query<Track.TrackRecord> query =
        entityManager.createQuery(sql).setParameter("id", trackId).unwrap(Query.class);
    Track.TrackRecord trackRecord =
        query
            .setTupleTransformer(
                (tuple, aliases) ->
                    new Track.TrackRecord((String) tuple[0], (String) tuple[1], (String) tuple[2]))
            .getSingleResult();
    return trackRecord;
  }

  /**
   * getTrackRecordWithRepoAnnotation
   *
   * <p>Use JPA Repository Annotation
   *
   * <p>Custom SQL with method in Repository
   *
   * <p>Need combine Entity with full package with SQL, kind confused
   *
   * @param trackId trackId
   * @return top.moma.zoffy.chinook.track.entity.Track.TrackRecord
   * @author Created by ivan
   * @since 2023/3/2 15:21
   */
  @Transactional(readOnly = true)
  public Track.TrackRecord getTrackRecordWithRepoAnnotation(Integer trackId) {
    return trackRepository.findTrackRecordWithUtility(trackId);
  }

  @Transactional(readOnly = true)
  public Track.TrackRecord getTrackRecordWithUtility(Integer trackId) {
    return trackRepository.findTrackRecordWithUtility(trackId);
  }
}

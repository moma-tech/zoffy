package top.moma.zoffy.chinook.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.zoffy.chinook.track.entity.Track;
import top.moma.zoffy.chinook.track.service.TrackService;

@RestController
public class TrackController {
  @Autowired TrackService trackService;

  @RequestMapping("/track/record/one/{id}")
  public Track.TrackRecord findOne(@PathVariable("id") Integer trackId) {
    return trackService.getTrackRecord(trackId);
  }

  @RequestMapping("/track/record/two/{id}")
  public Track.TrackRecord findTwo(@PathVariable("id") Integer trackId) {
    return trackService.getTrackRecordWithTuple(trackId);
  }

  @RequestMapping("/track/record/three/{id}")
  public Track.TrackRecord findThree(@PathVariable("id") Integer trackId) {
    return trackService.getTrackRecordWithRepoAnnotation(trackId);
  }

  @RequestMapping("/track/record/four/{id}")
  public Track.TrackRecord findFour(@PathVariable("id") Integer trackId) {
    return trackService.getTrackRecordWithUtility(trackId);
  }
}

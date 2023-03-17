package top.moma.zoffy.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.moma.zoffy.report.logic.ApArLogicImpl;
import top.moma.zoffy.report.logic.EntryLogicImpl;

import java.math.BigDecimal;

@RestController
@RequestMapping("/entry")
public class EntryController {
  @Autowired EntryLogicImpl entryLogic;
  @Autowired ApArLogicImpl apArLogic;

  @GetMapping("/gen")
  public void genEntry(@RequestParam String period) {
    entryLogic.generatePeriodEntry(period, "");
  }

  @GetMapping("/update")
  public boolean updateEntry(
      @RequestParam String period, @RequestParam String entryId, @RequestParam BigDecimal value) {
    // entryLogic.generatePeriodEntry(period, "");
    return entryLogic.updateManualEntry(period, entryId, value, "Test-User");
  }

  @GetMapping("/recal")
  public void recalEntry(@RequestParam String period) {
    // entryLogic.generatePeriodEntry(period, "");
    entryLogic.recalculateEntries(period, "Test-User");
  }

  @GetMapping("/calMonth")
  public void calMonth(@RequestParam String provider, @RequestParam String settlePeriod) {
    // entryLogic.generatePeriodEntry(period, "");
    apArLogic.calculateMonth(provider, settlePeriod);
  }
}

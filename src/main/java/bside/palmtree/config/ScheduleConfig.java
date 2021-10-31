package bside.palmtree.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import bside.palmtree.service.ranking.RankingService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableScheduling
@Configuration
public class ScheduleConfig {
	private final RankingService rankingService;

	@Scheduled(cron = "* * 0 * * *")
	public void dailySchedule() {
		this.rankingService.createRankings(LocalDate.now());
	}
}

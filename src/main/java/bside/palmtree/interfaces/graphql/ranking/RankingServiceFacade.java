package bside.palmtree.interfaces.graphql.ranking;

import org.springframework.stereotype.Service;

import bside.palmtree.service.ranking.RankingService;
import lombok.RequiredArgsConstructor;

/**
 * Created by YHH on 2021/04/13
 */
@RequiredArgsConstructor
@Service
public class RankingServiceFacade {
	private final RankingService rankingService;


}

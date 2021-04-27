package bside.palmtree.external;

/**
 * Created by YHH on 2021/04/09
 */
public interface OAuthClient {
	TokenInfo getTokenInfo(String accessToken);
}

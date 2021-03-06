package bus.dao;

import java.util.List;

import bus.vo.Account;
import bus.vo.Bus;
import bus.vo.Favorite;
import bus.vo.History;
import bus.vo.Station;

public interface BusMapper {
	
	int insertAccount(Account account);
	
	Account selectAccount(String id);
	
	int insertBus(Bus bus);
	
	Bus selectBus(int routId);
	
	List<Bus> selectBusContainsNum(String busNum);
	
	int insertStation(Station station);
	
	Station selectStation(int stnId);
	
	int insertFavorite(Favorite favorite);
	
	List<Bus> selectFavoriteBus(String userId);
	
	List<Station> selectFavoriteStn(String userId);
	
	List<Favorite> selectFavorite(String userId);
	
	int deleteFavorite(Favorite favorite);
	
	int insertHistory(History history);
	
	List<History> selectHistory(String userId);

	int updateHistory(History history);
}

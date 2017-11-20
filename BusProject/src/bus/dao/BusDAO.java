package bus.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bus.vo.Account;
import bus.vo.Bus;
import bus.vo.Favorite;
import bus.vo.Station;

public class BusDAO {
	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	/**
	 * 계정 정보를 데이터베이스에 저장한다.
	 * @param account 저장할 계정 정보
	 * @return 저장 결과
	 */
	public boolean insertAccount(Account account) {
		SqlSession session = null;
		boolean result = true;
		
		try {
			session = factory.openSession();
			BusMapper mapper = session.getMapper(BusMapper.class);
			
			if (mapper.insertAccount(account) != 1) {
				result = false;
			}
			
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return result;
	}
	
	/**
	 * ID를 가지고 해당 계정 정보를 가져온다.
	 * @param userId 찾고자 하는 계정의 ID
	 * @return ID와 일치하는 계정
	 */
	public Account selectAccount(String userId) {
		SqlSession session = null;
		Account result = null;
		
		try {
			session = factory.openSession();
			BusMapper mapper = session.getMapper(BusMapper.class);
			
			result = mapper.selectAccount(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return result;
	}
	
	/**
	 * 버스 목록을 넘겨 받아 데이터베이스에 저장한다.
	 * @param busesList 저장할 버스 리스트
	 * @return 저장 결과
	 */
	public boolean insertBuses(List<Bus> busesList) {
		SqlSession session = null;
		boolean result = true;
		int numOfInserted = 0;
		int numOfInsertTry = 0;
		
		try {
			session = factory.openSession();
			BusMapper mapper = session.getMapper(BusMapper.class);
			
			for (Bus bus : busesList) {
				numOfInsertTry++;
				numOfInserted = numOfInserted + mapper.insertBus(bus);
			}
			
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		if (numOfInsertTry != busesList.size()) {
			result = false;
		}
		
		if (numOfInserted != numOfInsertTry) {
			System.out.println("업데이트가 이미 되었습니다.");
		}
		
		return result;
	}
	
	/**
	 * 특정 번호를 포함하는 모든 버스를 가져온다.
	 * @param busNum 검색하려는 번호
	 * @return 특정 번호를 포함하는 버스
	 */
	public List<Bus> srchBusContainsNum(String busNum) {
		SqlSession session = null;
		List<Bus> busList = null;
		
		try {
			session = factory.openSession();
			BusMapper mapper = session.getMapper(BusMapper.class);
			
			busList = mapper.selectBusContainsNum(busNum);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return busList;
	}
	
	/**
	 * 저장할 정류장 목록을 넘겨 받아 데이터베이스에 저장한다.
	 * @param stationsList 저장할 정류장 리스트
	 * @return 저장 결과
	 */
	public boolean insertStations(List<Station> stationsList) {
		SqlSession session = null;
		boolean result = true;
		
		try {
			session = factory.openSession();
			BusMapper mapper = session.getMapper(BusMapper.class);
			
			for (Station station : stationsList) {
				if(mapper.insertStation(station) == 0) {
					result = false;
				}
			}
			
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return result;
	}
	
	
	/**
	 * 즐겨찾기 정보를 데이터베이스에 저장한다.
	 * @param favorite 저장할 즐겨찾기 정보
	 * @return boolean 즐겨찾기 저장 결과
	 */
	public boolean insertFavorite(Favorite favorite) {
		SqlSession session = null;
		boolean result = true;
		
		try {
			session = factory.openSession();
			BusMapper mapper = session.getMapper(BusMapper.class);
			
			if (mapper.insertFavorite(favorite) != 1) {
				result = false;
			}
			
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return result;
	}
	
	
	/**
	 * 사용자의 즐겨찾기 정보를 가져온다.
	 * @param userId 사용자 ID
	 * @return 즐겨찾기 정보
	 */
	public List<Favorite> selectFavoriteInfo(String userId) {
		SqlSession session = null;
		List<Favorite> favList = null;
		
		try {
			session = factory.openSession();
			BusMapper mapper = session.getMapper(BusMapper.class);
			
			favList = mapper.selectFavorite(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return favList;
	}
	
	
	/**
	 * 즐겨찾기 정보 중 버스만 받아온다.
	 * @param userId 사용자 ID
	 * @return busList 즐겨찾기에 등록된 버스의 리스트
	 */
	public List<Bus> selectFavoriteBus(String userId) {
		SqlSession session = null;
		List<Bus> busList = null;
		
		try {
			session = factory.openSession();
			BusMapper mapper = session.getMapper(BusMapper.class);
			
			busList = mapper.selectFavoriteBus(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return busList;
	}
	
	/**
	 * 즐겨찾기 정보 중 정류장만 받아온다.
	 * @param userId 사용자 ID
	 * @return busList 즐겨찾기에 등록된 정류장의 리스트
	 */
	public List<Station> selectFavoriteStn(String userId) {
		SqlSession session = null;
		List<Station> stnList = null;
		
		try {
			session = factory.openSession();
			BusMapper mapper = session.getMapper(BusMapper.class);
			
			stnList = mapper.selectFavoriteStn(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return stnList;
	}
	
	/**
	 * 계정에 저장되어 있는 즐겨찾기 정보를 모두를 가져와 대응되는 버스, 정류장 정보를 받아온다. 
	 * @param userId 사용자 ID
	 * @return favorMap 버스리스트, 정류장리스트가 저장되어 있는 Map
	 */
	public Map<String, Object> selectFavoriteAll(String userId) {
		SqlSession session = null;
		Map<String, Object> favorMap = new HashMap<>();
		
		try {
			session = factory.openSession();
			BusMapper mapper = session.getMapper(BusMapper.class);
			
			List<Favorite> favorList = mapper.selectFavorite(userId);
			
			List<Bus> busList = new ArrayList<>();
			List<Station> stnList = new ArrayList<>();
			
			for (Favorite favorite : favorList) {
				if (favorite.getBusOrStnType().equals("B")) {
					Bus bus = mapper.selectBus(favorite.getBusOrStnId());
					busList.add(bus);
					
				} else {
					Station station = mapper.selectStation(favorite.getBusOrStnId());
					stnList.add(station);
					
				}
			}
			
			favorMap.put("Bus", busList);
			favorMap.put("Station", stnList);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return favorMap;
	}
	
}

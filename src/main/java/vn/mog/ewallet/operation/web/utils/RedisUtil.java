package vn.mog.ewallet.operation.web.utils;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * Specify the cache expiration time
	 *
	 * @param key  key
	 * @param time time (seconds)
	 * @return
	 */
	public boolean expire(String key, long time) {
		try {
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Get expiration time according to key
	 *
	 * @param key key cannot be null
	 * @return time (seconds) return 0 for permanent validity
	 */
	public long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	/**
	 * Determine whether the key exists
	 *
	 * @param key key
	 * @return true exists false does not exist
	 */
	public boolean hasKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Delete cache
	 *
	 * @param key can pass one value or multiple
	 */
	public void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(Arrays.asList(key));
			}
		}
	}

	/**
	 * Ordinary cache acquisition
	 *
	 * @param key key
	 * @return value
	 */
	public Object get(String key) {
		return key == null ? null : redisTemplate.opsForValue().get(key);
	}

	public List<Object> mget(List<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	/**
	 * Put in ordinary cache
	 *
	 * @param key   key
	 * @param value
	 * @return true success false failure
	 */
	public boolean set(String key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Put the normal cache and set the time
	 *
	 * @param key   key
	 * @param value
	 * @param time  time (seconds) time must be greater than 0, if time is less than
	 *              or equal to 0, it will be set indefinitely
	 * @return true success false failure
	 */
	public boolean set(String key, Object value, long time) {
		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			} else {
				set(key, value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Increasing
	 *
	 * @param key   key
	 * @param delta how much to increase (greater than 0)
	 * @return
	 */
	public long incr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("The increment factor must be greater than 0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * Decrease
	 *
	 * @param key   key
	 * @param delta to be reduced by a few (less than 0)
	 * @return
	 */
	public long decr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("The decrement factor must be greater than 0");
		}
		return redisTemplate.opsForValue().increment(key, -delta);
	}

	// ================================Map=================================
	/**
	 * HashGet
	 *
	 * @param key  key cannot be null
	 * @param item item cannot be null
	 * @return value
	 */
	public Object hget(String key, String item) {
		return redisTemplate.opsForHash().get(key, item);
	}

	/**
	 * Get all the key values ​​corresponding to hashKey
	 *
	 * @param key key
	 * @return corresponding multiple key values
	 */
	public Map<Object, Object> hmget(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * HashSet
	 *
	 * @param key key
	 * @param map corresponds to multiple key values
	 * @return true success false failure
	 */
	public boolean hmset(String key, Map<String, Object> map) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * HashSet and set the time
	 *
	 * @param key  key
	 * @param map  corresponds to multiple key values
	 * @param time time (seconds)
	 * @return true success false failure
	 */
	public boolean hmset(String key, Map<String, Object> map, long time) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Put data into a hash table, if it does not exist, it will be created
	 *
	 * @param key   key
	 * @param item  item
	 * @param value
	 * @return true success false failure
	 */
	public boolean hset(String key, String item, Object value) {
		try {
			redisTemplate.opsForHash().put(key, item, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Put data into a hash table, if it does not exist, it will be created
	 *
	 * @param key   key
	 * @param item  item
	 * @param value
	 * @param time  Time (seconds) Note: If the existing hash table has time, the
	 *              original time will be replaced here
	 * @return true success false failure
	 */
	public boolean hset(String key, String item, Object value, long time) {
		try {
			redisTemplate.opsForHash().put(key, item, value);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Delete the value in the hash table
	 *
	 * @param key  key cannot be null
	 * @param item items can be multiple and cannot be null
	 */
	public void hdel(String key, Object... item) {
		redisTemplate.opsForHash().delete(key, item);
	}

	/**
	 * Determine whether there is a value in the hash table
	 *
	 * @param key  key cannot be null
	 * @param item item cannot be null
	 * @return true exists false does not exist
	 */
	public boolean hHasKey(String key, String item) {
		return redisTemplate.opsForHash().hasKey(key, item);
	}

	/**
	 * hash increment If it does not exist, it will create one and return the newly
	 * added value
	 *
	 * @param key  key
	 * @param item item
	 * @param by   how many to increase (greater than 0)
	 * @return
	 */
	public double hincr(String key, String item, double by) {
		return redisTemplate.opsForHash().increment(key, item, by);
	}

	/**
	 * hash decreasing
	 *
	 * @param key  key
	 * @param item item
	 * @param by   should be reduced (less than 0)
	 * @return
	 */
	public double hdecr(String key, String item, double by) {
		return redisTemplate.opsForHash().increment(key, item, -by);
	}

	// ============================set=============================
	/**
	 * Get all values ​​in Set according to key
	 *
	 * @param key key
	 * @return
	 */
	public Set<Object> sGet(String key) {
		try {
			return redisTemplate.opsForSet().members(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Query from a set according to value, whether it exists
	 *
	 * @param key   key
	 * @param value
	 * @return true exists false does not exist
	 */
	public boolean sHasKey(String key, Object value) {
		try {
			return redisTemplate.opsForSet().isMember(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Put data into set cache
	 *
	 * @param key    key
	 * @param values ​​can be multiple
	 * @return number of successes
	 */
	public long sSet(String key, Object... values) {
		try {
			return redisTemplate.opsForSet().add(key, values);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Put data into set cache
	 *
	 * @param key   key
	 * @param value can be multiple
	 * @param score order
	 * @param time  expiration time
	 */
	public boolean zSet(String key, Object value, double score, long time) {
		try {
			redisTemplate.opsForZSet().add(key, value, score);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Put data into set cache
	 *
	 * @param key   key
	 * @param value can be multiple
	 * @param score order
	 */
	public boolean zSetAdd(String key, Object value, double score) {
		try {
			redisTemplate.opsForZSet().add(key, value, score);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Get the collection size
	 *
	 * @param key key
	 * @return collection size
	 */
	public long zSetGetSize(String key) {
		try {
			return redisTemplate.opsForZSet().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Set<ZSetOperations.TypedTuple<Object>> zSetRangeWithScores(String key, long start, long end) {
		return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
	}

	public Set<ZSetOperations.TypedTuple<Object>> zSetReverseRangeWithScores(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
	}

	public long zSetRemove(String key, Object... values) {
		try {
			return redisTemplate.opsForZSet().remove(key, values);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean zSetIsMember(String key, Object value) {
		try {
			return redisTemplate.opsForZSet().score(key, value) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Put the set data into the cache
	 *
	 * @param key    key
	 * @param time   time (seconds)
	 * @param values ​​can be multiple
	 * @return number of successes
	 */
	public long sSetAndTime(String key, long time, Object... values) {
		try {
			Long count = redisTemplate.opsForSet().add(key, values);
			if (time > 0) {
				expire(key, time);
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Get the length of the set cache
	 *
	 * @param key key
	 * @return
	 */
	public long sGetSetSize(String key) {
		try {
			return redisTemplate.opsForSet().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Remove the value
	 *
	 * @param key    key
	 * @param values ​​can be multiple
	 * @return removed number
	 */
	public long setRemove(String key, Object... values) {
		try {
			Long count = redisTemplate.opsForSet().remove(key, values);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	// ===============================list=================================

	/**
	 * Get the contents of the list cache
	 *
	 * @param key   key
	 * @param start start
	 * @param end   end 0 to -1 represent all values
	 * @return
	 */
	public List<Object> lGet(String key, long start, long end) {
		try {
			return redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get the length of the list cache
	 *
	 * @param key key
	 * @return
	 */
	public long lGetListSize(String key) {
		try {
			return redisTemplate.opsForList().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Get the value in the list by index
	 *
	 * @param key   key
	 * @param index When index>=0, 0 is the head of the table, 1 is the second
	 *              element, and so on; when index<0, -1, is the end of the table,
	 *              -2 is the penultimate element, and so on
	 * @return
	 */
	public Object lGetIndex(String key, long index) {
		try {
			return redisTemplate.opsForList().index(key, index);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Put the list into the cache
	 *
	 * @param key   key
	 * @param value
	 * @return
	 */
	public boolean lSet(String key, Object value) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Put the list in the cache
	 *
	 * @param key   key
	 * @param value
	 * @param time  time (seconds)
	 * @return
	 */
	public boolean lSet(String key, Object value, long time) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Put the list in the cache
	 *
	 * @param key   key
	 * @param value
	 * @return
	 */
	public boolean lSet(String key, List<Object> value) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Put the list into the cache
	 *
	 * @param key   key
	 * @param value
	 * @param time  time (seconds)
	 * @return
	 */
	public boolean lSet(String key, List<Object> value, long time) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Modify a piece of data in the list according to the index
	 *
	 * @param key   key
	 * @param index index
	 * @param value
	 * @return
	 */
	public boolean lUpdateIndex(String key, long index, Object value) {
		try {
			redisTemplate.opsForList().set(key, index, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Remove N values ​​as value
	 *
	 * @param key   key
	 * @param count how many to remove
	 * @param value
	 * @return removed number
	 */
	public long lRemove(String key, long count, Object value) {
		try {
			Long remove = redisTemplate.opsForList().remove(key, count, value);
			return remove;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Perform pipeline operations
	 *
	 * @param sessionCallback session callback
	 */
	@SuppressWarnings("rawtypes")
	public List<Object> pipeLine(SessionCallback sessionCallback) {
		return redisTemplate.executePipelined(sessionCallback);
	}

	/**
	 * Set if it does not exist
	 *
	 * @param key   key
	 * @param value
	 * @return is set successfully
	 */
	public boolean setNotExist(String key, String value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}
}
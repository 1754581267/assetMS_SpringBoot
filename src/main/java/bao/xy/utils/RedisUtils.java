package bao.xy.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Redis工具类
 * @CreateTime: 2020-10-17-21-46
 */
@Service
public class RedisUtils {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 保存数据
     *
     * @param key  Key
     * @param data 数据
     */
    public void save(String key, Object data) {
        this.save(key, data, -1L);
    }

    /**
     * 保存数据
     *
     * @param key     Key
     * @param data    数据
     * @param timeout 过期时间
     */
    public void save(String key, Object data, Long timeout) {
        // 参数校验
        if (StringUtils.isEmpty(key) || data == null) {
            return;
        }
        // 序列化数据
        String serializeData = SerializeUtils.serializeToString(data);
        if (StringUtils.isEmpty(serializeData)) {
            return;
        }
        // 判断是否有过期时间
        if (timeout == null || timeout <= 0) {
            stringRedisTemplate.opsForValue().set(key, serializeData);
        } else {
            stringRedisTemplate.opsForValue().set(key, serializeData, timeout, TimeUnit.SECONDS);
        }
    }

    /**
     * 获取数据
     *
     * @param key Key
     * @return 数据
     */
    public Object get(String key) {
        // 参数校验
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        // 从缓存获取数据
        String data = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        return SerializeUtils.deserialize(data);
    }

    /**
     * 删除key对应的数据
     *
     * @param key Key
     */
    public void del(String key) {
        // 参数校验
        if (StringUtils.isEmpty(key)) {
            return;
        }
        stringRedisTemplate.delete(key);
    }

    /**
     * 更改key
     *
     * @param oldKey 旧Key
     * @param newKey 新Key
     */
    public void rename(String oldKey, String newKey) {
        // 参数校验
        if (StringUtils.isEmpty(oldKey) || StringUtils.isEmpty(newKey)) {
            return;
        }
        stringRedisTemplate.rename(oldKey, newKey);
    }

    /**
     * 设置key的过期时间(秒)
     *
     * @param key     Key
     * @param timeout 时间(秒)
     */
    public void expire(String key, long timeout) {
        // 参数校验
        if (StringUtils.isEmpty(key) || timeout <= 0) {
            return;
        }
        stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 取消对key过期时间的设置
     *
     * @param key Key
     * @return true or false
     */
    public boolean persist(String key) {
        // 参数校验
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        return stringRedisTemplate.persist(key);
    }

    /**
     * 查找所有匹配给定的模式的键
     *
     * @param pattern key的表达式,*表示多个,?表示一个
     * @return 全部符合表达式记录
     */
    public Set<String> getByPattern(String pattern) {
        // 参数校验
        if (StringUtils.isEmpty(pattern)) {
            return null;
        }
        Set<String> set = stringRedisTemplate.keys(pattern);
        return set;
    }

}

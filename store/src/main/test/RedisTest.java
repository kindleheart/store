import redis.clients.jedis.Jedis;

/**
 * Created by kindleheart happily.
 */
public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println("连接成功");
        System.out.println("服务器正在运行" + jedis.ping());
        jedis.set("name", "kindleheart");
    }
}

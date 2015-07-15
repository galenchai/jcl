package net.galenchai.zookeeper.client.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

/**
 * Assume the server is localhost and port is 2182
 */
public class TestCuratorBasis {

	public final static String CONNECTION_STRING = "localhost:2181";
	
	private CuratorFramework cf = null;
	
	@Before
	public void createClient() {
		RetryPolicy cp = new ExponentialBackoffRetry(1000, 5);
		cf = CuratorFrameworkFactory.newClient(CONNECTION_STRING, cp);
		cf.start();
	}
	
	//@Test
	public void testCreateNode() {
		try {
			cf.create().forPath("/test5");
			String t = cf.create().forPath("/test3", "mydata3".getBytes());
			Stat s = cf.setData().forPath("/test5", "mydata5".getBytes());
			System.out.println(s.getDataLength() + " : " + t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Manipulate /test1 in the background to check whether listener is active
	 * @throws Exception
	 */
	@Test
	public void testListener() throws Exception {
		final NodeCache cache = new NodeCache(cf, "/test1", false);
		cache.start(true);
		cache.getListenable().addListener(new NodeCacheListener() {
			public void nodeChanged() throws Exception {
				System.out.println("Node data update, new data : " + new String(cache.getCurrentData().getData()));
			}
			
		});
//		cf.setData().forPath("/test1", "new data".getBytes());
//		Thread.sleep(10000);
//		cf.delete().deletingChildrenIfNeeded().forPath("/test1");
		Thread.sleep(Integer.MAX_VALUE);
		cache.close();
	}
	
	
}

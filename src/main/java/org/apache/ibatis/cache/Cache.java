/*
 *    Copyright 2009-2023 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * <a href="https://mybatis.org/mybatis-3/sqlmap-xml.html#cache">Mybatis的缓存</a>
 *
 * 缓存提供者的SPI。
 * 每个命名空间都会创建一个缓存实例。缓存实现类必须有一个构造函数，参数：String类型的cache id。Mybatis会将命名空间当做id传给构造函数。
 *
 * 支持的缓存策略包括lru、fifo、soft、weak。如果开启，默认lru。
 * 支持自定义Cache，详情查看官网文档
 *
 * <pre>
 * public MyCache(final String id) {
 *   if (id == null) {
 *     throw new IllegalArgumentException("Cache instances require an ID");
 *   }
 *   this.id = id;
 *   initialize();
 * }
 * </pre>
 *
 * @author Clinton Begin
 */

public interface Cache {

  /**
   * @return The identifier of this cache
   */
  String getId();

  /**
   * @param key
   *          Can be any object but usually it is a {@link CacheKey}
   * @param value
   *          The result of a select.
   */
  void putObject(Object key, Object value);

  /**
   * @param key
   *          The key
   *
   * @return The object stored in the cache.
   */
  Object getObject(Object key);

  /**
   * As of 3.3.0 this method is only called during a rollback for any previous value that was missing in the cache. This
   * lets any blocking cache to release the lock that may have previously put on the key. A blocking cache puts a lock
   * when a value is null and releases it when the value is back again. This way other threads will wait for the value
   * to be available instead of hitting the database.
   *
   * @param key
   *          The key
   *
   * @return Not used
   */
  Object removeObject(Object key);

  /**
   * Clears this cache instance.
   */
  void clear();

  /**
   * Optional. This method is not called by the core.
   *
   * @return The number of elements stored in the cache (not its capacity).
   */
  int getSize();

  /**
   * Optional. As of 3.2.6 this method is no longer called by the core.
   * <p>
   * Any locking needed by the cache must be provided internally by the cache provider.
   *
   * @return A ReadWriteLock
   */
  default ReadWriteLock getReadWriteLock() {
    return null;
  }

}

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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.apache.ibatis.reflection.ArrayUtil;

/**
 * 缓存key类。
 * 负责cacheKey的生成、比较等工作。
 *
 * @author Clinton Begin
 */
public class CacheKey implements Cloneable, Serializable {

  private static final long serialVersionUID = 1146682552656046210L;

  public static final CacheKey NULL_CACHE_KEY = new CacheKey() {

    private static final long serialVersionUID = 1L;

    @Override
    public void update(Object object) {
      throw new CacheException("Not allowed to update a null cache key instance.");
    }

    @Override
    public void updateAll(Object[] objects) {
      throw new CacheException("Not allowed to update a null cache key instance.");
    }
  };

  // 自定义哈希码时常用的参数（很多框架都有使用），有助于减少哈希冲突，在分散性和计算性能之间取得良好平衡。
  private static final int DEFAULT_MULTIPLIER = 37;
  private static final int DEFAULT_HASHCODE = 17;

  private final int multiplier;

  // 通过hashCode、checksum、count、updateList四个字段一起判断是否相等
  private int hashcode;
  private long checksum;
  private int count;
  // 8/21/2017 - Sonarlint flags this as needing to be marked transient. While true if content is not serializable, this
  // is not always true and thus should not be marked transient.
  // 保存用来作为key的对象，
  // 判断是否两个cacheKey是否相同时，会根据updateList的元素一一比对判断。
  private List<Object> updateList;

  public CacheKey() {
    this.hashcode = DEFAULT_HASHCODE;
    this.multiplier = DEFAULT_MULTIPLIER;
    this.count = 0;
    this.updateList = new ArrayList<>();
  }

  public CacheKey(Object[] objects) {
    this();
    updateAll(objects);
  }

  public int getUpdateCount() {
    return updateList.size();
  }

  // 根据对象实例，生成cacheKey
  public void update(Object object) {
    // ArrayUtil.hashCode支持普通对象、数组对象
    int baseHashCode = object == null ? 1 : ArrayUtil.hashCode(object);

    // cacheKey的hashCode的逻辑：将对象的hashCode以某种规则加入到hashCode中。
    count++;
    checksum += baseHashCode;
    baseHashCode *= count;

    hashcode = multiplier * hashcode + baseHashCode;

    updateList.add(object);
  }

  public void updateAll(Object[] objects) {
    for (Object o : objects) {
      update(o);
    }
  }

  // 判断两个CacheKey是否相等
  @Override
  public boolean equals(Object object) {
    // 判断obj的类是否正确
    if (this == object) {
      return true;
    }
    if (!(object instanceof CacheKey)) {
      return false;
    }

    final CacheKey cacheKey = (CacheKey) object;

    // 判断hashCode、checksum、count是否相等
    if ((hashcode != cacheKey.hashcode) || (checksum != cacheKey.checksum) || (count != cacheKey.count)) {
      return false;
    }

    // 将updateList的元素取出来后，一一对比
    for (int i = 0; i < updateList.size(); i++) {
      Object thisObject = updateList.get(i);
      Object thatObject = cacheKey.updateList.get(i);
      if (!ArrayUtil.equals(thisObject, thatObject)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return hashcode;
  }

  @Override
  public String toString() {
    StringJoiner returnValue = new StringJoiner(":");
    returnValue.add(String.valueOf(hashcode));
    returnValue.add(String.valueOf(checksum));
    updateList.stream().map(ArrayUtil::toString).forEach(returnValue::add);
    return returnValue.toString();
  }

  // 深拷贝（除了updateList之外，都是基本数据类型，updateList通过手动复制）
  @Override
  public CacheKey clone() throws CloneNotSupportedException {
    CacheKey clonedCacheKey = (CacheKey) super.clone();
    clonedCacheKey.updateList = new ArrayList<>(updateList);
    return clonedCacheKey;
  }

}

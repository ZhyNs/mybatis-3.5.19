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
package org.apache.ibatis.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;

/**
 * BoundSql持有最后的sql语句字符串（在处理所有动态内容后得到的）。
 * BoundSql包含有占位符?的sql语句、固定顺序的参数列表，也包括一些动态语言的参数（比如loop和bind）。
 * <p>
 * BoundSql是SqlSource类创建的，SqlSource是在解析mapper.xml的sql语句时LanguageDriver创建。
 *
 * @author Clinton Begin
 */
public class BoundSql {

  private final String sql;
  private final List<ParameterMapping> parameterMappings;
  private final Object parameterObject;
  private final Map<String, Object> additionalParameters;
  private final MetaObject metaParameters;

  public BoundSql(Configuration configuration, String sql, List<ParameterMapping> parameterMappings,
      Object parameterObject) {
    this.sql = sql;
    this.parameterMappings = parameterMappings;
    this.parameterObject = parameterObject;
    this.additionalParameters = new HashMap<>();
    this.metaParameters = configuration.newMetaObject(additionalParameters);
  }

  public String getSql() {
    return sql;
  }

  public List<ParameterMapping> getParameterMappings() {
    return parameterMappings;
  }

  public Object getParameterObject() {
    return parameterObject;
  }

  public boolean hasAdditionalParameter(String name) {
    String paramName = new PropertyTokenizer(name).getName();
    return additionalParameters.containsKey(paramName);
  }

  public void setAdditionalParameter(String name, Object value) {
    metaParameters.setValue(name, value);
  }

  public Object getAdditionalParameter(String name) {
    return metaParameters.getValue(name);
  }

  public Map<String, Object> getAdditionalParameters() {
    return additionalParameters;
  }
}

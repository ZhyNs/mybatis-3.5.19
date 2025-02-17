/*
 *    Copyright 2009-2022 the original author or authors.
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
package org.apache.ibatis.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 参数处理器，负责设置PreparedStatement语句的参数
 *
 * @author Clinton Begin
 */
public interface ParameterHandler {

  // 获取参数对象
  // 在MapperMethod执行时创建ParameterObject，数据结构：Map<String, Object>
  // 具体方法：{@link org.apache.ibatis.reflection.ParamNameResolver.getNamedParams()}
  Object getParameterObject();

  // 给预处理sql语句设置参数
  void setParameters(PreparedStatement ps) throws SQLException;

}

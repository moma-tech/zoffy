/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package top.moma.zoffy.framework.trace.logback;

import ch.qos.logback.classic.pattern.MDCConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.util.Map;
import org.springframework.util.StringUtils;
import top.moma.zoffy.framework.trace.TraceWorker;

/**
 * LogbackMDCPatternConverter
 *
 * <p>MDC TraceID to tid
 *
 * @version 1.0
 * @author Created by ivan at 18:54.
 */
public class LogbackMDCPatternConverter extends MDCConverter {
  private static final String CONVERT_TRACE_ID_KEY = TraceWorker.TRACE_ID;

  private boolean convert4TID = false;

  @Override
  public void start() {
    super.start();
    convert4TID = true;
  }

  @Override
  public String convert(ILoggingEvent iLoggingEvent) {
    if (convert4TID) {
      return convertTID(iLoggingEvent);
    }
    return super.convert(iLoggingEvent);
  }

  public String convertTID(ILoggingEvent iLoggingEvent) {
    Map<String, String> mdcPropertyMap = iLoggingEvent.getMDCPropertyMap();
    String value = mdcPropertyMap.get(CONVERT_TRACE_ID_KEY);
    if (StringUtils.hasText(value)) {
      return value;
    }
    return "tid: N/A";
  }
}

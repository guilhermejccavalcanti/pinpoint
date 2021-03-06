/*
 * Copyright 2014 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.profiler.modifier.db.interceptor;

import com.navercorp.pinpoint.bootstrap.context.DatabaseInfo;
import com.navercorp.pinpoint.bootstrap.context.RecordableTrace;
import com.navercorp.pinpoint.bootstrap.context.CallStackFrame;
import com.navercorp.pinpoint.bootstrap.interceptor.*;
import com.navercorp.pinpoint.bootstrap.interceptor.tracevalue.DatabaseInfoTraceValueUtils;

/**
 * @author netspider
 * @author emeroad
 */
public class StatementExecuteQueryInterceptor extends SpanEventSimpleAroundInterceptor {



    public StatementExecuteQueryInterceptor() {
        super(StatementExecuteQueryInterceptor.class);
    }

    @Override
    public void doInBeforeTrace(CallStackFrame recorder, final Object target, Object[] args) {
        recorder.markBeforeTime();
        /**
         * If method was not called by request handler, we skip tagging.
         */
        DatabaseInfo databaseInfo = DatabaseInfoTraceValueUtils.__getTraceDatabaseInfo(target, UnKnownDatabaseInfo.INSTANCE);

        recorder.recordServiceType(databaseInfo.getExecuteQueryType());
        recorder.recordEndPoint(databaseInfo.getMultipleHost());
        recorder.recordDestinationId(databaseInfo.getDatabaseId());

    }


    @Override
    public void doInAfterTrace(CallStackFrame recorder, Object target, Object[] args, Object result, Throwable throwable) {

        recorder.recordApi(getMethodDescriptor());
        if (args.length > 0) {
            Object arg = args[0];
            if (arg instanceof String) {
                recorder.recordSqlInfo((String) arg);
                // TODO more parsing result processing
            }
        }
        recorder.recordException(throwable);
        recorder.markAfterTime();

    }

}

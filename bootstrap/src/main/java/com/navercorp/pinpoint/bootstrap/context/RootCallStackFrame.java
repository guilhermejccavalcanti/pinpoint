package com.navercorp.pinpoint.bootstrap.context;

import com.navercorp.pinpoint.bootstrap.interceptor.MethodDescriptor;
import com.navercorp.pinpoint.common.trace.AnnotationKey;
import com.navercorp.pinpoint.common.trace.ServiceType;

public interface RootCallStackFrame {

    boolean canSampled();

    boolean isRoot();

    void recordStartTime(long startTime);
    
    void markBeforeTime();

    void markAfterTime();

    void recordException(Throwable throwable);

    void recordApi(MethodDescriptor methodDescriptor);

    void recordApi(MethodDescriptor methodDescriptor, Object[] args);

    void recordApi(MethodDescriptor methodDescriptor, Object args, int index);

    void recordApi(MethodDescriptor methodDescriptor, Object[] args, int start, int end);

    void recordApiCachedString(MethodDescriptor methodDescriptor, String args, int index);

    void recordAttribute(AnnotationKey key, String value);

    void recordAttribute(AnnotationKey key, int value);

    void recordAttribute(AnnotationKey key, Object value);

    void recordServiceType(ServiceType serviceType);

    void recordRpcName(String rpc);

    void recordRemoteAddress(String remoteAddress);
    
    void recordEndPoint(String endPoint);

    void recordParentApplication(String parentApplicationName, short parentApplicationType);

    void recordAcceptorHost(String host);
}
package com.kitchen.sdk.plugin;

import com.kitchen.sdk.metrics.MetricsClient;
import com.kitchen.sdk.metrics.annotation.Metrics;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class BytebuddyPlugin implements Plugin {
    @Override
    public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        return (DynamicType.Builder<?>) builder.method(ElementMatchers.isAnnotatedWith(Metrics.class).and(support())).intercept(MethodDelegation.to(MetricsIntercept.class));
    }

    private static <T extends MethodDescription> Junction support() {
        return new SupportMatcher<>();
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public boolean matches(TypeDescription target) {
        return true;
    }

    public static class MetricsIntercept {
        @RuntimeType
        public static Object intercept(@Origin Class obj, @AllArguments Object[] allArguments, @SuperCall Callable<?> zuper, @Origin Method method) throws Exception {
            MetricsClient client = null;
//            try {
//                client = MetricsUtil.createClient(obj, allArguments, method);
//            } catch (Throwable e) {
//            }
            Object ret = null;
//            try {
//                ret = zuper.call();
//                MetricsUtil.doSuccess(client, method);
//            } catch (Exception e) {
//                if (MetricsUtil.isRealSuccess(method, e))
//                    MetricsUtil.doSuccess(client, method);
//                throw e;
//            } finally {
//                MetricsUtil.doMetrics(client, method);
//            }
            return ret;
        }
    }
}

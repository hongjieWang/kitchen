package com.kitchen.sdk.plugin;


import com.kitchen.sdk.metrics.util.StringUtil;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public class SupportMatcher<T extends MethodDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private static final MethodDescription.InDefinedShape REQUEST_PARAM_NAME;

    private static final MethodDescription.InDefinedShape REQUEST_PARAM_VALUE;

    private static final MethodDescription.InDefinedShape PATH_VARIABLE_NAME;

    private static final MethodDescription.InDefinedShape PATH_VARIABLE_VALUE;

    static {
        MethodList<MethodDescription.InDefinedShape> requestParam = TypeDescription.ForLoadedType.of(RequestParam.class).getDeclaredMethods();
        REQUEST_PARAM_NAME = (MethodDescription.InDefinedShape) ((MethodList<?>) requestParam.filter(ElementMatchers.named("name"))).getOnly();
        REQUEST_PARAM_VALUE = (MethodDescription.InDefinedShape) ((MethodList<?>) requestParam.filter(ElementMatchers.named("value"))).getOnly();
        MethodList<MethodDescription.InDefinedShape> pathVariable = TypeDescription.ForLoadedType.of(PathVariable.class).getDeclaredMethods();
        PATH_VARIABLE_NAME = (MethodDescription.InDefinedShape) ((MethodList<?>) pathVariable.filter(ElementMatchers.named("name"))).getOnly();
        PATH_VARIABLE_VALUE = (MethodDescription.InDefinedShape) ((MethodList<?>) pathVariable.filter(ElementMatchers.named("value"))).getOnly();
    }

    @Override
    public boolean matches(T target) {
        if (target.getDeclaringType() instanceof net.bytebuddy.description.annotation.AnnotationSource) {
            AnnotationList declaredAnnotations = target.getDeclaringType().asErasure().getDeclaredAnnotations();
            if (!isController(declaredAnnotations))
                return true;
            return !isSpecificParam(target);
        }
        return true;
    }

    private boolean isSpecificParam(T target) {
        ParameterList<?> parameters = target.getParameters();
        for (ParameterDescription param : parameters) {
            if (isBasicType(param) && hasNullValueAnnotation(param.getDeclaredAnnotations()))
                return true;
        }
        return false;
    }

    private boolean isBasicType(ParameterDescription param) {
        return (param.getType().isPrimitive() || param.getType().asErasure().isPrimitiveWrapper() || param.getType().getActualName().equals("java.lang.String"));
    }

    private boolean hasNullValueAnnotation(AnnotationList declaredAnnotations) {
        if (declaredAnnotations.isEmpty())
            return true;
        for (AnnotationDescription anno : declaredAnnotations) {
            String name = anno.getAnnotationType().getName();
            if (RequestParam.class.getName().equals(name) && isBlank(anno, REQUEST_PARAM_NAME, REQUEST_PARAM_VALUE))
                return true;
            if (PathVariable.class.getName().equals(name) && isBlank(anno, PATH_VARIABLE_NAME, PATH_VARIABLE_VALUE))
                return true;
        }
        return false;
    }

    private boolean isBlank(AnnotationDescription anno, MethodDescription.InDefinedShape requestParamName, MethodDescription.InDefinedShape requestParamValue) {
        AnnotationValue<?, ?> annoNameValue = anno.getValue(requestParamName);
        AnnotationValue<?, ?> annoValue = anno.getValue(requestParamValue);
        return (StringUtil.isBlank(annoNameValue.resolve(String.class)) && StringUtil.isBlank((String) annoValue.resolve(String.class)));
    }

    private boolean isController(AnnotationList declaredAnnotations) {
        for (AnnotationDescription anno : declaredAnnotations) {
            String name = anno.getAnnotationType().getName();
            if (RestController.class.getName().equals(name) || Controller.class.getName().equals(name))
                return true;
        }
        return false;
    }
}
package org.ciapge.config.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author 朱林
 * @description
 * @date 2021/11/25 22:42
 */
public class MyBeanDefinitionParser implements BeanDefinitionParser {

    private Class<?> beanClass;
    public MyBeanDefinitionParser(Class<?> beanClass ) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        // 注册到spring容器中
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(beanClass);
        rootBeanDefinition.setLazyInit(false);
        String id = element.getAttribute("id");
        parserContext.getRegistry().registerBeanDefinition(id, rootBeanDefinition);
        for (Method method : beanClass.getMethods()) {
            // 判断赋值语句
            if (!isProperty(method, beanClass)) {
                continue;
            }
            String name = method.getName();
            String methodName = name.substring(3, 4).toLowerCase() + name.substring(4);
            String value = element.getAttribute(methodName);
            rootBeanDefinition.getPropertyValues().addPropertyValue(methodName, value);
        }
        return rootBeanDefinition;
        }

    private boolean isProperty(Method method, Class<?> beanClass) {
        String methodName = method.getName();
        boolean flag = methodName.length() > 3 && methodName.startsWith("set") && Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 1;
        if (!flag) {
            return false;
        }
        Class<?> type = method.getParameterTypes()[0];
        Method getter = null;
        try {
            getter = beanClass.getMethod("get" + methodName.substring(3));
        } catch (NoSuchMethodException ignore) {

        }

        if (null == getter) {
            try {
                getter = beanClass.getMethod("is" + methodName.substring(3));
            } catch (NoSuchMethodException ignore) {

            }
        }
        flag = getter != null && Modifier.isPublic(getter.getModifiers()) && type.equals(getter.getReturnType());
        return flag;
    }



}

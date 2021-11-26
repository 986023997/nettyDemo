package org.ciapge.config.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

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
        return null;
    }
}

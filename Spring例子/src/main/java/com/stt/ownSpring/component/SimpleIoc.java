package com.stt.ownSpring.component;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简写IOC容器
 */
public class SimpleIoc {

    private Map<String, Object> beanMap = new ConcurrentHashMap<>();

    public SimpleIoc(String location) throws Exception{
        this.loadBeans(location);
    }

    private void loadBeans(String location) throws Exception{
        // 加载 xml 配置文件
        InputStream inputStream = new FileInputStream(location);
        NodeList nodeList = nodeList(inputStream);
        loadBeans(nodeList);
    }

    private NodeList nodeList(InputStream inputStream) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);
        Element root = doc.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        return nodes;
    }


    // 加载 xml 配置文件
    public void loadBeans(NodeList nodes) throws Exception {
        for(int i = 0 ; i < nodes.getLength() ; i++){
            Node node = nodes.item(i);
            if(node instanceof Element){
                Element ele = (Element)node;
                String id = ele.getAttribute("id");
                String className = ele.getAttribute("class");

                //加载classBean
                Class beanClazz = null;
                try{
                    beanClazz = Class.forName(className);
                }catch (Exception e){
                    e.printStackTrace();
                    return;
                }

                // 创建 bean
                Object bean = beanClazz.newInstance();

                // 遍历 <property> 标签
                NodeList propertyNodes = ele.getElementsByTagName("property");
                for(int j = 0 ; j < propertyNodes.getLength() ; j++){
                    Node propertyNode  = propertyNodes.item(j);
                    if(propertyNode instanceof Element){
                        Element element = (Element)propertyNode;
                        String name = element.getAttribute("name");
                        String value = element.getAttribute("value");
                        // 利用反射将 bean 相关字段访问权限设为可访问
                        Field declaredField = beanClazz.getDeclaredField(name);
                        declaredField.setAccessible(true);
                        if(value != null && value.length() > 0){
                            // 将属性值填充到相关字段中
                            declaredField.set(bean,value);
                        }else{
                            String ref = element.getAttribute("ref");
                            if(ref == "" || ref.length() == 0){
                                throw new IllegalArgumentException("ref config error");
                            }
                            declaredField.set(bean,getBean(ref));
                        }
                        // 将 bean 注册到 bean 容器中
                        registerBean(id, bean);
                     }
                }
            }
        }
    }


    public Object getBean(String beanName){
        return beanMap.get(beanName);
    }

    private void registerBean(String id, Object bean) {
        beanMap.put(id, bean);
    }
}

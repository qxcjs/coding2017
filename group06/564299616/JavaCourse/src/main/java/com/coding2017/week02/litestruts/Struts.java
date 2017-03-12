package com.coding2017.week02.litestruts;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

public class Struts {

	private static final Map<String, ActionBean> map = new HashMap<String, ActionBean>();

	private static final Map<String, Object> beanMap = new HashMap<String, Object>();

	public static void main(String[] args) {

	}

	static {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("struts.xml");
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("struts", StrutsBean.class);
		digester.addObjectCreate("struts/action", ActionBean.class);
		digester.addSetProperties("struts/action", "name", "name");
		digester.addSetProperties("struts/action", "class", "clazz");
		digester.addCallMethod("struts/action/result", "put", 2);
		digester.addCallParam("struts/action/result", 0, "name");
		digester.addCallParam("struts/action/result", 1);
		digester.addSetNext("struts/action", "addAction");
		try {
			StrutsBean bean = (StrutsBean) digester.parse(in);
			for (ActionBean action : bean.getActions()) {
				map.put(action.getName(), action);
				try {
					beanMap.put(action.getName(), Class.forName(map.get(action.getName()).getClazz()).newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 0. 读取配置文件struts.xml
	 * 
	 * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
	 * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test"
	 * ,"password"="1234") , 那就应该调用 setName和setPassword方法
	 * 
	 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
	 * 
	 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如
	 * {"message": "登录成功"} , 放到View对象的parameters
	 * 
	 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp， 放到View对象的jsp字段中。
	 * 
	 * @param actionName
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static View runAction(String actionName, Map<String, String> parameters) throws Exception {
		if (!map.containsKey(actionName)) {
			throw new Exception();
		}
		Object object = beanMap.get(actionName);

		Field[] fields = object.getClass().getDeclaredFields();
		Map<String, String> fieldMap = new HashMap<String, String>();
		for (Field field : fields) {
			fieldMap.put(field.getName(), field.getName());
			field.setAccessible(true);
			field.set(object, parameters.get(field.getName()));
		}

		Method executeMethod = null;
		Method[] methods = object.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if ("execute".equals(method.getName())) {
				executeMethod = method;
			}
		}

		View view = new View();
		if (executeMethod != null) {
			Object invoke = executeMethod.invoke(object);
			view.setJsp(map.get(actionName).getResults().get(String.valueOf(invoke)));
			
			Map<String,Object> parameMap = new HashMap<String, Object>();
			for (Method method : methods) {
				if (method.getName().startsWith("get")) {
					String fieldName = getFieldName(method, fieldMap);
					if (fieldName != null) {
						Object value = method.invoke(object);
						parameMap.put(fieldName, value);
					}
				}
			}
			view.setParameters(parameMap);
		}
		return view;
	}

	private static String getFieldName(Method method, Map<String, String> fieldMap) {
		StringBuffer append = new StringBuffer().append(method.getName().substring(3, 4).toLowerCase())
				.append(method.getName().substring(4));

		return fieldMap.get(append.toString());
	}
}

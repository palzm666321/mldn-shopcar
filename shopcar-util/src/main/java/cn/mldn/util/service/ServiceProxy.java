package cn.mldn.util.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.mldn.util.dbc.DatabaseConnection;

public class ServiceProxy implements InvocationHandler {
	private Object target;

	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object backResult = null ;
		if (this.checkTransactionMethodName(method.getName())) {
			DatabaseConnection.getConnection().setAutoCommit(false);
		}
		try {
			backResult = method.invoke(this.target, args) ;
			if (this.checkTransactionMethodName(method.getName())) {
				DatabaseConnection.getConnection().commit();
			}
		} catch (Exception e) {
			if (this.checkTransactionMethodName(method.getName())) {
				DatabaseConnection.getConnection().rollback();
			}
			throw e ;
		} finally {
			DatabaseConnection.close();
		}
		return backResult ;
	}
	private boolean checkTransactionMethodName(String methodName) {
		return methodName.startsWith("add") || methodName.startsWith("edit") || methodName.startsWith("delete") ;
	}

}

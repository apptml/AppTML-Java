package io.github.apptml.iface;

public interface LanguageEngine {
	
	public Object evalUrl(String script);
	public Object get(String obj);
	public Object invoke(String func, Object... args);
	public void put(String name, Object value);
	
}

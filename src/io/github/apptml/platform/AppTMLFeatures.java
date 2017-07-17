package io.github.apptml.platform;

import java.util.HashMap;
import java.util.function.Consumer;

import org.jsoup.nodes.Element;

import io.github.coalangsoft.lib.data.Func;

public class AppTMLFeatures {
	
	private HashMap<String, Func<Element, Void>> features;
	
	{
		features = new HashMap<>();
	}
	
	public void add(String name, Func<Element, Void> c){
		features.put(name, c);		
	}
	public void use(String name, Element e){
		Func<Element, Void> c = features.get(name);
		if(c != null){
			c.call(e);
		}
	}
	
	public void forEach(final Func<String, Void> f){
		features.keySet().forEach(new Consumer<String>() {
			@Override
			public void accept(String arg0) {
				f.call(arg0);
			}
		});;
	}
	
}

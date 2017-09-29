package io.github.apptml.base.platform;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import io.github.coalangsoft.lib.data.Func;

public abstract class AppTMLFeatures<UI> {
	
	private AppTMLPlatform<UI> platform;
	
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
	
	public void setPlatform(AppTMLPlatform<UI> p){
//		throw new RuntimeException("NIy");
		this.platform = p;
		System.out.println(this);
	}
	public AppTMLPlatform<UI> getPlatform(){
		return platform;
	}
	
	public Document document(String url) throws MalformedURLException, IOException, URISyntaxException {
		//from <style> tags
		if(url.startsWith("file:/")){
			return Jsoup.parse(Paths.get(new URL(url).toURI()).toFile(), "UTF-8");
		}else{
			return Jsoup.parse(new URL(url), 3000);
		}
	}
	
}

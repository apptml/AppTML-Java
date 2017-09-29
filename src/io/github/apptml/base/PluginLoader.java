package io.github.apptml.base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

import io.github.apptml.base.iface.StyleLanguage;
import io.github.apptml.base.platform.AppTMLPlatform;
import io.github.apptml.urlscripting.ScriptLanguage;
import io.github.coalangsoft.lib.data.Pair;

public class PluginLoader {
	
	public static <UI> void applyAll(Properties ps, AppTMLPlatform<UI> p) throws ClassNotFoundException, IOException{
		String[] jars = ps.getProperty("library.plugin.jars").split(";");
		String[] classes = ps.getProperty("library.plugin.classes").split(";");
		applyAll(jars,classes,p);
	}
	
	@SuppressWarnings("unchecked")
	public static <UI> void applyAll(String[] jars, String[] classes, AppTMLPlatform<UI> p) throws ClassNotFoundException, IOException{
		URL[] jarUrls = new URL[jars.length];
		for(int i = 0; i < jarUrls.length; i++){
			jarUrls[i] = new URL(jars[i]);
		}
		URLClassLoader cl = new URLClassLoader(jarUrls);
		for(int i = 0; i < classes.length; i++){
			apply((Class<? extends AppTMLPlugin<UI>>) cl.loadClass(classes[i]), p);
		}
		cl.close();
	}
	
	public static <UI> void apply(Class<? extends AppTMLPlugin<UI>> c, AppTMLPlatform<UI> p){
		try {
			apply0(c.getClassLoader(), c.newInstance(), p);
		} catch (InstantiationException | IllegalAccessException e) {
			try {
				apply0(c.getClassLoader(), c.newInstance(), p);
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static <UI> void apply0(ClassLoader cl, AppTMLPlugin<UI> plugin, AppTMLPlatform<UI> p) {
		Pair<String,ScriptLanguage>[] scriptLanguages = plugin.getScriptLanguages(cl);
		for(int i = 0; i < scriptLanguages.length; i++){
			Pair<String,ScriptLanguage> pair = scriptLanguages[i];
			p.scriptLanguages.put(pair.getA(), pair.getB());
		}
		
		Pair<String,StyleLanguage<UI>>[] styleLanguages = plugin.getStyleLanguages();
		for(int i = 0; i < styleLanguages.length; i++){
			Pair<String,StyleLanguage<UI>> pair = styleLanguages[i];
			p.styleLanguages.put(pair.getA(), pair.getB());
		}
	}
	
}

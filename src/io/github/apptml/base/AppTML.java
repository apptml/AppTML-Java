package io.github.apptml.base;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Properties;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import io.github.apptml.base.platform.AppTMLDisplay;
import io.github.apptml.base.platform.AppTMLFeatures;
import io.github.apptml.base.platform.AppTMLLauncher;
import io.github.apptml.base.platform.AppTMLPlatform;
import io.github.coalangsoft.lib.data.Func;
import io.github.coalangsoft.lib.security.SecurityProfile;

public class AppTML<UI> {
	
	private ArrayList<AppTMLPlatform<UI>> platforms;
	private Properties settings;
	
	{
		platforms = new ArrayList<>();
	}
	
	public AppTML(Properties settings){
		this.settings = settings;
	}
	
	public void bootDocument(Document doc, String url) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		//apptml-launcher
		String launcher = doc.getElementsByTag("apptml-launcher").get(0).attr("name");
		launch(loadPlatform(launcher), doc, url);
	}

	public void launch(AppTMLPlatform<UI> p, final Document doc, String url) {
		try {
			PluginLoader.applyAll(settings, p);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final AppTMLFeatures<UI> f = p.features;
		
		//use features
		f.forEach(new Func<String, Void>(){

			@Override
			public Void call(String p) {
				Elements tags = doc.getElementsByTag("apptml-" + p);
				for(int i = 0; i < tags.size(); i++){
					f.use(p, tags.get(i));
				}
				return null;
			}
			
		});
		
		AppTMLLauncher<UI> l = p.launcher;
		
		//platform main tag
		Elements mainTags = doc.getElementsByTag("apptml-" + p.name);
		for(int i = 0; i < mainTags.size(); i++){
			l.onMainTag(f, mainTags.get(i));
		}
		
		AppTMLDisplay d = l.display(f, url, doc);
		d.show();
	}

	private AppTMLPlatform<UI> loadPlatform(String lib) throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String libJar = settings.getProperty("library.jar." + lib);
		if(libJar != null){
			URLClassLoader l = new URLClassLoader(
				new URL[]{new URL(libJar)}
			);
			String libClass = settings.getProperty("library.class." + lib);
			return (AppTMLPlatform<UI>) l.loadClass(libClass).newInstance();
		}else{
			throw new RuntimeException(lib);
		}
	}
	
}

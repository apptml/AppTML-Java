package io.github.apptml.base.platform;

import java.util.HashMap;

import io.github.apptml.base.iface.StyleLanguage;
import io.github.apptml.urlscripting.ScriptLanguage;

public class AppTMLPlatform<UI> {
	
	public final String name;
	public final AppTMLLauncher<UI> launcher;
	public final AppTMLFeatures<UI> features;
	public final HashMap<String, ScriptLanguage> scriptLanguages;
	public final HashMap<String, StyleLanguage<UI>> styleLanguages;
	
	public AppTMLPlatform(String name, AppTMLLauncher<UI> launcher, AppTMLFeatures<UI> features){
		this.name = name;
		this.launcher = launcher;
		this.features = features;
		this.scriptLanguages = new HashMap<>();
		this.styleLanguages = new HashMap<>();
		this.features.setPlatform(this);
	}
	
}

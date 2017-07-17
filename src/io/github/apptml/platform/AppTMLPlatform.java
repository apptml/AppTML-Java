package io.github.apptml.platform;

public class AppTMLPlatform<JSEngine> {
	
	public final String name;
	public final AppTMLLauncher<JSEngine> launcher;
	public final AppTMLFeatures features;
	
	public AppTMLPlatform(String name, AppTMLLauncher<JSEngine> launcher, AppTMLFeatures features){
		this.name = name;
		this.launcher = launcher;
		this.features = features;
	}
	
}

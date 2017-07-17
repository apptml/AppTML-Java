package io.github.apptml.platform;

import org.jsoup.nodes.Element;

public interface AppTMLLauncher<JSEngine> {
	
	/**
	 * Initializes the AppTML web runtime.
	 * After calling this, the display contains the webpage specified and the "apptml" JavaScript object is initialized.
	 * @param features The platform features
	 * @param url Which URL to load
	 * @return the display to show first
	 */
	AppTMLDisplay<JSEngine> display(AppTMLFeatures features, String url);
	void onMainTag(Element mainTag);
	
}

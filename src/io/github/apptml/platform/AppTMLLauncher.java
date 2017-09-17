package io.github.apptml.platform;

import org.jsoup.nodes.Element;

public interface AppTMLLauncher<UI> {
	
	/**
	 * Initializes the AppTML web runtime.
	 * After calling this, the display contains the webpage specified and the "apptml" JavaScript object is initialized.
	 * @param features The platform features
	 * @param url Which URL to load
	 * @return the display to show first
	 */
	AppTMLDisplay display(AppTMLFeatures<UI> features, String url);
	void onMainTag(AppTMLFeatures<UI> f, Element mainTag);
	
}

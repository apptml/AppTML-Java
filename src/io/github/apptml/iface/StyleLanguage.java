package io.github.apptml.iface;

import org.jsoup.nodes.Element;

import io.github.apptml.platform.AppTMLFeatures;

public interface StyleLanguage<UI> {
	
	UI createUI(AppTMLFeatures<UI> features, Element data);
	
}

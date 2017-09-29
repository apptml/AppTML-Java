package io.github.apptml.base.iface;

import org.jsoup.nodes.Element;

import io.github.apptml.base.platform.AppTMLFeatures;

public interface StyleLanguage<UI> {
	
	UI createUI(AppTMLFeatures<UI> features, Element data);
	
}

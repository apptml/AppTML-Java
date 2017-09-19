package io.github.apptml.platform;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public interface AppTMLLauncher<UI> {
	
	AppTMLDisplay display(AppTMLFeatures<UI> features, String url, Document doc);
	void onMainTag(AppTMLFeatures<UI> f, Element mainTag);
	
}

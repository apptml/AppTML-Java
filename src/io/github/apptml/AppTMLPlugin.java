package io.github.apptml;

import io.github.apptml.iface.ScriptLanguage;
import io.github.apptml.iface.StyleLanguage;
import io.github.coalangsoft.lib.data.Pair;

public interface AppTMLPlugin<UI> {
	
	Pair<String, ScriptLanguage> getScriptLanguages();
	Pair<String, StyleLanguage<UI>> getStyleLanguages();
	
}

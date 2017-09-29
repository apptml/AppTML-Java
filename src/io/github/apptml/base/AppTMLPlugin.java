package io.github.apptml.base;

import io.github.apptml.base.iface.StyleLanguage;
import io.github.apptml.urlscripting.ScriptLanguage;
import io.github.coalangsoft.lib.data.Pair;

public interface AppTMLPlugin<UI> {
	
	Pair<String, ScriptLanguage>[] getScriptLanguages(ClassLoader cl);
	Pair<String, StyleLanguage<UI>>[] getStyleLanguages();
	
}

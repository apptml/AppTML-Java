import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.jsoup.Jsoup;

import io.github.apptml.AppTML;

public class AppTMLTest {

	public static void main(String[] args) throws MalformedURLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Properties ps = new Properties();
		File f = new File("F:/Info/CCL/AppTML/pseudo.html");
		ps.load(new FileInputStream(new File("settings.properties")));
		new AppTML(ps).bootDocument(Jsoup.parse(f, "UTF-8"), f.toURI().toURL().toExternalForm());
	}
	
}

package com.compare.response;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

public interface IComparator {
	
	
	public boolean compare(URL url1, URL url2);
	
	public HashMap<String,String> getData(File file1, File file2);

}

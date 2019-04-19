package com.compare.response;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

public interface IComparator {
	
	
	public boolean compare(String url1, String url2);
	
	public String[] getData(File file1, File file2 , int i);

}

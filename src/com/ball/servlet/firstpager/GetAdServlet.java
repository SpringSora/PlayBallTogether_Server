package com.ball.servlet.firstpager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ball.bean.Advertisement;
import com.google.gson.Gson;

@WebServlet(urlPatterns={"/GetAdServlet"})
public class GetAdServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Advertisement> advertisements;
	private HashMap<String, String> map;
	private List<String> imageNames;
	private String root;
	public GetAdServlet() {
		// TODO Auto-generated constructor stub
		advertisements = new ArrayList<Advertisement>();
		map = new HashMap<String, String>();
		imageNames = new ArrayList<String>();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filePath = req.getSession().getServletContext().getRealPath("/Ad_Pic");
		File file = new File(filePath);
		root = req.getSession().getServletContext().getRealPath("");
		showAllFiles(file);
		/*for(Iterator<String> iterator = imageNames.iterator();iterator.hasNext();){
		}*/
		Iterator<String> iterator = imageNames.iterator();
		while(iterator.hasNext()){
			String Path = iterator.next();
			String picName = Path.split("/")[2];
			advertisements.add(new Advertisement(Path, map.get(picName.split("\\.")[0])));
		}
		Gson gson = new Gson();
		resp.getWriter().write(gson.toJson(advertisements));
		advertisements.clear();
		map.clear();
		imageNames.clear();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}
	
	private void showAllFiles(File dir){
		File[] fs = dir.listFiles();
		for(int i = 0;i < fs.length;i++){
			if(fs[i].getName().toLowerCase().endsWith(".jpg")||fs[i].getName().toLowerCase().endsWith(".png")||fs[i].getName().toLowerCase().endsWith(".bmp")||fs[i].getName().toLowerCase().endsWith(".jpeg")){
				imageNames.add(fs[i].getAbsolutePath().substring(root.length()).replaceAll("\\\\", "/"));
			}
			if(fs[i].getName().toLowerCase().endsWith(".txt")){
				String encoding = "GBK";
				try {
					InputStreamReader reader = new InputStreamReader(new FileInputStream(fs[i].getAbsoluteFile()),encoding);
					BufferedReader bufferedReader = new BufferedReader(reader);
					String url = null;
					while((url = bufferedReader.readLine())!=null){
						map.put(fs[i].getName().split("\\.")[0], url);
					}
					reader.close();
					bufferedReader.close();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fs[i].isDirectory()){
				showAllFiles(fs[i]);
			}
		}
	}
}

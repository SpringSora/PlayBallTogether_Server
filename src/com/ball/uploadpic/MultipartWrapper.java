package com.ball.uploadpic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;




public class MultipartWrapper extends HttpServletRequestWrapper {

	private Map<String, String[]> params = new HashMap<String, String[]>();

	// private final static String PATH =
	// "C:\\Users\\John_wen\\Workspaces\\MyEclipse 10\\FileUpload\\WebRoot\\upload\\";

	public MultipartWrapper(HttpServletRequest request,String SellerIdFile) throws FileUploadException, IOException {
		super(request);
		setParams(request,SellerIdFile);
	}
	private void setParams(HttpServletRequest request,String SellerIdFile) throws FileUploadException, IOException {
		boolean isMul = ServletFileUpload.isMultipartContent(request);
		if (isMul) {
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator fileItemIterator = upload.getItemIterator(request);
			InputStream is = null;
			while (fileItemIterator.hasNext()) {
				FileItemStream fis = fileItemIterator.next();
				is = fis.openStream();
				if (fis.isFormField()) {
					setFormField(fis.getFieldName(), is);
				} else {
					if (fis.getName() != null && !"".equals(fis.getName())) {
						String path = request.getSession().getServletContext().getRealPath("/UpLoad/"+SellerIdFile);
						File file = new File(path);
						if(!file.exists()){
							file.mkdirs();
						}
						Streams.copy(is, new FileOutputStream(path + "\\" + fis.getName()), true);
					}
					params.put(fis.getFieldName(), new String[] { fis.getName() });
				}
			}
		} else {
			params = request.getParameterMap();
		}
	}
	


	private void setFormField(String fieldName, InputStream is) throws IOException {
		String[] values = params.get(fieldName);
		if (params.containsKey(fieldName)) {
			values = Arrays.copyOf(values, values.length + 1);
			values[values.length - 1] = Streams.asString(is);
			params.put(fieldName, values);
		} else {
			params.put(fieldName, new String[] { Streams.asString(is) });
		}
	}

	@Override
	public String getParameter(String name) {
		String[] values = params.get(name);
		if (values != null) {
			return values[0];
		} else {
			return null;
		}
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return params;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = params.get(name);
		if (values != null) {
			return values;
		} else {
			return null;
		}
	}
}

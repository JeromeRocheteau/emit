package fr.icam.emit.listeners;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class MockHttpServletResponse implements HttpServletResponse {

	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(System.out);
	}

	public void setCharacterEncoding(String charset) {
		// TODO Auto-generated method stub

	}

	public void setContentLength(int len) {
		// TODO Auto-generated method stub

	}

	public void setContentType(String type) {
		// TODO Auto-generated method stub

	}

	public void setBufferSize(int size) {
		// TODO Auto-generated method stub

	}

	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub

	}

	public void resetBuffer() {
		// TODO Auto-generated method stub

	}

	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub

	}

	public void setLocale(Locale loc) {
		// TODO Auto-generated method stub

	}

	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addCookie(Cookie cookie) {
		// TODO Auto-generated method stub

	}

	public boolean containsHeader(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public String encodeURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	public String encodeRedirectURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	public String encodeUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	public String encodeRedirectUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	public void sendError(int sc, String msg) throws IOException {
		// TODO Auto-generated method stub

	}

	public void sendError(int sc) throws IOException {
		// TODO Auto-generated method stub

	}

	public void sendRedirect(String location) throws IOException {
		// TODO Auto-generated method stub

	}

	public void setDateHeader(String name, long date) {
		// TODO Auto-generated method stub

	}

	public void addDateHeader(String name, long date) {
		// TODO Auto-generated method stub

	}

	public void setHeader(String name, String value) {
		// TODO Auto-generated method stub

	}

	public void addHeader(String name, String value) {
		// TODO Auto-generated method stub

	}

	public void setIntHeader(String name, int value) {
		// TODO Auto-generated method stub

	}

	public void addIntHeader(String name, int value) {
		// TODO Auto-generated method stub

	}

	public void setStatus(int sc) {
		// TODO Auto-generated method stub

	}

	public void setStatus(int sc, String sm) {
		// TODO Auto-generated method stub

	}

}

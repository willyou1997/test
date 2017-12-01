package com.struts.trio;

import database.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.struts2.ServletActionContext;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class HelloAction{
	public String ID;
	//private ServletRequest request;
	private ArrayList<String> list = null;
	
    //password = request.getParameter("password");
	public ArrayList<String> getList() {
		return this.list;
	}

	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
	/*public String getpassword() {
		return this.password;
	}

	public void setpassword(String password) {
		this.password = password;
	}*/
	
	public String towelcome(){
		return "SUCCESS";
	}
	 
	public String tosign(){
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		String password = Srequest.getParameter("password");
		int i;
		System.out.println(ID + " " + password);
		String sql1 = "select * from usersID";
		connect newc = new connect();
		ArrayList<Map<String, String>> result1 = newc.select(sql1, "usersID");
		if (result1.size() == 0) {
			return "FALSE";
		}
		System.out.println(result1.size());
		for (i = 0 ; i < result1.size(); i ++) {
			System.out.println(i);
			System.out.println(this.ID.equals(result1.get(i).get("ID")));
			System.out.println(password.equals(result1.get(i).get("password")));
			if (this.ID.equals(result1.get(i).get("ID")) && password.equals(result1.get(i).get("password"))) {
				setID(ID);
				//System.out.println("Success");
				return "SUCCESS";
			}
		}
		
		
		//session.setAttribute("result", result2);

		return "FALSE";
	}
	
	public String tomain() {
		return "SUCCESS";
	}
	
	public String tomoniter() {
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		String password = Srequest.getParameter("password");
		int i;
		//System.out.println(ID + " " + password);
		String sql1 = "select * from moniter";
		connect newc = new connect();
		ArrayList<Map<String, String>> result1 = newc.select(sql1, "moniter");
		if (result1.size() == 0) {
			return "FALSE";
		}
		System.out.println(result1.size());
		for (i = 0 ; i < result1.size(); i ++) {
			System.out.println(i);
			System.out.println(this.ID.equals(result1.get(i).get("ID")));
			System.out.println(password.equals(result1.get(i).get("password")));
			if (this.ID.equals(result1.get(i).get("ID")) && password.equals(result1.get(i).get("password"))) {
				System.out.println("Success");
				return "SUCCESS";
			}
		}
		
		
		//session.setAttribute("result", result2);

		return "FALSE";
	}
	
	public String tomonitermain() {
		return "SUCCESS";
	}
	
	public String tocancel() {
		//System.out.println(ID);
		String sql1 = "delete from usersid where ID=" + "\"" + this.ID + "\"";
		//System.out.println(this.ID);
		connect newc = new connect();
		int result1 = newc.delete(sql1);
		//System.out.println(result);
		
        String sql2 = "insert into nousers values (\"" + this.ID + "\")";
        int result2 = newc.update(sql2);
        if (result1 == 0 || result2 == 0) {
			return "FALSE";
		} 
        return "SUCCESS";
	}
	
	public String tonouseradd(){
		String sql = "insert into nousers values (\"" + this.ID + "\")";
		connect newc = new connect();
		int result = newc.update(sql);
        if (result == 0) {
			return "FALSE";
		} 
        return "SUCCESS";
	}
	
	public String tousers() {
		String sql1 = "select * from usersid";
		String sql2 = "select * from nousers";
		connect mc = new connect();
		ArrayList<Map<String, String>> result1 = mc.select(sql1, "usersid");
		ArrayList<Map<String, String>> result2 = mc.select(sql2, "nousers");
		if (result1.size() == 0 && result2.size() == 0) {
			return "FALSE";
		}
		if (result1.size() == 0){
			return "FALSE1";
		}
		if (result2.size() == 0){
			return "FALSE2";
		}
		ServletRequest request = ServletActionContext.getRequest();
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		session.setAttribute("list1", result1);
		
		session.setAttribute("list2", result2);

		return "SUCCESS";
		
	}
	
	public String towrite() {
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		String password = Srequest.getParameter("password");
		int i;
		//System.out.println(ID + " " + password);
		String sql1 = "select * from nousers";
		connect newc = new connect();
		ArrayList<Map<String, String>> result1 = newc.select(sql1, "nousers");
		if (result1.size() == 0) {
			return "FALSE";
		}
		//System.out.println("pp" + result1.size());
		for (i = 0 ; i < result1.size(); i ++) {
			//System.out.println(i);
			//System.out.println(this.ID.equals(result1.get(i).get("ID")));
			//System.out.println(password.equals(result1.get(i).get("password")));
			if (this.ID.equals(result1.get(i).get("ID"))) {
				String sql2 = "insert into usersid values(" + "\"" + ID + "\"" + "," + "\"" + password + "\"" +")";
				String sql3 = "delete from nousers where ID=" + "\"" + ID + "\"";
				System.out.println(sql3);
				int result = newc.delete(sql3);
				if (result == 0) {
					return "FALSE";
				} 
				System.out.println(sql2);
				int status = newc.update(sql2);
				if (status == 0)
					return "FALSE";
				//System.out.println("Success");
				return "SUCCESS";
			}
		}
		
		
		//session.setAttribute("result", result2);

		return "FALSE";
	}
	
	public String tocreate() {
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		String treename = Srequest.getParameter("treename");
		String father = Srequest.getParameter("father");
		String son = Srequest.getParameter("son");
		String time = Srequest.getParameter("time");
		int year , month , day;
		year = Integer.parseInt(time.substring(0,4));
		month = Integer.parseInt(time.substring(5,7));
		day = Integer.parseInt(time.substring(8,10));
		String sql = "insert into users values(" + "\"" + treename + "\"" + "," + "\"" + "user1" + "\"" + "," + "\"" + father + "\"" + "," + "\"" + son + "\"" + ","
				+year + "," + month + "," + day + ")";
		System.out.println(sql);
		connect mc = new connect();
		int status = mc.update(sql);
		if (status == 0)
			return "FALSE";

		return "SUCCESS";
	}
	
	public String tochange() {
		return "SUCCESS";
	}

	public String tosearch(){
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		String name = Srequest.getParameter("name");
		String sql = "select * from users where name = \"" + name + "\"";
		connect newc = new connect();
		ArrayList<Map<String, String>> result = newc.select(sql, "users");
		if (result.size() == 0) {
			return "FALSE";
		}
		int i;
		for (i = 0 ; i < result.size(); i ++) {
			System.out.println(result.get(i));
	    }
		return "SUCCESS";
	}
	public String toadd() {
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		String treename = Srequest.getParameter("treename");
		String father = Srequest.getParameter("father");
		String gfather = Srequest.getParameter("gfather");
		String son = Srequest.getParameter("son");
		String time1 = Srequest.getParameter("time1");
		int year1 , month1 , day1;
		year1 = Integer.parseInt(time1.substring(0,4));
		month1 = Integer.parseInt(time1.substring(5,7));
		day1 = Integer.parseInt(time1.substring(8,10));
		String time2 = Srequest.getParameter("time2");
		int year2 , month2 , day2;
		year2 = Integer.parseInt(time2.substring(0,4));
		month2 = Integer.parseInt(time2.substring(5,7));
		day2 = Integer.parseInt(time2.substring(8,10));
		connect mc = new connect();
		int status1,status2;
		if (!(son.equals("null"))){
			String sql1 = "insert into users values(" + "\"" + treename + "\"" + "," + "\"" + "user1" + "\"" + "," + "\"" + father + "\"" + "," + "\"" + son + "\"" + ","
					+year1 + "," + month1 + "," + day1 + ")";
			status1 = mc.update(sql1);
		}
		else{
			status1 = 1;
		}
		if (!(gfather.equals("null"))){
			String sql2 = "insert into users values(" + "\"" + treename + "\"" + "," + "\"" + "user1" + "\"" + "," + "\"" + gfather + "\"" + "," + "\"" + father + "\"" + ","
					+year2 + "," + month2 + "," + day2 + ")";
			status2 = mc.update(sql2);
		}
		else{
			status2 =1;
		}
		/*String sql1 = "insert into users values(" + "\"" + treename + "\"" + "," + "\"" + "user1" + "\"" + "," + "\"" + father + "\"" + "," + "\"" + son + "\"" + ","
				+year1 + "," + month1 + "," + day1 + ")";
		String sql2 = "insert into users values(" + "\"" + treename + "\"" + "," + "\"" + "user1" + "\"" + "," + "\"" + gfather + "\"" + "," + "\"" + father + "\"" + ","
				+year2 + "," + month2 + "," + day2 + ")";
		System.out.println(sql1);
		connect mc = new connect();
		int status1 = mc.update(sql1);
		int status2 = mc.update(sql2);*/
		if (status1 == 0 || status2 == 0)
			return "FALSE";

		return "SUCCESS";
	}
	
	public String todelete() {
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		String treename = Srequest.getParameter("treename");
		String name = Srequest.getParameter("name");
		String father = Srequest.getParameter("father");
		String son = Srequest.getParameter("son");
		String time = Srequest.getParameter("time");
		connect mc = new connect();
		int status;
	    String sql = "delete from users where treename=" + "\"" + treename + "\"" + "and name=" + "\"" + name + "\"" + "and father=" + "\"" + father + "\"" + "and son=" + "\"" + son + "\"";
		status = mc.delete(sql);
		
		if (status == 0) {
			return "FALSE";
		} else {
			return "SUCCESS";
		}
	}
	
	public String toupdate() {
		return "SUCCESS";
	}
	
	public String tome() {
		return "SUCCESS";
	}
	
	public String toother() {
		return "SUCCESS";
	}
	
	public String toname() {
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		String oldname = Srequest.getParameter("oldname");
		String newname = Srequest.getParameter("newname");
		String sql1 = "update users set father=\"" + newname + "\" where father=\"" + oldname +"\""; 
		String sql2 = "update users set son=\"" + newname + "\" where son=\"" + oldname +"\"";
		
		System.out.println(sql2);
		connect mc = new connect();
		int status1 = mc.delete(sql1);
		int status2 = mc.delete(sql2);
		
		if (status1 == 0 && status2 == 0) {
			System.out.println("1:" + status1 + "2:" + status2);
			return "FALSE";
		} else {
			return "SUCCESS";
		}
	}
	
	public String totime() {
		//System.out.println(ID);
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		String treename = Srequest.getParameter("treename");
		String name = Srequest.getParameter("name");
		String father = Srequest.getParameter("father");
		String son = Srequest.getParameter("son");
		String time = Srequest.getParameter("time");
		int year , month , day;
		year = Integer.parseInt(time.substring(0,4));
		month = Integer.parseInt(time.substring(5,7));
		day = Integer.parseInt(time.substring(8,10));
		String sql = "update users set year=" + year + ", month=" + month + ",day=" + day + " where treename=\"" + treename + "\"and name=\"" + name + "\"and father=\"" + father
				+ "\"and son=\"" + son + "\"";
		System.out.println(sql);
		connect mc = new connect();
		int status = mc.delete(sql);
			
		if (status == 0) {
			return "FALSE";
		} else {
			return "SUCCESS";
		}
	}
	
	public String torelation() {
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		String father = Srequest.getParameter("father");
		String son = Srequest.getParameter("son");
		String newfather = Srequest.getParameter("newfather");
		String newson = Srequest.getParameter("newson");
		String sql = "update users set father=\"" + newfather + "\", son=\"" + newson + "\" where father=\"" + father +"\" and son =\"" + son + "\""; 
        //String sql2 = "update users set son=\"" + newname + "\" where son=\"" + oldname +"\"";
		
		//System.out.println(sql);
		connect mc = new connect();
		int status = mc.delete(sql);
		
		if (status == 0) {
			return "FALSE";
		} else {
			return "SUCCESS";
		}
	}
	
	public int mergetemp(String treename , String newname ,String ID , String father , connect newc){
		String sql1 = "select * from users where treename = " + "\"" + treename +"\" and name = \"" + ID + "\" and father = \"" + father + "\"";
		System.out.println("11" + sql1 + "\n");
		//connect newc = new connect();
		ArrayList<Map<String, String>> result = newc.select(sql1, "users");
		if (result.size() == 0) {
			return 0;
		}
		System.out.println(result.size());
		int i;
		for (i = 0 ; i < result.size(); i ++) {
			/*System.out.println(i);
			System.out.println(this.ID.equals(result1.get(i).get("ID")));
			System.out.println(password.equals(result1.get(i).get("password")));
			if (this.ID.equals(result1.get(i).get("ID")) && password.equals(result1.get(i).get("password"))) {
				//System.out.println("Success");
				return "SUCCESS";
			}*/
			String sql2 = "update users set treename = \"" + newname + "\" where treename=\"" + treename + "\"and name=\"" + ID + "\"and father=\"" + father + "\"";
			System.out.println("12" + sql2 + "\n");
			int status = newc.update(sql2);
			mergetemp(treename,newname,ID,result.get(i).get("son"),newc);
			return status;
		}
		return 1;
	}
	
	public String tomerge() {
		ServletRequest Srequest = ServletActionContext.getRequest();
		HttpServletRequest Sreq = (HttpServletRequest) Srequest;
		HttpSession session = Sreq.getSession();
		String treename = Srequest.getParameter("treename");
		String father = Srequest.getParameter("father");
		String ID = Srequest.getParameter("ID");
		String sql1 = "select * from users where treename != " + "\"" + treename +"\" and name = \"" + ID + "\" and father = \"" + father + "\"";
		//System.out.println("00" + sql1 + "\n");
		connect newc = new connect();
		ArrayList<Map<String, String>> result = newc.select(sql1, "users");
		if (result.size() == 0) {
			return "FALSE";
		}
		System.out.println("01" + sql1 + "\n");
		int i , status;
		for (i = 0 ; i < result.size(); i ++) {
			status = mergetemp(result.get(i).get("treename"),treename,ID,father,newc);
			if (status == 0){
				return "FALSE";
			}
	    }
		return "SUCCESS";
	}
	
	public String toresult() {
		return "SUCCESS";
	}
	
	public String tohome() {
		return "SUCCESS";
	}
}

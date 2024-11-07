package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNum()
	{
	Random random=new Random();
	int randomNumber=random.nextInt(5000);
	return randomNumber;
	}
	
	public String getSystemDateYYYYDDMM()
	{
		//get current date
		Date dateobj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String date=sim.format(dateobj);
		return date;
	}
	public String getRequiredDateYYYYMMDD(int days) {
		
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		//String Startdate=sim.format(dateobj);
		
        Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String requiredDate=sim.format(cal.getTime());
		return requiredDate;
       }
	
	
}



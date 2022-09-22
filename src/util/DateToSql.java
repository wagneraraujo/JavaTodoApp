package util;

import java.util.Date;

public class DateToSql
{
    public static void main(String args[]){
        Date uDate = new Date();
        java.sql.Date sql_date = convertDate(uDate);


    }

    private static java.sql.Date convertDate(java.util.Date date){
java.sql.Date myDate = new java.sql.Date(date.getTime());
return myDate;
    }
}

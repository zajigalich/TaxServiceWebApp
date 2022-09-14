package com.my.web.customTags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DateTagHandler extends SimpleTagSupport {

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        Calendar cal = Calendar.getInstance();
        out.println(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(cal.getTime()));
    }
}

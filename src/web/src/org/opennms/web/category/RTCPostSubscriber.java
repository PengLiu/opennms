//
// This file is part of the OpenNMS(R) Application.
//
// OpenNMS(R) is Copyright (C) 2002-2003 Blast Internet Services, Inc.  All rights reserved.
// OpenNMS(R) is a derivative work, containing both original code, included code and modified
// code that was published under the GNU General Public License. Copyrights for modified 
// and included code are below.
//
// OpenNMS(R) is a registered trademark of Blast Internet Services, Inc.
//
// Copyright (C) 1999-2001 Oculan Corp.  All rights reserved.
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
//
// For more information contact:
//      OpenNMS Licensing       <license@opennms.org>
//      http://www.opennms.org/
//      http://www.blast.com/
//

package org.opennms.web.category;

import java.io.IOException;

import org.apache.log4j.Category;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.opennms.core.resource.Vault;
import org.opennms.core.utils.ThreadCategory;
import org.opennms.netmgt.EventConstants;
import org.opennms.netmgt.config.ViewsDisplayFactory;
import org.opennms.netmgt.config.viewsdisplay.Section;
import org.opennms.netmgt.config.viewsdisplay.View;
import org.opennms.netmgt.utils.EventProxy;
import org.opennms.netmgt.utils.TcpEventProxy;
import org.opennms.netmgt.xml.event.Event;
import org.opennms.netmgt.xml.event.Parm;
import org.opennms.netmgt.xml.event.Parms;
import org.opennms.netmgt.xml.event.Value;
import org.opennms.web.Util;


/**
 * @author <A HREF="larry@opennms.org">Larry Karnowski</A>
 * @author <A HREF="http://www.opennms.org/">OpenNMS</A>
 */
public class RTCPostSubscriber extends Object
{
    protected EventProxy proxy;
    protected String url;
    protected String username;
    protected String password;

    protected static Category log = ThreadCategory.getInstance( "RTC" );
    
    public RTCPostSubscriber() throws IOException, MarshalException, ValidationException {
        this.proxy = new TcpEventProxy();
    }


    public static void sendSubscribeEvent( EventProxy proxy, String url, String username, String password, String categoryName ) {
        if( proxy == null || url == null || username == null || password == null || categoryName == null ) {
            throw new IllegalArgumentException( "Cannot take null parameters." );
        }

        Event event = new Event();
        event.setSource("RTCPostSubscriber");
        event.setUei(EventConstants.RTC_SUBSCRIBE_EVENT_UEI);        
        event.setHost("host");
        event.setTime(EventConstants.formatToString(new java.util.Date()));
        
        Parms parms = new Parms();

        // URL        
        Value value = new Value();
        value.setContent(url);
        Parm parm = new Parm();
        parm.setParmName(EventConstants.PARM_URL);
        parm.setValue(value);
        parms.addParm(parm);

        // User        
        value = new Value();
        value.setContent(username);
        parm = new Parm();
        parm.setParmName(EventConstants.PARM_USER);
        parm.setValue(value);
        parms.addParm(parm);        

        // Password        
        value = new Value();
        value.setContent(password);
        parm = new Parm();
        parm.setParmName(EventConstants.PARM_PASSWD);
        parm.setValue(value);
        parms.addParm(parm);        

        // category        
        value = new Value();
        value.setContent(categoryName);
        parm = new Parm();
        parm.setParmName(EventConstants.PARM_CAT_LABEL);
        parm.setValue(value);
        parms.addParm(parm);        

        event.setParms( parms );

        proxy.send( event );
        
        log.info("Subscription requested for " + username + " to " + url );
    }


    public static void sendUnsubscribeEvent( EventProxy proxy, String url ) {        
        if( proxy == null || url == null ) {
            throw new IllegalArgumentException( "Cannot take null parameters." );
        }
                       
        Event event = new Event();
        event.setSource("RTCPostSubscriber");
        event.setUei(EventConstants.RTC_UNSUBSCRIBE_EVENT_UEI);        
        event.setHost("host");
        event.setTime(EventConstants.formatToString(new java.util.Date()));
        
        // URL
        Parms parms = new Parms();        
        Value value = new Value();
        value.setContent(url);
        Parm parm = new Parm();
        parm.setParmName(EventConstants.PARM_URL);
        parm.setValue(value);
        parms.addParm(parm);
        
        event.setParms( parms );

        proxy.send( event );
        
        log.info("Unsubscription sent for " + url );        
    }



    public String subscribe( String categoryName ) {
        if( categoryName == null ) {
            throw new IllegalArgumentException( "Cannot take null parameters." );
        }

        this.initFromRtcPropertyFile(categoryName);

        sendSubscribeEvent( this.proxy, this.url, this.username, this.password, categoryName );
        return( this.url );
    }


    public void unsubscribe() {
        sendUnsubscribeEvent( this.proxy, this.url );        
    }
    
    
    public void close() {
        ((TcpEventProxy)proxy).close();
        this.proxy = null;
    }
    
    protected void initFromRtcPropertyFile( String categoryName) {
        if( categoryName == null ) {
            throw new IllegalArgumentException( "Cannot take null parameters." );
        }
        
        this.username  = Vault.getProperty("opennms.rtc-client.http-post.username");
        this.password  = Vault.getProperty("opennms.rtc-client.http-post.password");
        String baseUrl = Vault.getProperty("opennms.rtc-client.http-post.base-url");

        if( baseUrl.endsWith("/") ) {
            this.url = baseUrl + Util.encode(categoryName);                
        }
        else {
            this.url = baseUrl + "/" + Util.encode(categoryName);
        }
        
        log.debug("RTCPostSubscriber initialized: url=" + this.url + ", user=" + this.username);
    }
    
    
    public static void subscribeAll(String viewName) throws IOException, MarshalException, ValidationException {
        if( viewName == null ) {
            throw new IllegalArgumentException("Cannot take null parameters.");
        }
        
        //get the list of categories from the viewsdisplay.xml        
        ViewsDisplayFactory.init();
        ViewsDisplayFactory factory = ViewsDisplayFactory.getInstance();         
        View view = factory.getView(viewName);
        
        if( view != null ) {
            Section[] sections = view.getSection();            

            //create a JMS connection to subscribe
            RTCPostSubscriber subscriber = new RTCPostSubscriber();
            
            for( int i=0; i < sections.length; i++ ) { 
                Section section = sections[i];
                String[] categories = section.getCategory(); 
        
                for( int j=0; j < categories.length; j++ ) {             
                    try {
                        subscriber.subscribe(categories[j]);
                        log.info( "Sent subscription event to RTC for category: " + categories[j]);
                    }
                    catch( Exception e ) {
                        log.error( "Could not send POST subscription event to RTC for category: " + categories[j], e );
                    }
                }
            }        
                        
            //close the subscription JMS connection
            subscriber.close();
        }
    }
        
}


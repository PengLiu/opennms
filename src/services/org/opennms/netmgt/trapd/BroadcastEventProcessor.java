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
// Modifications:
//
// 2003 Jan 31: Cleaned up some unused imports.
//
// Original code base Copyright (C) 1999-2001 Oculan Corp.  All rights reserved.
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
// Tab Size = 8
//

package org.opennms.netmgt.trapd;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Category;
import org.opennms.core.utils.ThreadCategory;
import org.opennms.netmgt.EventConstants;
import org.opennms.netmgt.eventd.EventIpcManagerFactory;
import org.opennms.netmgt.eventd.EventListener;
import org.opennms.netmgt.xml.event.Event;

/**
 *
 * @author <a href="mailto:tarus@opennms.org">Tarus Balog</a>
 * @author <a href="http://www.opennms.org/">OpenNMS</a>
 */
final class BroadcastEventProcessor
	implements EventListener
{
	/**
	 * Create message selector to set to the subscription
	 */
	BroadcastEventProcessor()
	{
		// Create the selector for the ueis this service is interested in
		//
		List ueiList = new ArrayList();

		// nodeGainedInterface
		ueiList.add(EventConstants.NODE_GAINED_INTERFACE_EVENT_UEI);

		// interfaceDeleted
		ueiList.add(EventConstants.INTERFACE_DELETED_EVENT_UEI);
		
		EventIpcManagerFactory.init();
		EventIpcManagerFactory.getInstance().getManager().addEventListener(this, ueiList);
	}

	/**
	 * Unsubscribe from eventd
	 */
	public void close()
	{
		EventIpcManagerFactory.getInstance().getManager().removeEventListener(this);
	}

	/**
	 * This method is invoked by the EventIpcManager
	 * when a new event is available for processing.
	 * Each message is examined for its Universal Event Identifier
	 * and the appropriate action is taking based on each UEI.
	 *
	 * @param event	The event 
	 *
	 */
	public void onEvent(Event event)
	{
		Category log = ThreadCategory.getInstance(getClass());

		String eventUei = event.getUei();
		if (eventUei == null)
			return;
			
		if (log.isDebugEnabled())
			log.debug("Received event: " + eventUei);

		if(eventUei.equals(EventConstants.NODE_GAINED_INTERFACE_EVENT_UEI))
		{
			// add to known nodes
			if (Long.toString(event.getNodeid()) != null && event.getInterface() != null)
			{
				TrapdIPMgr.setNodeId(event.getInterface().toString(), Long.toString(event.getNodeid()));
			}
			if (log.isDebugEnabled())
				log.debug("Added "  + event.getInterface() + " to known node list");
		}
		else if (eventUei.equals(EventConstants.INTERFACE_DELETED_EVENT_UEI))
		{
			// remove from known nodes
                        if (event.getInterface() != null)
                        {
                                TrapdIPMgr.removeNodeId(event.getInterface().toString());
                        }
			if (log.isDebugEnabled())
				log.debug("Removed "  + event.getInterface() + " from known node list");
		}
		else if(eventUei.equals(EventConstants.INTERFACE_REPARENTED_EVENT_UEI))
		{
			// add to known nodes
			if (Long.toString(event.getNodeid()) != null && event.getInterface() != null)
			{
				TrapdIPMgr.setNodeId(event.getInterface().toString(), Long.toString(event.getNodeid()));
			}
			if (log.isDebugEnabled())
				log.debug("Reparented "  + event.getInterface() + " to known node list");
		}
	}
	
	/**
	 * Return an id for this event listener
	 */
	public String getName()
	{
		return "Trapd:BroadcastEventProcessor";
	}
}

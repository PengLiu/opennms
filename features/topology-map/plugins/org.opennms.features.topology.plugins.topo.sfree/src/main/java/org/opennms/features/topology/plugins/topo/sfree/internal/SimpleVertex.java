/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2012 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2012 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.features.topology.plugins.topo.sfree.internal;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleVertex  {
	String m_id;
	int m_x;
	int m_y;
	boolean m_selected;
	boolean m_locked = false;
	String m_icon;
	String m_label = "none provided";
	SimpleGroup m_parent = null;
	List<SimpleEdge> m_edges = new ArrayList<SimpleEdge>();
    private String m_ipAddr ="127.0.0.1";
    private int m_nodeID = -1;
    private String m_iconKey;
    private String m_tooltipText;
	
	public SimpleVertex() {}
	
	public SimpleVertex(String id) {
		m_id = id;
	}

	public SimpleVertex(String id, int x, int y) {
		m_id = id;
		m_x = x;
		m_y = y;
	}
	
	public SimpleGroup getParent() {
		return m_parent;
	}
	
	public void setParent(SimpleGroup parent) {
		if (m_parent != null) {
			m_parent.removeMember(this);
		}
		m_parent = parent;
		if (m_parent != null) {
			m_parent.addMember(this);
		}
	}
	
	public boolean isLocked() {
		return m_locked;
	}

	public void setLocked(boolean locked) {
		m_locked = locked;
	}

	public abstract boolean isLeaf();
	
	public boolean isRoot() {
		return m_parent == null;
	}

	public String getId() {
		return m_id;
	}

	public void setId(String id) {
		m_id = id;
	}

	public int getX() {
		return m_x;
	}

	public void setX(int x) {
		m_x = x;
	}

	public int getY() {
		return m_y;
	}

	public void setY(int y) {
		m_y = y;
	}

	public boolean isSelected() {
		return m_selected;
	}

	public void setSelected(boolean selected) {
		m_selected = selected;
	}

	public String getIcon() {
		return m_icon;
	}

	public void setIcon(String icon) {
		m_icon = icon;
	}

	public String getLabel() {
		return m_label;
	}

	public void setLabel(String label) {
		m_label = label;
	}
	
	public String getIpAddr() {
	    return m_ipAddr;
	}
	
	public void setIpAddr(String ipAddr){
	    m_ipAddr = ipAddr;
	}
	
	public int getNodeID() {
		return m_nodeID;
	}
	
	public void setNodeID(int nodeID) {
		m_nodeID = nodeID;
	}

    public List<SimpleEdge> getEdges() {
		return m_edges;
	}
	
	void addEdge(SimpleEdge edge) {
		m_edges.add(edge);
	}
	
	void removeEdge(SimpleEdge edge) {
		m_edges.remove(edge);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_id == null) ? 0 : m_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleVertex other = (SimpleVertex) obj;
		if (m_id == null) {
			if (other.m_id != null)
				return false;
		} else if (!m_id.equals(other.m_id))
			return false;
		return true;
	}

    public void setIconKey(String iconKey) {
        m_iconKey = iconKey;
    }
    
    public String getIconKey() {
        return m_iconKey;
    }
    
    public void setTooltiptext(String tooltipText) {
        m_tooltipText = tooltipText;
    }
    
    public String getTooltipText() {
        return m_tooltipText;
    }

	
//	public int getSemanticZoomLevel() {
//		return m_semanticZoomLevel >= 0
//				? m_semanticZoomLevel
//				: m_parent == null 
//				? 0 
//				: m_parent.getSemanticZoomLevel() + 1;
//	}
	
//	public SimpleVertex getDisplayVertex(int semanticZoomLevel) {
//		if(getParent() == null || getSemanticZoomLevel() <= semanticZoomLevel) {
//			return this;
//		}else {
//			return getParent().getDisplayVertex(semanticZoomLevel);
//		}
//
//	}
	
	
}
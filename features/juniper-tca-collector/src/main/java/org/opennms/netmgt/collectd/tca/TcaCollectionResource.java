/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2008-2012 The OpenNMS Group, Inc.
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

package org.opennms.netmgt.collectd.tca;

import java.io.File;

import org.opennms.core.utils.DefaultTimeKeeper;
import org.opennms.core.utils.TimeKeeper;
import org.opennms.netmgt.collectd.AbstractCollectionResource;
import org.opennms.netmgt.collectd.CollectionAgent;
import org.opennms.netmgt.config.StorageStrategy;
import org.opennms.netmgt.config.collector.CollectionAttributeType;
import org.opennms.netmgt.config.collector.ServiceParameters;
import org.opennms.netmgt.dao.support.IndexStorageStrategy;
import org.opennms.netmgt.model.RrdRepository;

/**
 * The Class TcaCollectionResource.
 * 
 * @author Alejandro Galue <agalue@opennms.org>
 */
public class TcaCollectionResource extends AbstractCollectionResource {

	/** The Constant RESOURCE_TYPE_NAME. */
	public static final String RESOURCE_TYPE_NAME = "juniperTcaEntry";

	/** The Time Keeper. */
	private TimeKeeper m_timeKeeper = new DefaultTimeKeeper();

	/** The m_peer address. */
	private String m_peerAddress;

	/** The m_strategy. */
	private StorageStrategy m_strategy = new IndexStorageStrategy();

	/**
	 * Instantiates a new TCA collection resource.
	 *
	 * @param agent the collection agent
	 * @param peerAddress the TCA peer address
	 */
	protected TcaCollectionResource(CollectionAgent agent, String peerAddress) {
		super(agent);
		m_peerAddress = peerAddress;
	}

	/* (non-Javadoc)
	 * @see org.opennms.netmgt.config.collector.CollectionResource#getResourceTypeName()
	 */
	@Override
	public String getResourceTypeName() {
		return RESOURCE_TYPE_NAME;
	}

	/* (non-Javadoc)
	 * @see org.opennms.netmgt.config.collector.CollectionResource#getParent()
	 */
	@Override
	public String getParent() {
		return Integer.toString(m_agent.getNodeId());
	}

	/* (non-Javadoc)
	 * @see org.opennms.netmgt.config.collector.CollectionResource#getInstance()
	 */
	@Override
	public String getInstance() {
		return m_peerAddress;
	}

	/* (non-Javadoc)
	 * @see org.opennms.netmgt.collectd.AbstractCollectionResource#getLabel()
	 */
	@Override
	public String getLabel() {
		return m_peerAddress;
	}

	/* (non-Javadoc)
	 * @see org.opennms.netmgt.collectd.AbstractCollectionResource#getType()
	 */
	@Override
	public int getType() {
		return -1; // Is this right?
	}

	/* (non-Javadoc)
	 * @see org.opennms.netmgt.collectd.AbstractCollectionResource#rescanNeeded()
	 */
	@Override
	public boolean rescanNeeded() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.opennms.netmgt.collectd.AbstractCollectionResource#shouldPersist(org.opennms.netmgt.config.collector.ServiceParameters)
	 */
	@Override
	public boolean shouldPersist(ServiceParameters params) {
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "node[" + m_agent.getNodeId() + "]." + getResourceTypeName() + "[" + getLabel() +"]";
	}

	/* (non-Javadoc)
	 * @see org.opennms.netmgt.collectd.AbstractCollectionResource#getResourceDir(org.opennms.netmgt.model.RrdRepository)
	 */
	@Override
	public File getResourceDir(RrdRepository repository) {
		String resourcePath = m_strategy.getRelativePathForAttribute(getParent(), getLabel(), null);
		return new File(repository.getRrdBaseDir(), resourcePath);
	}

	/* (non-Javadoc)
	 * @see org.opennms.netmgt.collectd.AbstractCollectionResource#getTimeKeeper()
	 */
	@Override
	public TimeKeeper getTimeKeeper() {
		return m_timeKeeper;
	}

	/**
	 * Sets the time keeper.
	 *
	 * @param timeKeeper the new time keeper
	 */
	public void setTimeKeeper(TimeKeeper timeKeeper) {
		m_timeKeeper = timeKeeper;
	}
	
    /**
     * Sets the attribute value.
     *
     * @param type the type
     * @param value the value
     */
    public void setAttributeValue(CollectionAttributeType type, String value) {
        TcaCollectionAttribute attr = new TcaCollectionAttribute(this, type, type.getName(), value);
        addAttribute(attr);
    }

}

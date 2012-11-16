package org.opennms.features.topology.app.internal;

import org.opennms.features.topology.api.GraphContainer;
import org.opennms.features.topology.api.Layout;
import org.opennms.features.topology.api.topo.VertexRef;

public class DefaultLayout implements Layout {

	private GraphContainer m_graphContainer;

	public DefaultLayout(GraphContainer graphContainer) {
		m_graphContainer = graphContainer;
	}

	@Override
	public int getVertexX(VertexRef v) {
		return m_graphContainer.getVertexX(v);
	}

	@Override
	public int getVertexY(VertexRef v) {
		return m_graphContainer.getVertexY(v);
	}

	@Override
	public void setVertexX(VertexRef v, int x) {
		m_graphContainer.setVertexX(v, x);
	}

	@Override
	public void setVertexY(VertexRef v, int y) {
		m_graphContainer.setVertexY(v, y);
	}

}
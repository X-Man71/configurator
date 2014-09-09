package de.hs.furtwangen.bam.jee.configurator.web.domain;

import java.util.ArrayList;
import java.util.List;

import de.hs.furtwangen.bam.jee.configurator.model.OrderPosition;

public class OrderPositionModel {
		public OrderPositionModel(){
			listOrderPositions = new ArrayList<>();
		}
		
		private List<OrderPosition> listOrderPositions;

		public List<OrderPosition> getListOrderPositions() {
			return listOrderPositions;
		}

		public void setListOrderPositions(List<OrderPosition> listOrderPositions) {
			this.listOrderPositions = listOrderPositions;
		}

}

package business;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import data_access.PerformanceDAO;
import model.Performance;

public class PerformanceBL {

	private PerformanceDAO performanceDAO;
	
	public PerformanceBL() {
		performanceDAO = new PerformanceDAO();
	}
	
	public void addPerformance(Performance performance) {
		performanceDAO.insert(performance);
		
	}
	
	public void editPerformance(Performance performance, Performance newPerformance) {
		if(performanceDAO.findByTitle(performance.getTitle()) != null) {
			performanceDAO.updatePerformance(performance, newPerformance.getTitle(), newPerformance.getGenre(), newPerformance.getDate(), newPerformance.getNrTickets());
		}
	}
	
	public void deletePerformance(Performance performance) {
		if(performanceDAO.findByTitle(performance.getTitle()) != null) {
			performanceDAO.deletePerformance(performance.getTitle());
		}
	}
	
	public void deleteAll() {
		performanceDAO.deleteAll();
	}
}

package generics.collections;

import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import generics.collections.PriorityTask.Priority;

class CollectionTaskExample {

	static PhoneTask mikePhone = new PhoneTask("Mike", "987 6543");
	static PhoneTask paulPhone = new PhoneTask("Paul", "123 4567");
	
	static CodingTask dbCode = new CodingTask("db");
	static CodingTask guiCode = new CodingTask("gui");
	static CodingTask logicCode = new CodingTask("logic");
	
	static Collection<PhoneTask> phoneTasks = new ArrayList<>();
	static Collection<CodingTask> codingTasks = new ArrayList<>();
	static Collection<Task> mondayTasks = new ArrayList<>();
	static Collection<Task> tuesdayTasks = new ArrayList<>();
	
	@Test
	void testCollection() {
		Collections.addAll(phoneTasks, paulPhone, mikePhone);
		assertTrue(phoneTasks.toString().equals("[phone Paul, phone Mike]"));
	
		Collections.addAll(codingTasks, dbCode, guiCode, logicCode);
		assertTrue(codingTasks.toString().equals("[code db, code gui, code logic]"));
	
		Collections.addAll(mondayTasks, logicCode, mikePhone);
		assertTrue(mondayTasks.toString().equals("[code logic, phone Mike]"));
	
		Collections.addAll(tuesdayTasks, dbCode, guiCode, paulPhone);
		assertTrue(tuesdayTasks.toString().equals("[code db, code gui, phone Paul]"));
	
		//add all
		Collection<Task> allTasks = new ArrayList<>(mondayTasks);
		allTasks.addAll(tuesdayTasks);
		assertTrue(allTasks.toString().equals("[code logic, phone Mike, code db, code gui, phone Paul]"));
	
		//remove
		assertTrue(mondayTasks.remove(mikePhone));
	
		//clear
		mondayTasks.clear();
		assertTrue(mondayTasks.toString().equals("[]"));
		//add back
		Collections.addAll(mondayTasks, logicCode, mikePhone);
		
		//remove all
		Collection<Task> tuesdayNonPhoneTasks = new ArrayList<>(tuesdayTasks);
		tuesdayNonPhoneTasks.removeAll(phoneTasks);
		assertTrue(tuesdayNonPhoneTasks.toString().equals("[code db, code gui]"));
	
		//retain all
		//We want the least restrictive type possible, which is Collection<?>.
		//we might have used PhoneTask instead of Task, 
		//but gives no justification for restricting what the argument may contain.
		Collection<Task> tuesdayPhoneTasks = new ArrayList<>(phoneTasks);
		tuesdayPhoneTasks.retainAll(tuesdayTasks);
		assertTrue(tuesdayPhoneTasks.toString().equals("[phone Paul]"));
		
		//if iterating to remove elements, must use iterators remove method, else throw concur. modif. error.
		Collection<Task> merged = Merger.merge(mondayTasks, tuesdayTasks);
		assertTrue(merged.toString().equals("[code db, code gui, code logic, phone Mike, phone Paul]"));
	}
	
	@Test
	void testSet() {
		NavigableSet<PriorityTask> priorityTasks = new TreeSet<>();
		priorityTasks.add(new PriorityTask(mikePhone, Priority.MEDIUM));
		priorityTasks.add(new PriorityTask(paulPhone, Priority.HIGH));
		priorityTasks.add(new PriorityTask(dbCode, Priority.MEDIUM));
		priorityTasks.add(new PriorityTask(guiCode, Priority.LOW));
		
		assertTrue(priorityTasks.toString()
				.equals("[phone Paul: HIGH, code db: MEDIUM, phone Mike: MEDIUM, code gui: LOW]"));
				
		//Used to set exclusion/inclusion points for sub-sets/range views.
		PriorityTask firstLowPTask = new PriorityTask(new EmptyTask(), Priority.LOW);
		PriorityTask firstMediumPTask = new PriorityTask(new EmptyTask(), Priority.MEDIUM);
		
		//start at the head, and going down to the element firstLowPTask, excluding low priorities.
		SortedSet<PriorityTask> highAndMediumPTasks = priorityTasks.headSet(firstLowPTask);
		assertTrue(highAndMediumPTasks.toString()
				.equals("[phone Paul: HIGH, code db: MEDIUM, phone Mike: MEDIUM]"));
		 
		//Sub-set is half interval, including medium, but excluding low.
		SortedSet<PriorityTask> mediumPTasks = priorityTasks.subSet(firstMediumPTask, firstLowPTask);
		assertTrue(mediumPTasks.toString().equals("[code db: MEDIUM, phone Mike: MEDIUM]"));
		
		//Changes to the original affect the range view and visa versa. 
		PriorityTask logicMedium = new PriorityTask(logicCode, Priority.MEDIUM);
		priorityTasks.add(logicMedium);
		assertTrue(mediumPTasks.toString()
				.equals("[code db: MEDIUM, code logic: MEDIUM, phone Mike: MEDIUM]"));
		 
		mediumPTasks.remove(logicMedium);
		assertTrue(priorityTasks.toString()
				.equals("[phone Paul: HIGH, code db: MEDIUM, phone Mike: MEDIUM, code gui: LOW]"));
		
	}
	
	@Test
	void testArrayQueue() {
		Queue<Task> taskQueue = new ArrayDeque<>();
		taskQueue.offer(mikePhone);
		taskQueue.offer(paulPhone);
		
		Task nextTask;
		/*
		 * Two alternate ways
		 * The choice between depends on queue emptiness condition.
		 */
		nextTask = taskQueue.poll();
		if(nextTask != null) { /*proccess next task...*/}
		
		try {
			nextTask = taskQueue.remove();
			//process next task...
		}catch (NoSuchElementException e) {
			//but we never run out.
		}
		
		//Another way...
		nextTask = taskQueue.peek();
		if(nextTask instanceof PhoneTask) {
			taskQueue.remove();
			//process next task...
		}
		
	}
	
	@Test
	void testPriorityQueue() {
		
	}
}

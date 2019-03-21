package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.accenture.adf.businesstier.dao.VisitorDAO;
import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

/**
 * JUnit test case for VisitorDAO class for testing all repository methods to
 * call database sub-routines
 * 
 */
public class TestVisitorDAO {
	
	private Visitor visitor;
	private VisitorDAO visitorDAO;
	private ArrayList<Event> registeredEvents;

	/**
	 * Setting up initial objects 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		visitor = new Visitor();
		visitorDAO = new VisitorDAO();
		registeredEvents = new ArrayList<Event>();
	}

	/**
	 * Deallocating objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		visitor = null;
		visitorDAO = null;
		registeredEvents = null;
	}

	/**
	 * Test case for method insertData
	 */
	@Test
	public void testInsertData() {
		visitor.setUserName("TestVisitor1");
		visitor.setFirstName("TestVFname");
		visitor.setLastName("TestVLname");
		visitor.setPassword("ttt");
		visitor.setPhoneNumber("2344");
		visitor.setAddress("TestPlace");
		visitor.setEmail("abc@gmail.com");
		try {
			visitorDAO.insertData(visitor);
			
			visitor = visitorDAO.searchUser("TestVisitor1", "ttt");
		} catch (SQLException exception) {			
			fail("SQL Exception");
		} catch (ClassNotFoundException exception) {			
			fail("Class Not Found Exception");
		} catch (Exception exception) {			
			fail("NULL Exception");
		}
		
		assertEquals(true, visitor.getUserName().equals("TestVisitor1"));
	}	

	/**
	 * Test case for method searchUser
	 */
	@Test
	public void testSearchUser() {
		String userName = "TestVisitor1";
		String password = "ttt";
		try {
			visitor = visitorDAO.searchUser("TestVisitor1", "ttt");
		} catch (SQLException exception) {
			fail("SQL Exception");
		} catch (ClassNotFoundException exception) {
			fail("Class Not Found Exception");
		} catch (Exception exception) {
			fail("NULL Exception");
		}
		assertEquals(true, visitor.getUserName().equals("TestVisitor1")); 
	}

	/**
	 * Test case for method registerVisitorToEvent
	 */
	@Test
	public void testRegisterVisitorToEvent() {
		try {
			visitor = visitorDAO.searchUser("TestVisitor1", "ttt");
			visitorDAO.registerVisitorToEvent(visitor, 1001);
			registeredEvents=visitor.getRegisteredEvents();
			
		} catch (SQLException exception) {			
			fail("SQL Exception");
		} catch (ClassNotFoundException exception) {
			fail("Class Not Found Exception");
		} catch (Exception exception) {
			fail("NULL Exception");
		}
	}	

	/**
	 * Test case for method registeredEvents
	 */
	@Test
	public void testRegisteredEvents() {
		try {
			visitor = visitorDAO.searchUser("TestVisitor1", "ttt");
			registeredEvents = visitorDAO.registeredEvents(visitor);
		} catch (SQLException exception) {
			fail("SQL Exception");
		} catch (ClassNotFoundException exception) {
			fail("Class Not Found Exception");
		}
		for (Event event : registeredEvents) {
			assertEquals(1001, event.getEventid());
			break;
		}
	}

	/**
	 * Test case for method updateVisitor
	 */
	@Test
	public void testUpdateVisitor() {
		int updateStatus = 0;
		try {
			visitor = visitorDAO.searchUser("TestVisitor1", "ttt");
			visitor.setAddress("pune");
			updateStatus = visitorDAO.updateVisitor(visitor);
			
			
		} catch (SQLException exception) {
			fail("SQL Exception");
		} catch (ClassNotFoundException exception) {			
			fail("Class Not Found Exception");
		}
		assertTrue(updateStatus==1);
		
	}

	/**
	 * Test case for method unregisteredEvents
	 */
	@Test
	public void testUnregisterEvent()
{
		try
		{
			visitor = visitorDAO.searchUser("halwa1", "rofl");
			visitorDAO.unregisterEvent(visitor, 1001);
			registeredEvents = visitorDAO.registeredEvents(visitor);
			
				assertEquals(false,registeredEvents.equals(1001));
			
		}
		catch (SQLException exception)
		{
			fail("SQL Exception");
		}
		catch (ClassNotFoundException exception)
		{			
			fail("Class Not Found Exception");
		}
		catch (Exception exception)
		{
			fail("NULL Exception");
		}
	}

}

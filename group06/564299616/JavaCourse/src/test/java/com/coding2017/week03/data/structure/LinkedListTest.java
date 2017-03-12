package com.coding2017.week03.data.structure;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {
	
	private LinkedList list = new LinkedList();

	@Test
	public void testAddObject() {
		list.add(1);
		Assert.assertEquals(1, list.size());
		list.add(2);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void testAddIntObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		list.add(1);
		Assert.assertEquals(1, list.get(0));
		list.add(2);
		Assert.assertEquals(2, list.get(1));
	}

	@Test
	public void testRemoveInt() {
		list.add("liss");
		list.add("wt");
		list.add("a");
		Assert.assertEquals("a", list.remove(2));
		Assert.assertEquals("liss", list.remove(0));
		Assert.assertEquals(0, list.size());
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFirst() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddLast() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFirst() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLast() {
		fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testReverse() {
		list.reverse();
		Assert.assertEquals("[]", list.toString());
		list.add("a");
		list.add("b");
		list.reverse();
		Assert.assertEquals("[b,a]", list.toString());
		
	}

	@Test
	public void testRemoveFirstHalf() {
		list.add(1);
		list.add(2);
		list.add("a");
		list.add("b");
		list.add("c");
		list.removeFirstHalf();
		Assert.assertEquals("[a,b,c]", list.toString());
	}

	@Test
	public void testRemoveIntInt() {
		list.remove(0, 0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.remove(1, 2);
		Assert.assertEquals("[1]", list.toString());
	}

	@Test
	public void testGetElements() {
		LinkedList l = new LinkedList();
		l.add(1);
		l.add(2);
		
		list.add(100);
		list.add(101);
		list.add(102);
		list.add(103);
		Assert.assertArrayEquals(new int[]{101,102}, list.getElements(l));
		l.add(5);
		Assert.assertArrayEquals(new int[0], list.getElements(l));
	}

	@Test
	public void testSubtract() {
		LinkedList l = new LinkedList();
		l.add(1);
		l.add(2);
		
		list.subtract(l);
		Assert.assertEquals("[]", list.toString());
		
		list.add(1);
		list.add(1);
		list.add(3);
		list.add(2);
		list.add(5);
		list.subtract(l);
		Assert.assertEquals("[3,5]", list.toString());
	}

	@Test
	public void testRemoveDuplicateValues() {
		list.removeDuplicateValues();
		list.add("1");
		list.add("2");
		list.add("2");
		list.add("3");
		list.add("3");
		list.add("3");
		list.removeDuplicateValues();
		Assert.assertEquals("[1,2,3]", list.toString());
	}

	@Test
	public void testRemoveRange() {
		list.add(1);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(9);
		list.removeRange(4, 6);
		Assert.assertEquals("[1,3,7,9]", list.toString());
		list.removeRange(0, 7);
		Assert.assertEquals("[7,9]", list.toString());
		list.removeRange(0, 100);
		Assert.assertEquals("[]", list.toString());
		
	}

	@Test
	public void testIntersection() {
		LinkedList l = new LinkedList();
		l.add(1);
		l.add(2);
		l.add(5);
		l.add(7);
		
		list.add(2);
		list.add(7);
		list.add(8);
		Assert.assertEquals("[2,7]", list.intersection(l).toString());
	}

}

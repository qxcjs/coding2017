package com.coding2017.week03.data.structure;

import java.util.Arrays;
import java.util.Objects;

public class LinkedList implements List {
	
	private Node head;
	
	private int size = 0;
	
	public void add(Object o){
		if(this.head==null){
			head=new Node(o);
		}else{
			Node current = new Node(o);
			Node prev = head;
			for(int i=1;i<this.size();i++){
				prev = prev.next;
			}
			prev.next=current;
		}
		size++;
	}
	public void add(int index , Object o){
		if(index>this.size)
			throw new IndexOutOfBoundsException();
		
		size++;
	}
	public Object get(int index){
		if(index>this.size)
			throw new IndexOutOfBoundsException();
		int count = 0;
		Node current = head;
		while(count<index){
			count++;
			current = current.next;
		}
		return current.data;
	}
	
	public Object remove(int index){
		if(index>this.size)
			throw new IndexOutOfBoundsException();
		Object o = null;
		if(index == 0){
			o=head.data;
			head = this.head.next;
		}else{
			Node current = head;
			Node prev = null;
			for(int i=0;i<index;i++){
				prev = current;
				current = current.next;
			}
			prev.next=current.next;
			o = current.data;
		}
		size--;
		return o;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		
	}
	public void addLast(Object o){
		
	}
	public Object removeFirst(){
		return null;
	}
	public Object removeLast(){
		return null;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
		public Node(Object data) {
			super();
			this.data = data;
		}
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		Node current = this.head;
		Object[] objs = new Object[this.size];
		for(int i=0;i<this.size;i++){
			objs[i] = current.data;
			current = current.next;
		}
		this.head = null;
		this.size=0;
		for(int i=objs.length-1;i>=0;i--){
			add(objs[i]);
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		for(int i=this.size/2-1;i>=0;i--){
			this.remove(i);
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(i<0 || i>this.size || (i+length)>this.size)
			throw new IndexOutOfBoundsException();
		for(int j=0;j<length;j++){
			this.remove(i);
		}
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(LinkedList list){
		if(list == null || list.size==0 || (int)list.get(list.size-1)>=this.size-1){
			return new int[0];
		}
		int[] result = new int[list.size];
		for(int i=0;i<list.size;i++){
			int index = (int)list.get(i);
			if(index>=this.size){
				break;
			}
			result[i]=(int)this.get(index);
		}
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		if(list==null || list.size==0 || this.size==0){
			return;
		}
		Node current = this.head;
		int [] array = new int[this.size];
		int count=0;
		for(int i=0;i<this.size;i++){
			if(current!=null){
				Node node = list.head;
				do{
					if(Objects.equals(current.data, node.data)){
						array[count++]=i;
					}
				}while((node=node.next)!=null);
				current = current.next;
			}
		}
		
		array= Arrays.copyOf(array, count);
		for(int i=array.length-1;i>=0;i--){
			this.remove(array[i]);
		}
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		if(this.size==0){
			return;
		}
		Node current = this.head;
		Node next = null;
		int[] array = new int[this.size];
		int count = 0;
		for(int i=1;i<this.size;i++){
			if(current.next!=null){
				next = current.next;
				if(Objects.equals(current.data, next.data)){
					array[count++]=i;
				}
				current = current.next;
			}
		}
		array= Arrays.copyOf(array, count);
		for(int i=array.length-1;i>=0;i--){
			this.remove(array[i]);
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		if(min >= max || this.size==0){
			return;
		}
		Node current = this.head;
		int[] array = new int[this.size];
		int count = 0;
		for(int i=0;i<this.size;i++){
			if((int)current.data>min && (int)current.data<max){
				array[count++]=i;
			}
			current = current.next;
		}
		array= Arrays.copyOf(array, count);
		for(int i=array.length-1;i>=0;i--){
			this.remove(array[i]);
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		if(list==null || list.size==0){
			return this;
		}
		if(this.size==0){
			return list;
		}
		LinkedList newList = new LinkedList();
		Node current1 = this.head;
		Node current2 = list.head;
		while(current1!=null && current2!=null){
			if((int)current1.data<(int)current2.data){
				current1 = current1.next;
			}else if((int)current1.data>(int)current2.data){
				current2 = current2.next;
				continue;
			}else{
				newList.add(current1.data);
				current1 = current1.next;
				current2 = current2.next;
			}
		}
		return newList;
	}
	
	@Override
	public String toString() {
		if(this.head==null){
			return "[]";
		}
		Node current = this.head;
		StringBuilder sb = new StringBuilder("[");
		while(current!=null){
			sb.append(current.data.toString());
			current = current.next;
			if(current!=null){
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	
}

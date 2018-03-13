package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.NoSuchElementException;

import linkedLists.LinkedList;
import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		if (length==0) {
			first=last=(SNode<E>) nuevo;}
		else {
			((SNode<E>) nuevo).setNext(first);
			first=((SNode<E>) nuevo);
		}
		length++;
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		if (target==last) {
			last=((SNode<E>) nuevo);
			((SNode<E>) nuevo).setNext(null);
			((SNode<E>) target).setNext(((SNode<E>) nuevo));
			
		}
		else {
			((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext());
			((SNode<E>) target).setNext(((SNode<E>) nuevo));
		}
		length++;		
		
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		if (target==first) {
			this.addFirstNode(nuevo);
			
		}
		else {
			Node<E> prevNode=getNodeBefore(target);
			this.addNodeAfter(prevNode, nuevo);
		}
		length++;
		
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (this.length()==0) throw new NoSuchElementException("List is empty");
		return first;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (this.length()==0) throw new NoSuchElementException("List is empty");
		return last;
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		if(target==last) throw new  NoSuchElementException("target is the last node"); 
		SNode<E> node=((SNode<E>) target).getNext();

		
		return node;
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		SNode<E> before=(SNode<E>) this.getFirstNode();
		if(target==first) throw new NoSuchElementException (" Such an element does not exist");
		else {
			while(before.getNext()!=target) {
				if(before==last) throw new NoSuchElementException (" Such an element does not exist");
				before=before.getNext();
				
			}
		}
		return before;
	}

	public int length() {
		
		return this.length;
	}

	public void removeNode(Node<E> target) {
		if (target == first) { 
			first = first.getNext(); 
			length--;
		}
		else if(target==last) {
		
			last = (SNode<E>) this.getNodeBefore(target);
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			prevNode.getNext().setNext(null); 
			length--;
		}	
		
		else
		{ 
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			
			prevNode.setNext(((SNode<E>) target).getNext());
			length--;
		}
		
		((SNode<E>) target).clean();   // clear all references from target
	
	
		
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}

}

import java.io.*;
import java.util.*;

public class Stack{
	private int pointer_index; //next available index
	private char[] array; //stack array
	private int size; //size of stack

	public Stack(int size){
		array = new char[size];
		pointer_index = 0;
		this.size = size;
	}

	public Stack(char[] chars){
		array = chars;
		pointer_index = chars.length;
		size = chars.length;
	}

	public boolean isempty_Stack(){
		//checks if the stack exists
		if(this == null){
			System.err.println("NON-EXISTENT STACK");
			return false;
		}

		//if pointer index is 0, stack is empty
		if(pointer_index == 0)
			return true;

		return false; //otherwise stack is not empty
	}

	public boolean isfull_Stack(){
		//checks if the stack exists
		if(this == null){
			System.err.println("NON-EXISTENT STACK");
			return false;
		}

		//if pointer index points to size, stack is full
		if(pointer_index == size)
			return true;

		return false; //otehrwise stack is not full
	}
	
	public boolean pop(Stack popped){
		//checks if the stack exists
		if(this == null){
			System.err.println("POPPING FROM NONEXISTENT STACK");
			return false;
		}
		//checks if the stack is not empty
		else if(isempty_Stack()){
			System.err.println("POPPING FROM AN EMPTY STACK");
			return false;
		}

		//stores the last item in the stack and removes it
		popped.push(array[pointer_index - 1]);
		//popped.array[popped.pointer_index] = array[pointer_index-1];
		array[--pointer_index] = 0;

		return true; //successful popping
	}

	public boolean push(char item){
		//checks if the stack exists
		if(this == null){
			System.err.println("PUSHING TO A NONEXISTENT STACK");
			return false;
		}
		//checks if the stack is full
		else if(isfull_Stack()){
			System.err.println("PUSHING TO A FULL STACK");
			return false;
		}

		//stores the item at the last available index
		array[pointer_index++] = item;

		return true;
	}

	public boolean validParentheses(Stack popped){
		Stack garbage = new Stack(1000);
		//if the last character in string is a open parentheses, return false;
		if(array[pointer_index-1] == '[' || array[pointer_index-1] == '(' || 
				array[pointer_index-1] == '{')
			return false;
		else if(isempty_Stack())
			return true;
		else if(pointer_index == 1)
			return false;

		
		while(!isempty_Stack()){
			if(array[pointer_index-1] == '}')
				pop(popped);
			else if(array[pointer_index-1] == ']')
				pop(popped);
			else if(array[pointer_index-1] == ')')
				pop(popped);
			else{
				if(popped.array[popped.pointer_index - 1] == '}'){
					if(array[pointer_index-1] == '{'){
						pop(garbage);
						popped.pop(garbage);
					}
					else
						return false;
				}
				else if(popped.array[popped.pointer_index-1] == ']'){
					if(array[pointer_index-1] == '['){
						pop(garbage);
						popped.pop(garbage);
					}
					else
						return false;
				}
				else if(popped.array[popped.pointer_index - 1] == ')'){
					if(array[pointer_index-1] == '('){
						pop(garbage);
						popped.pop(garbage);
					}
					else
						return false;
				}
			}
		}

		return true;
	}

	public static void main(String[] args){
		Scanner test = new Scanner(System.in);
		/*Stack stack = new Stack(test.toCharArray());
		Stack popped = new Stack(100);
		boolean yo = stack.validParentheses(popped);

		if(yo)
			System.out.println("TEST PASSED");
		else
			System.out.println("TEST FAILED");*/

		Stack stack; 
		Stack popped = new Stack(1000);
		while(true){
			System.out.print("Please enter parentheses for validation: ");
			String validation = test.next();
			stack = new Stack(validation.toCharArray());
			boolean result = stack.validParentheses(popped);
			if(result)
				System.out.println("Valid Parentheses!");
			else
				System.out.println("Invalid Parentheses. Learn how parentheses work. FUCKING CUNTFLAP.");
		}
	}
	
	

} //end of Stack class

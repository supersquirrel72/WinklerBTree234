/*****************************************************
*** Class: Node234
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This class creates the nodes and children for the
***          BTree.
******************************************************
*** Changes:October 6th - Created the class
***         Oct. 6th - Created all methods and classes
***         Oct. 8th - Tweaked the methods according to new additions.  
***         Nov. 7th - Rewrote Node234 to fit with Tree234 and DataItem.
******************************************************/
package BTree234;

/**
 *
 * @author Gabriel
 */
public class Node234 {
    private static final int ORDER = 4;
	private int numItems;
	private Node234 parent;
	private Node234 childArray[] = new Node234[ORDER];
	private DataItem itemArray[] = new DataItem[ORDER-1];
	
	public void connectChild(int childNum, Node234 child)
	{
		childArray[childNum] = child;
		if(child != null)
			child.parent = this;
	}
	
	public Node234 disconnectChild(int childNum)
	{
		Node234 tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}
	
	public Node234 getChild(int childNum)
	{ return childArray[childNum]; }
	
	public Node234 getParent()
	{ return parent; }
	
	public boolean isLeaf()
	{ return (childArray[0]==null) ? true : false; }
	
	public int getNumItems()
	{ return numItems; }
	
	public DataItem getItem(int index)
	{ return itemArray[index]; }
	
	public boolean isFull()
	{ return (numItems==ORDER-1) ? true : false; }
	
	public boolean findItem(String key)
	{
		for(int j=0; j<ORDER-1; j++)
		{
			if(itemArray[j] == null)
				break;
			else if(itemArray[j].dData.compareTo(key) == 0)
                        {
                            itemArray[j].count++;
                            return true;    
                        }
                        
		}
		return false;
	}
	
/*****************************************************
*** Method: insertItem
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method creates a new instance of the DataItem class as 
***         a newItem integer and inserts the result as an array.
******************************************************
*** Changes:Nov. 6th - Created the method
***         Nov. 6th - Fixed the damn compareTo(itsKey) that was causing awful things.
******************************************************/
	public int insertItem(DataItem newItem)
	{
		//assumes node is not full
		numItems++;
		String newKey = newItem.dData;
		
		for(int j=ORDER-2; j>=0; j--)
		{
			if(itemArray[j] == null)
				continue;
			else
			{
				String itsKey = itemArray[j].dData;
				if(newKey.compareTo(itsKey) < 0)
					itemArray[j+1] = itemArray[j];
                                else if (newKey.compareTo(itsKey)>0)
				{
					itemArray[j+1] = newItem;
					return j+1;
				}
                                else
                                {
                                    itemArray[j].count ++;
                                    return j;
                                }
                                
			}//end else (not null)
		}//end for
		itemArray[0] = newItem;
		return 0;
	}
	
/*****************************************************
*** Method: removeItem
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method removes the items from the itemArray array and returns
***           temp.
******************************************************
*** Changes:Nov. 6th - Created the method
******************************************************/
	public DataItem removeItem()
	{
		//assumes node not empty
		DataItem temp = itemArray[numItems-1];
		itemArray[numItems-1] = null;
		numItems--;
		return temp;
	}
	
/*****************************************************
*** Method: displayNode
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method displays the values of the array itemArray.
******************************************************
*** Changes:Nov. 6th - Created the method
******************************************************/
	public String displayNode()
	{
            String value = "";
		for(int j = 0; j<numItems; j++)
			value += itemArray[j].displayItem();
		return value;
	}
/*****************************************************
*** Method: countNode
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method sets the count for the number of words in the text file.
******************************************************
*** Changes:Nov. 6th - Created the method
***         Nov. 6th - Fixed the return.
******************************************************/
        public int countNode()
	{
            int answer = 0;
		for(int j = 0; j<numItems; j++)
			answer += itemArray[j].count;
		return answer;
	}
}

/*****************************************************
*** Class: Tree234
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This class adds nodes to the 234 tree, sorts them, and sets
***         up the methods for displaying the data.
******************************************************
*** Changes:October 6th - Created the class
***         Oct. 6th - Altered old methods to try and fit the new setup.
***         Oct. 8th - Realized the old methods need to be replaced entirely.
***         Nov. 6th - Rewrote the class and methods, making sure they are
***                    compatible with the other classes.
******************************************************/
package BTree234;

/**
 *
 * @author Gabriel
 */
public class Tree234 {
    private Node234 root = new Node234();
    private int total = 0;
	public boolean find(String key)
	{
		Node234 curNode = root;
		
                while(true)
		{
			if( (curNode.findItem(key)) )
				return true;			//found
			else if( curNode.isLeaf() )
				return false;			//not found
			else
				curNode = getNextChild(curNode, key);
		} //end while
	}
	
/*****************************************************
*** Method: findMinimum
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method isn't used, but it is helpful for making sure my
***          tree is sorting properly.
******************************************************
*** Changes:Nov. 6th - Created the method
******************************************************/
	public void findMinimum()
	{
		Node234 curNode = root;
		Node234 answer = new Node234();
		while((curNode = curNode.getChild(0)) != null)
			answer = curNode;
		System.out.println("Minimum value is "+answer.getItem(0).dData);
	}
	
/*****************************************************
*** Method: traverseInOrder
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method works through the leaves of the tree, in order.
******************************************************
*** Changes:Nov. 6th - Created the method
******************************************************/
	public void traverseInOrder()
	{
		recTraverse(root);
	}
        
/*****************************************************
*** Method: recTraverse
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method pulls curNode from Node234 and sets the leaves based on
***          the number of occurrences 
******************************************************
*** Changes:Nov. 6th - Created the method
******************************************************/
	private void recTraverse(Node234 curNode)
	{
		//if it's a leaf, display it.
		if(curNode.isLeaf())
		{
			for(int j = 0; j<curNode.getNumItems(); j++)
				curNode.getItem(j).displayItem();
			return;
		}
		//otherwise get child 0, print item 0, get child 1, print item 1...
		else
		{
			for(int j = 0; j < curNode.getNumItems()+1; j++)
			{
				recTraverse(curNode.getChild(j));
				if(j < curNode.getNumItems())
					curNode.getItem(j).displayItem();
			}
		}
	}
        
/*****************************************************
*** Method: sortTraverse
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method increases the index of the array i times after each
***          new node.
******************************************************
*** Changes:Nov. 6th - Created the method
******************************************************/
    public void sortTraverse(String[] theArray)
    {
	int i = 0;
	recSortTraverse(root, theArray, i);
    }
		
    private int recSortTraverse(Node234 curNode, String[] theArray, int i)
    {
//if it's a leaf, display it.
    if(curNode.isLeaf())
    {
	for(int j = 0; j<curNode.getNumItems(); j++)
    	{
            theArray[i] = curNode.getItem(j).dData;
            i++;
	}
	return i;
    }
//otherwise get child 0, print item 0, get child 1, print item 1...
    else
    {
    for(int j = 0; j < curNode.getNumItems()+1; j++)
    	{
	i = recSortTraverse(curNode.getChild(j), theArray, i);
	if(j < curNode.getNumItems())
            {
            theArray[i] = curNode.getItem(j).dData;
            i++;
            }
	}
	return i;
    }
    }
/*****************************************************
*** Method: insert
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method inserts a string variable in its respective
***          leaf, splitting it if the node is full.
******************************************************
*** Changes:Nov. 6th - Created the method
***         Nov. 6th - Fixed the first if statement.
******************************************************/	
	public void insert(String dValue)
	{
            if(!find(dValue))
            {
		Node234 curNode = root;
		DataItem tempItem = new DataItem(dValue);
		
		while(true)
		{
			if (curNode.isFull() )
			{
				split(curNode);
				curNode = curNode.getParent();

				curNode = getNextChild(curNode, dValue);
			}
			
			else if (curNode.isLeaf() )
				break;
			else
			curNode = getNextChild(curNode, dValue);
		}
		
		curNode.insertItem(tempItem);
            }
	}
/*****************************************************
*** Method: split
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method handles the splitting of the nodes based on how many
***          
******************************************************
*** Changes:Nov. 6th - Created the method
******************************************************/	
    public void split(Node234 thisNode)
    {
        DataItem itemB, itemC;
	Node234 parent, child2, child3;
	int itemIndex;
	
	itemC = thisNode.removeItem(); //remove rightmost item
	itemB = thisNode.removeItem(); //remove next item
	child2 = thisNode.disconnectChild(2); //remove children
	child3 = thisNode.disconnectChild(3);
		
	Node234 newRight = new Node234();
	if(thisNode==root)
	{
            root = new Node234();
            parent = root;
            root.connectChild(0, thisNode);
	}
        else
	parent = thisNode.getParent();
		
        itemIndex = parent.insertItem(itemB); //insert old middle item to parent
	int n = parent.getNumItems();
		
	for(int j = n-1; j>itemIndex; j--)// moving the pointers for the parent
        {
            Node234 temp = parent.disconnectChild(j);
            parent.connectChild(j+1, temp);
	}
		
		//connect newRight to parent
        parent.connectChild(itemIndex+1, newRight);
            newRight.insertItem(itemC);
            newRight.connectChild(0, child2);
            newRight.connectChild(1, child3);
	}
/*****************************************************
*** Method: getNextChild
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method adds children to the tree until there are no more
***          items to add from the text file.
******************************************************
*** Changes:Nov. 6th - Created the method
******************************************************/
    public Node234 getNextChild(Node234 theNode, String theValue)
    {
	int j;
	//assumes node is not empty, not full, not a leaf
	int numItems = theNode.getNumItems();
	for(j=0; j<numItems; j++)		//for each item in node
	{
		if( theValue.compareTo(theNode.getItem(j).dData) < 0)
			return theNode.getChild(j); //return left child
	}
	return theNode.getChild(j);		//return right child
    }
/*****************************************************
*** Method: displayTree
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method takes the results from recDisplayTree and puts them in
***          one String to be used by the GUI.
******************************************************
*** Changes:Nov. 6th - Created the method
******************************************************/	
    public String displayTree()  
    {
        total = 0;
        String answer = recDisplayTree(root, 0, 0);
        return answer;
        } 
/*****************************************************
*** Method: countTree
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method initializes total for recDisplayTree.
******************************************************
*** Changes:Nov. 6th - Created the method
******************************************************/	        
    public int countTree()
    {
        total = 0;
        recDisplayTree(root, 0, 0);
        return total;
    }
/*****************************************************
*** Method: recDisplayTree
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This method displays each individual node for the GUI, allowing it 
***          to be called and displayed in jTextArea1.
******************************************************
*** Changes:Nov. 6th - Created the method
******************************************************/		
    private String recDisplayTree(Node234 thisNode, int level, int childNumber)
    {
        String answer = "";
        int numItems = thisNode.getNumItems();
        for(int j = 0; j < numItems+1; j++)
        {
            Node234 nextNode = thisNode.getChild(j);
            if(nextNode != null)
            {
                answer += recDisplayTree(nextNode, level+1, j);
                if(j < numItems)
                {
                    answer += thisNode.getItem(j).displayItem();
                    total += thisNode.getItem(j).count;
                }
            }
            else
            {
                answer += thisNode.displayNode();
                total += thisNode.countNode();
                return answer;
            }
		
        }
        return answer;
    }
}

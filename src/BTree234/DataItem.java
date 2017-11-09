/*****************************************************
*** Class: DataItem
*** Author: Gabriel Winkler
******************************************************
*** Purpose: This class creates the data items for use in the Tree234 class. 
******************************************************
*** Changes:Nov. 6th - Created the class
***         Nov. 6th - Added the DataItem class.
******************************************************/
package BTree234;

/**
 *
 * @author Gabriel
 */
public class DataItem {
    public String dData; //one data item
    public int count;
	
	public DataItem(String dd)
	{ 
            dData = dd;
            count = 1;
        }
	
	public String displayItem()
	{ 
        return dData + "..." + count + "\n";
        }
}

package Astro.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// box class, contains options from baseClass
class Box<T extends baseClass> {
	Map<Integer, T> options = new HashMap<Integer, T>();
	
	// capacity of the box (ex: employee box capacity is 10, however Shifts box capacity is 20)
    int capacity;
    Random rand = new Random();
    public Box(int capacity)
    {
        this.capacity = capacity;
    }
    // push option inside the box
    public void AddOption(T option)
    {
        if (options.size() < capacity)
            options.put(option.Id, option);
    }
    
    // remove an option from the box
    public void RemoveOption(T option)
    {
        options.remove(option.Id);
    }
    
    // check if there is any option in the box or it is empty
    public boolean ContainsOptions()
    {
        return options.size() > 0;
    }
    
    // grab an option randomly from the box
    public T GrabOption()
    {
        if (options.size() == 0)
            return null;
        ArrayList<T> values = new ArrayList<T>(options.values());
        return values.get(rand.nextInt(options.size()));
    }
    // get the number of options within the box
    public int GetOptionsCount()
    {
        return this.options.size();

    }
    
    // create a new box and add all the option from the current box to the new one
    public Box<T> Clone()
    {
        Box<T> bx = new Box<T>(this.GetOptionsCount());
        for (T option : this.options.values()) {
        	bx.AddOption(option);
		}
        return bx;
    }
    
    // Clear the box by removing all the options 
    public void Clear()
    {
        options.clear();
    }

}

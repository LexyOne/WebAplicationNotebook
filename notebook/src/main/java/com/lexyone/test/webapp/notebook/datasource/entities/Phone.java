package com.lexyone.test.webapp.notebook.datasource.entities;

public class Phone {
    private final int area;		// area code (3 digits)
    private final int exch;		// exchange  (3 digits)
    private final int ext1;		// extension1 (2 digits)
    private final int ext2;		// extension2 (2 digits)

    private static final Phone emptyPhone = new Phone(); 
    
	public static Phone getEmptyPhone() {
		return emptyPhone;
	}

    private Phone() {
    	this(0, 0, 0, 0);
    }
    
    public Phone(int area, int exch, int ext1, int ext2) {
    	this.area = area;
        this.exch = exch;
        this.ext1  = ext1;
        this.ext2  = ext2;
    }

	@Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Phone that = (Phone) obj;
        return 		(this.area == that.area) && 
        			(this.exch == that.exch) && 
        			(this.ext1 == that.ext1) && 
        			(this.ext2 == that.ext2);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + area;
		result = prime * result + exch;
		result = prime * result + ext1;
		result = prime * result + ext2;
		return result;
	}

	@Override
    public String toString() {
        return !isEmpty() ? String.format("+(%03d)%03d-%02d-%02d", area, exch, ext1, ext2) : "";
    }
	
	public static String getRegexPattern() {
		return "\\+\\([0-9]{3}\\)[0-9]{3}\\-[0-9]{2}\\-[0-9]{2}";
	} 
	
	public static Phone valueOf(final String value) {
		if(!value.matches(getRegexPattern())) {
			throw new IllegalArgumentException();
		}
	
	    int area = Integer.parseInt(value.substring(2, 5));
	    int exch = Integer.parseInt(value.substring(6, 9));
	    int ext1 = Integer.parseInt(value.substring(10, 12));
	    int ext2 = Integer.parseInt(value.substring(13));
	    
	    return new Phone(area, exch, ext1, ext2);
	} 

	public boolean isEmpty() {
		return area==0 && (exch==0 && ext1==0 && ext2==0);
	}
	public boolean isCorrect() {
		return area!=0 && (exch!=0 || ext1!=0 || ext2!=0);
	}

}

package com.restassured.requests.pojo;

public class PostProductRequest {
	
	
	    private String image;

	    private int shipping;

	    private int price;

	    private String name;

	    private String upc;

	    private String description;

	    private String model;

	    private String type;

	    private String url;

	    private String manufacturer;

	    public PostProductRequest(String name, String type, int price, int shipping, String upc, String description
	    		,String model,  String url, String manufacturer,String image) {

	    	this.name=name;
	    	this.type=type;
	    	this.price=price;
	    	this.shipping=shipping;
	    	this.upc=upc;
	    	this.description=description;
	    	this.model=model;
	    	this.url=url;
	    	this.manufacturer= manufacturer;
	    	this.image= image;

	    }

	    public String getImage ()
	    {
	        return image;
	    }

	    public void setImage (String image)
	    {
	        this.image = image;
	    }

	    public int getShipping ()
	    {
	        return shipping;
	    }

	    public void setShipping (int shipping)
	    {
	        this.shipping = shipping;
	    }

	    public int getPrice ()
	    {
	        return price;
	    }

	    public void setPrice (int price)
	    {
	        this.price = price;
	    }

	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    public String getUpc ()
	    {
	        return upc;
	    }

	    public void setUpc (String upc)
	    {
	        this.upc = upc;
	    }

	    public String getDescription ()
	    {
	        return description;
	    }

	    public void setDescription (String description)
	    {
	        this.description = description;
	    }

	    public String getModel ()
	    {
	        return model;
	    }

	    public void setModel (String model)
	    {
	        this.model = model;
	    }

	    public String getType ()
	    {
	        return type;
	    }

	    public void setType (String type)
	    {
	        this.type = type;
	    }

	    public String getUrl ()
	    {
	        return url;
	    }

	    public void setUrl (String url)
	    {
	        this.url = url;
	    }

	    public String getManufacturer ()
	    {
	        return manufacturer;
	    }

	    public void setManufacturer (String manufacturer)
	    {
	        this.manufacturer = manufacturer;
	    }

	    @Override
	    public String toString()
	    {
	        return "Post Product Request API: [image = "+image+", shipping = "+shipping+", price = "+price+", name = "+name+", upc = "+upc+", description = "+description+", model = "+model+", type = "+type+", url = "+url+", manufacturer = "+manufacturer+"]";
	    }
	
			

}

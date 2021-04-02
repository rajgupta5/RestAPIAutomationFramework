package com.restassured.responses.pojo;

public class PostProductResponse {
	
	
	    private String image;

	    private String createdAt;

	    private String shipping;

	    private String price;

	    private String name;

	    private String upc;

	    private String description;

	    private String model;

	    private String id;

	    private String type;

	    private String url;

	    private String updatedAt;
	    
	    private String manufacturer;
	    

	    public String getManufacturer() {
			return manufacturer;
		}

		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}

		public String getImage ()
	    {
	        return image;
	    }

	    public void setImage (String image)
	    {
	        this.image = image;
	    }

	    public String getCreatedAt ()
	    {
	        return createdAt;
	    }

	    public void setCreatedAt (String createdAt)
	    {
	        this.createdAt = createdAt;
	    }

	    public String getShipping ()
	    {
	        return shipping;
	    }

	    public void setShipping (String shipping)
	    {
	        this.shipping = shipping;
	    }

	    public String getPrice ()
	    {
	        return price;
	    }

	    public void setPrice (String price)
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

	    public String getId ()
	    {
	        return id;
	    }

	    public void setId (String id)
	    {
	        this.id = id;
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

	    public String getUpdatedAt ()
	    {
	        return updatedAt;
	    }

	    public void setUpdatedAt (String updatedAt)
	    {
	        this.updatedAt = updatedAt;
	    }

	    @Override
	    public String toString()
	    {
	        return "Reponse of Post Product API [image = "+image+", createdAt = "+createdAt+", shipping = "+shipping+", price = "+price+", name = "+name+", upc = "+upc+", description = "+description+", model = "+model+", id = "+id+", type = "+type+", url = "+url+", updatedAt = "+updatedAt+"]";
	    }
	

}
